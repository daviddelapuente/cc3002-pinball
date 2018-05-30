package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;

import java.util.List;

/**
 * Interface that represents a Game object
 *
 * @author Dvid de la puente
 * @see ExtraBallBonus
 * @see JackPotBonus
 * @see DropTargetBonus
 */
public interface IGame {

    /**
     * @return if the table of the game is playable
     */
    boolean isPlayableTable();

    /**
     * when a game is created is inicialized whit a null table
     * so this metod, set the valeu of the table to the param
     * @param newTable the new table
     */
    void setGameTable(Table newTable);

    /**
     * @return the table name
     */
    String getTableName();

    /**
     * @return the actual table which is being played
     */
    Table getCurrentTable();

    /**
     * @return the bumpers of the table
     */
    List<Bumper> getBumpers();

    /**
     * @return the targets of the table
     */
    List<Target> getTargets();

    /**
     * @return the DropTargetBonus variable that has the game
     */
    Bonus getDropTargetBonus();

    /**
     * @return the ExtraBallBonusThat has the game
     */
    Bonus getExtraBallBonus();

    /**
     * @return the jackPotBonus That has the game
     */
    Bonus getJackPotBonus();

    /**
     * @return the number of balls that has the game
     */
    int getAvailableBalls();

    /**
     * @return the score of the game
     */
    int getCurrentScore();

    /**
     * decreese in one the number of ballsavailable in the game
     */
    void dropBall();

    /**
     * increese in one the numer of balls available in the game
     */
    void addBall();

    /**
     * @return the state of the game, if the game ends, this will return true
     */
    boolean gameOver();

    /**
     * trigger the extraBallBonus that the game has
     */
    void triggerGetExtraBallBonus();

    /**
     * trigger the jackPotBonus that the game has
     */
    void triggerJackPotBonus();

    /**
     * trigger the DropTargetBonus that the game has
     */
    void triggerDropTargetBonus();

    /**
     * set the game score=score+i
     * @param score the points
     */
    void plusScore(int score);
}
