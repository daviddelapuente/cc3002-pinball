package gameLogic.logic.gameelements.target;

import gameLogic.logic.controller.visitor.*;

import java.util.Random;
/**
 * this class extendes the abstractTarget but this metods are unique to this class
 *
 * @author David de la puente
 */
public class DropTarget extends AbstractTarget implements Target {

    /**
     * this is the constructor, set the variables
     * the target is active and the game is nullGame
     */
    public DropTarget(){
        this.isActive=true;
        this.score=100;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * this metod tells the visitor that the object that send the message is a DropTarget(double dispatch)
     * @param visitor a DropTargetBumper
     */
    public void accept(Visitor visitor) {
        visitor.visitDropTarget(this);
    }

    /**
     * Alredy explained in the hittable interface
     * particular in this class, this migth trigger an extraBall Bonus from the game
     * if al the dropTargets are dropet, then it migth trigger a DropTargetBonus
     * @return 0
     */
    @Override
    public int hit() {
        int hitValue=0;
        if (this.isActive){
            //seed = 900000
            Random generator = new Random();
            double a= generator.nextDouble();

            if(a<0.3){
               setChanged();
               Visitor bonusVisitor=new VisitorExtraBallBonus();
               notifyObservers(bonusVisitor);
            }

            setChanged();
            Visitor v = new VisitorDropTarget();
            this.accept(v);
            notifyObservers(v);

            setChanged();
            Visitor bonusVisitor= new VisitorDropTargetBonus();
            notifyObservers(bonusVisitor);

            this.isActive=false;
            hitValue=this.getScore();
            this.score=0;
        }
        return hitValue;
    }

    @Override
    public int hit(int seed) {
        int hitValue=0;
        if (this.isActive){
            Random generator = new Random(seed);
            double a= generator.nextDouble();

            if(a<0.3){
                setChanged();
                Visitor bonusVisitor=new VisitorExtraBallBonus();
                notifyObservers(bonusVisitor);
            }

            setChanged();
            Visitor v = new VisitorDropTarget();
            this.accept(v);
            notifyObservers(v);

            setChanged();
            Visitor bonusVisitor= new VisitorDropTargetBonus();
            notifyObservers(bonusVisitor);

            this.isActive=false;
            hitValue=this.getScore();
            this.score=0;
        }
        return hitValue;
    }

    @Override
    public void reset() {
        setChanged();
        Visitor v = new VisitorResetDropTarget();
        notifyObservers(v);
        this.isActive=true;
    }
}
