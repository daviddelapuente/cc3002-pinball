package logic.gameelements.target;

import controller.NullGame;
import controller.visitor.Visitor;
import controller.visitor.VisitorDropTarget;

import java.util.Random;
/**
 * this class extendes the abstractTarget but this metods are unique to this class
 *
 * @author David de la puente
 */
public class DropTarget extends AbstractTarget implements Target {
    private int numberOfDropTargets;

    /**
     * this is the constructor, set the variables
     * the target is active and the game is nullGame
     */
    public DropTarget(){
        this.isActive=true;
        this.game=new NullGame();
    }

    @Override
    public int getScore() {
        return 100;
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
        if (this.isActive){
            this.isActive=false;
            game.getCurrentTable().increseDroppedDropTargets();

            //seed = 900000
            Random generator = new Random(900000);
            double a= generator.nextDouble();

            if(a<0.3){
                this.game.triggerGetExtraBallBonus();
            }

            setChanged();
            Visitor v = new VisitorDropTarget();
            this.accept(v);
            notifyObservers(v);

            if (game.getCurrentTable().getCurrentlyDroppedDropTargets()==game.getCurrentTable().getNumberOfDropTargets()){
                this.game.triggerDropTargetBonus();
            }
        }
        return 0;
    }

    @Override
    public int hit(int seed) {
        if (this.isActive){
            this.isActive=false;
            game.getCurrentTable().increseDroppedDropTargets();
            Random generator = new Random(seed);
            double a= generator.nextDouble();

            if(a<0.3){
                this.game.triggerGetExtraBallBonus();
            }

            setChanged();
            Visitor v = new VisitorDropTarget();
            this.accept(v);
            notifyObservers(v);

            if (game.getCurrentTable().getCurrentlyDroppedDropTargets()==game.getCurrentTable().getNumberOfDropTargets()){
                this.game.triggerDropTargetBonus();
            }
        }
        return 0;
    }

    /**
     * set the variable numberOfDropTargets, to the @param
     * @param numberOfDropTargets is the number of dropDropTargets in the table
     */
    public void setNumberOfDropTargets(int numberOfDropTargets){
        this.numberOfDropTargets=numberOfDropTargets;
    }
}
