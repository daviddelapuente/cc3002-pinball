package logic.gameelements.bumper;

import controller.visitor.Visitor;
import controller.visitor.VisitorPopBumper;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;

/**
 * this class extendes the abstractBumper but this metods are unique to this class
 *
 * @author David de la puente
 */
public class PopBumper extends AbstractBumper{

    /**
     * this is the constructor, set the variables
     * game start as nullGame
     * and the bumper modes are inicialized
     */
    public PopBumper(){
        this.hitsToUpgrade=3;
        this.bmnu=new BumperModeNotUpgrade(100);
        this.bmu=new BumperModeUpgrade(300);

        this.bumperMode=bmnu;
    }

    /**
     * this metod tells the visitor that the object that send the message is a PopBumper (double dispatch)
     * @param visitor a popBumperVisitor
     */
    public void accept(Visitor visitor) {
        visitor.visitPopBumper(this);
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
        Visitor v=new VisitorPopBumper();
        this.accept(v);

        notifyObservers(v);

        this.bonusOfHit();
        return 0;
    }

    @Override
    public int hit(int seed) {
        if(this.remainingHitsToUpgrade()>0){
            this.hitsToUpgrade-=1;
        }

        setChanged();
        Visitor v=new VisitorPopBumper();
        this.accept(v);

        notifyObservers(v);

        this.bonusOfHit(seed);
        return 0;
    }

    /**
     * set the variable hits to upgrade to 3
     */
    @Override
    public void resetHitsToUpgrade(){
        this.hitsToUpgrade=3;
    }
}
