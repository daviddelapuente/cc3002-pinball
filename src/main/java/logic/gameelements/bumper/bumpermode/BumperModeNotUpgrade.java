package logic.gameelements.bumper.bumpermode;
/**
 * class that represent a bumper that is not upgraded
 *
 * @author David de la puent
 */
public class BumperModeNotUpgrade extends AbstractBumperMode {
    /**
     * the constructor of the class
     * @param n the score of a bumper (upgraded or not)
     */
    public BumperModeNotUpgrade(int n){
        this.score=n;
    }

    /**
     * @return false becaus the mode isnt upgrade
     */
    @Override
    public boolean getIsUpgrade(){
        return false;
    }
}
