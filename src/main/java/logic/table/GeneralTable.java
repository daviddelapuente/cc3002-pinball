package logic.table;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GeneralTable extends Observable implements Observer,Table {
    protected Game game;
    protected int currentlyDroppedDropTargets;

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public int getNumberOfDropTargets() {
        return 0;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return 0;
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
    public void resetDropTargets() {

    }

    @Override
    public void upgradeAllBumpers() {

    }

    @Override
    public boolean isPlayableTable() {
        return false;
    }

    @Override
    public void makePlayable() {

    }

    @Override
    public int getNumberOfBumpers(){
        return 0;
    }

    @Override
    public double getProp() {
        return 0;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void setGame(Game game){this.game=game;}

    @Override
    public void increseDroppedDropTargets() {
        this.currentlyDroppedDropTargets+=1;
    }

}
