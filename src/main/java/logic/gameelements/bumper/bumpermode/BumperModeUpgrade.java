package logic.gameelements.bumper.bumpermode;

public class BumperModeUpgrade extends AbstractBumperMode {
    public BumperModeUpgrade(int n){
        this.score=n;
    }


    @Override
    public boolean getIsUpgrade(){
        return true;
    }
}
