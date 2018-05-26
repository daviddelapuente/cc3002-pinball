package controller;

import logic.bonus.Bonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;

import java.util.List;

public interface IGame {
    boolean isPlayableTable();

    void setGameTable(Table newTable);

    String getTableName();

    Table getCurrentTable();

    List<Bumper> getBumpers();

    List<Target> getTargets();

    Bonus getDropTargetBonus();

    Bonus getExtraBallBonus();

    Bonus getJackPotBonus();

    int getAvailableBalls();

    int getCurrentScore();

    void dropBall();

    void addBall();

    boolean gameOver();

    void triggerGetExtraBallBonus();

    void triggerJackPotBonus();

    void triggerDropTargetBonus();
}
