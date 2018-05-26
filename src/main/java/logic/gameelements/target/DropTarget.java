package logic.gameelements.target;

import controller.NullGame;
import controller.visitor.Visitor;
import controller.visitor.VisitorDropTarget;

import java.util.Random;

public class DropTarget extends AbstractTarget implements Target { private int numberOfDropTargets;
    public DropTarget(){
        this.isActive=true;
        this.game=new NullGame();
    }

    @Override
    public int getScore() {
        return 100;
    }

    public void accept(Visitor visitor) {
        visitor.visitDropTarget(this);
    }

    @Override
    public int hit() {
        if (this.isActive){
            this.isActive=false;
            game.getCurrentTable().increseDroppedDropTargets();
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

    public void setNumberOfDropTargets(int numberOfDropTargets){
        this.numberOfDropTargets=numberOfDropTargets;
    }
}
