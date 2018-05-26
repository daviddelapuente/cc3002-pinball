package logic.gameelements.bumper.bumpermode;

public class BumperModeNotUpgrade extends AbstractBumperMode {
    public BumperModeNotUpgrade(int n){
        this.score=n;
    }

    @Override
    public boolean getIsUpgrade(){
        return false;
    }
}
