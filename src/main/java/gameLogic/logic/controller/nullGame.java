package gameLogic.logic.controller;

import gameLogic.logic.bonus.Bonus;
import gameLogic.logic.gameelements.bumper.Bumper;
import gameLogic.logic.gameelements.target.Target;
import gameLogic.logic.table.Table;

import java.util.List;

public class nullGame implements IGame {
    @Override
    public boolean isPlayableTable() {
        return false;
    }

    @Override
    public void setGameTable(Table newTable) {

    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Table getCurrentTable() {
        return null;
    }

    @Override
    public List<Bumper> getBumpers() {
        return null;
    }

    @Override
    public List<Target> getTargets() {
        return null;
    }

    @Override
    public Bonus getDropTargetBonus() {
        return null;
    }

    @Override
    public Bonus getExtraBallBonus() {
        return null;
    }

    @Override
    public Bonus getJackPotBonus() {
        return null;
    }

    @Override
    public int getAvailableBalls() {
        return 0;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }

    @Override
    public void dropBall() {

    }

    @Override
    public void addBall() {

    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public void triggerGetExtraBallBonus() {

    }

    @Override
    public void triggerJackPotBonus() {

    }

    @Override
    public void triggerDropTargetBonus() {

    }

    @Override
    public void plusScore(int score) {

    }

    @Override
    public int getPopBumpers() {
        return 0;
    }

    @Override
    public int getKickerBumpers() {
        return 0;
    }

    @Override
    public int getDropTargets() {
        return 0;
    }

    @Override
    public int getSpotTargets() {
        return 0;
    }

    @Override
    public boolean getIsActivateDropBonus() {
        return false;
    }

    @Override
    public void setIsActivateDropBonus(boolean b) {

    }
}
