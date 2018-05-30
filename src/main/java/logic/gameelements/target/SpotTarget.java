package logic.gameelements.target;

import controller.visitor.Visitor;
import controller.visitor.VisitorJackPotBonus;
import controller.visitor.VisitorSpotTarget;

import java.util.Random;

public class SpotTarget extends AbstractTarget implements Target{

    /**
     * this is the constructor, set the variables
     * the target is active and the game is nullGame
     */
    public SpotTarget(){
        this.isActive=true;
    }

    @Override
    public int getScore() {
        return 0;
    }

    /**
     * this metod tells the visitor that the object that send the message is a SpotTarget(double dispatch)
     * @param visitor a SpotTargetVisitor
     */
    public void accept(Visitor visitor) {
        visitor.visitSpotTarget(this);
    }

    /**
     * this metod tells the visitor that the object that send the message is a SpotTarget(double dispatch)
     * @param visitor a JackPotVisitor
     */

    /**
     * Alredy explained in the hittable interface
     * particular in this class, this migth trigger a jackPotBonus Bonus from the game
     * @return 0
     */
    @Override
    public int hit(int seed) {
        if(this.isActive){
            this.isActive=false;

            //seed =900000
            Random generator = new Random(seed);
            double a= generator.nextDouble();

            if(a<0.02){
                setChanged();
                Visitor bonusVisitor=new VisitorJackPotBonus();
                notifyObservers(bonusVisitor);
            }

            setChanged();
            Visitor v = new VisitorSpotTarget();
            this.accept(v);
            notifyObservers(v);
        }
        return 0;
    }

    @Override
    public int hit() {
        if(this.isActive){
            this.isActive=false;

            //seed =900000
            Random generator = new Random();
            double a= generator.nextDouble();

            if(a<0.02){
                setChanged();
                Visitor bonusVisitor=new VisitorJackPotBonus();
                notifyObservers(bonusVisitor);
            }

            setChanged();
            Visitor v = new VisitorSpotTarget();
            this.accept(v);
            notifyObservers(v);
        }
        return 0;
    }
}
