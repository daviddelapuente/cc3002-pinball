package logic.controller;

import logic.bonus.Bonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;

import java.util.List;

public interface IGame{
    public boolean isPlayableTable();

    public void setGameTable(Table newTable);

    public String getTableName();

    public Table getCurrentTable();

    public List<Bumper> getBumpers();

    public List<Target> getTargets();

    public Bonus getDropTargetBonus();

    public Bonus getExtraBallBonus();

    public Bonus getJackPotBonus();

    public int getAvailableBalls();

    public int getCurrentScore();

    public void dropBall();

    public void addBall();

    public boolean gameOver();

    public void triggerGetExtraBallBonus();

    public void triggerJackPotBonus();

    public void triggerDropTargetBonus();

    public void plusScore(int score);

    public int getPopBumpers();

    public int getKickerBumpers();

    public int getDropTargets();

    public int getSpotTargets();

    public boolean getIsActivateDropBonus();

    public void setIsActivateDropBonus(boolean b);
}
