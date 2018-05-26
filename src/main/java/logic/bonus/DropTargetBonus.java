package logic.bonus;

import controller.Game;

import java.util.Observable;

public class DropTargetBonus extends Observable implements Bonus{
    private int timesTriggered;

    public DropTargetBonus(){
        this.timesTriggered=0;
    }

    @Override
    public int timesTriggered() {
        return this.timesTriggered;
    }

    @Override
    public void trigger(Game game) {
        this.timesTriggered+=1;
        game.getCurrentTable().upgradeAllBumpers();
        setChanged();
        notifyObservers(1000000);
    }
}
