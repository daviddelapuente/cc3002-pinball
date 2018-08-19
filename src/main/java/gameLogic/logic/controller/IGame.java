package gameLogic.logic.controller;

import gameLogic.logic.bonus.Bonus;
import gameLogic.logic.controller.visitor.Visitor;
import gameLogic.logic.gameelements.bumper.Bumper;
import gameLogic.logic.gameelements.target.Target;
import gameLogic.logic.table.NullGameTable;
import gameLogic.logic.table.Table;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
