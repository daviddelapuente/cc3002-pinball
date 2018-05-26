package controller;

import logic.bonus.Bonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.NullGameTable;
import logic.table.Table;

import java.util.List;
/**
 * null game object will null metos implementations, must be inizialised in classes that
 * need to inizialise a game
 *
 * @author David de la puente
 */
public class NullGame implements IGame {
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
        Table nullTable=new NullGameTable();
        return nullTable;
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
}
