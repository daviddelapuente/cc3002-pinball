package logic.gameelements.bumper;

import controller.Game;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.bumpermode.BumperMode;

/**
 * Interface that represents operations related to a bumper behavior.
 *
 * @author Juan-Pablo Silva
 * @see KickerBumper
 * @see PopBumper
 */
public interface Bumper extends Hittable {
    /**
     * Gets the remaining hits the bumper has to receive to upgrade.
     *
     * @return the remaining hits to upgrade the bumper
     */
    int remainingHitsToUpgrade();

    /**
     * Gets whether the bumper is currently upgraded or not.
     *
     * @return true if the bumper is upgraded, false otherwise
     */
    boolean isUpgraded();

    /**
     * Upgrades a bumper making {@link #isUpgraded()} return true.
     */
    void upgrade();

    /**
     * Downgrades a bumper making {@link #isUpgraded()} return false.
     */
    void downgrade();

    /**
     * set the game parameter in an abstractBumper (double thispatch)
     * so then the bumper can message the game that has to triggar a bonus
     * @param game the game controller object
     */
    void setGame(Game game);

    /**
     * return the the mode of a bumper (if is upgrade or not)
     * the modes has the information of the score of the bumper
     * @see logic.gameelements.bumper.bumpermode
     */
    BumperMode getBumperMode();

    /**
     * this metod is in charge to probably trigger a bonus
     * but it has a random seed, so is testable
     */
    void bonusOfHit(int seed);

    /**
     * this metod is the same that the last but is random so it
     * cant be tested
     */
    void bonusOfHit();
}
