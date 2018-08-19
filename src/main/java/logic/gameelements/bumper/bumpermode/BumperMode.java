package logic.gameelements.bumper.bumpermode;
/**
 * Interface that represents the modes of the bumpers
 * bumpers that have diferents upgrades should implement this interface
 *
 * @author David de la puent
 */
public interface BumperMode {

    /**
     * @return if the bumper mode is upGrade
     */
    boolean getIsUpgrade();

    /**
     * return the score of the bumperMode, that is unique to a bumperMode
     * @return
     */
    int getScore();
}
