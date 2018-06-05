package logic.gameelements.bumper;

import controller.visitor.Visitor;
import controller.visitor.VisitorKickerBumper;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;
/**
 * this class extendes the abstractBumper but this metods are unique to this class
 *
 * @author David de la puente
 */
public class KickerBumper extends AbstractBumper{

    /**
     * this is the constructor, set the variables
     * game start as nullGame
     * and the bumper modes are inicialized
     */
    public KickerBumper(){
        this.hitsToUpgrade=5;
        this.bmnu=new BumperModeNotUpgrade(500);
        this.bmu =new BumperModeUpgrade(1000);
        this.bumperMode=bmnu;
    }

    /**
     * this metod tells the visitor that the object that send the message is a kick bumper (double dispatch)
     * @param visitor a KickBumperVisitor
     */
    public void accept(Visitor visitor) {
        visitor.visitKickerBumper(this);
    }

    /**
     * Alredy explained in the hittable interface
     * particular in this class, this migth trigger an extraBall Bonus from the game
     * @return 0
     */
    @Override
    public int hit() {
        if(this.remainingHitsToUpgrade()>0){
            this.hitsToUpgrade-=1;
        }

        setChanged();
        Visitor v = new VisitorKickerBumper();
        this.accept(v);
        notifyObservers(v);

        this.bonusOfHit();
        return this.getScore();
    }

    /**
     * same as upside but has a seed
     * @param seed
     * @return 0
     */
    @Override
    public int hit(int seed) {
        if(this.remainingHitsToUpgrade()>0){
            this.hitsToUpgrade-=1;
        }

        setChanged();
        Visitor v = new VisitorKickerBumper();
        this.accept(v);
        notifyObservers(v);

        this.bonusOfHit(seed);
        return this.getScore();
    }

    /**
     * set the variable hits to upgrade to 5
     */
    @Override
    public void resetHitsToUpgrade(){
        this.hitsToUpgrade=5;
    }
}
