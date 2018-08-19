package gameLogic.logic.gameelements.bumper.bumpermode;
/**
 * class that represent a bumper that is upgraded
 *
 * @author David de la puent
 */
public class BumperModeUpgrade extends AbstractBumperMode {

    /**
     * the constructor of the class
     * @param n the score of a bumper (upgraded or not)
     */
    public BumperModeUpgrade(int n){
        this.score=n;
    }

    /**
     * @return true because the mode is upgrade
     */
    @Override
    public boolean getIsUpgrade(){
        return true;
    }
}
