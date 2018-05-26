package logic.gameelements.bumper;

import controller.NullGame;
import controller.visitor.Visitor;
import controller.visitor.VisitorPopBumper;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;

import java.util.Random;

public class PopBumper extends AbstractBumper{
    public PopBumper(){
        this.game=new NullGame();
        this.hitsToUpgrade=3;
        this.bmnu=new BumperModeNotUpgrade(100);
        this.bmu=new BumperModeUpgrade(300);

        this.bumperMode=bmnu;
    }

    public void accept(Visitor visitor) {
        visitor.visitPopBumper(this);
    }

    @Override
    public int hit() {
        if(this.remainingHitsToUpgrade()>0){
            this.hitsToUpgrade-=1;
        }

        setChanged();
        Visitor v=new VisitorPopBumper();
        this.accept(v);

        notifyObservers(v);

        if(this.remainingHitsToUpgrade()==0&&!this.isUpgraded()){
            Random generetor = new Random(900000);
            double a=generetor.nextDouble();
            if(a<=0.1){
                this.game.triggerGetExtraBallBonus();
            }
            this.upgrade();
        }
        return 0;
    }

    @Override
    public void resetHitsToUpgrade(){
        this.hitsToUpgrade=3;
    }
}
