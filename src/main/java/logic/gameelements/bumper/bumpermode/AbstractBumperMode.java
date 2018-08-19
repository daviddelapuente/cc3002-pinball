package logic.gameelements.bumper.bumpermode;

/**
 * Abstract Class that has the atributes an common metods of bumperModes
 *
 * @author David de la puent
 */

public abstract class AbstractBumperMode implements BumperMode {
    protected int score;

    public abstract boolean getIsUpgrade();

    public int getScore(){
        return this.score;
    }
}
