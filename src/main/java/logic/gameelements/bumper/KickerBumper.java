package logic.gameelements.bumper;

import controller.NullGame;
import controller.visitor.Visitor;
import controller.visitor.VisitorKickerBumper;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;

import java.util.Random;

public class KickerBumper extends AbstractBumper{

    public KickerBumper(){
        this.game=new NullGame();
        this.hitsToUpgrade=5;
        this.bmnu=new BumperModeNotUpgrade(500);
        this.bmu =new BumperModeUpgrade(1000);

        this.bumperMode=bmnu;
    }

    public void accept(Visitor visitor) {
        visitor.visitKickerBumper(this);
    }

    @Override
    public int hit() {
        if(this.remainingHitsToUpgrade()>0){
            this.hitsToUpgrade-=1;
        }

        setChanged();

        Visitor v = new VisitorKickerBumper();
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
        this.hitsToUpgrade=5;
    }
}
