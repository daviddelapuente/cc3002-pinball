package logic.gameelements.target;

import controller.NullGame;
import controller.visitor.Visitor;
import controller.visitor.VisitorSpotTarget;

import java.util.Random;

public class SpotTarget extends AbstractTarget implements Target{
    public SpotTarget(){
        this.isActive=true;
        this.game=new NullGame();
    }

    @Override
    public int getScore() {
        return 0;
    }

    public void accept(Visitor visitor) {
        visitor.visitSpotTarget(this);
    }

    @Override
    public int hit() {
        if(this.isActive){
            this.isActive=false;

            Random generator = new Random(900000);
            double a= generator.nextDouble();

            if(a<0.02){
                this.game.triggerJackPotBonus();
            }

            setChanged();
            Visitor v = new VisitorSpotTarget();
            this.accept(v);
            notifyObservers(v);
        }
        return 0;
    }
}
