package logic.table;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
/**
 * class that represent a table (null or gameTable)
 *should be implementated in game clases
 * @author David de la puente
 */
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

    /**
     * this metod is called when the observable objetc, makes notiications
     * @param o is the object that notify
     * @param arg the informacion traspased
     */
    @Override
    public void update(Observable o, Object arg) {

    }

    public void setGame(Game game){this.game=game;}

    @Override
    public void increseDroppedDropTargets() {
        this.currentlyDroppedDropTargets+=1;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

}
