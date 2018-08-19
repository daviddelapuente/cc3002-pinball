package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
/**
 * class that represent a table (null or gameTable)
 *should be implementated in game clases
 * @author David de la puente
 */
public class NullGameTable extends Observable implements Observer,Table {
    private List<Bumper> bumpers=new ArrayList<>();
    private List<Target> targets=new ArrayList<>();

    @Override
    public String getTableName() {
        return "";
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
        return this.bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return this.targets;
    }

    @Override
    public boolean isPlayableTable() {
        return false;
    }

    @Override
    public void resetDropTargets() {

    }

    @Override
    public void upgradeAllBumpers() {

    }

    @Override
    public int getCurrentlyDroppedSpotTargets() {
        return 0;
    }

    @Override
    public void increseDroppedDropTargets() {
    }

    @Override
    public void decreseDroppedDropTarget() {

    }

    @Override
    public void makePlayable() {

    }

    @Override
    public int getPopBumpers(){
        return 0;
    }

    @Override
    public int getKickerBumpers(){
        return 0;
    }

    @Override
    public int getDropTargets(){return 0;}

    @Override
    public int getSpotTargets(){return 0;}

    @Override
    public void decreseDroppedSpotTarget() {

    }

    @Override
    public void resetSpotTargets() {

    }

    @Override
    public void increseDroppedSpotTargets() {

    }

    /**
     * this metod is called when the observable objetc, makes notiications
     * @param o is the object that notify
     * @param arg the informacion traspased
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
