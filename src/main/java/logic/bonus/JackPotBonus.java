package logic.bonus;

import controller.Game;

import java.util.Observable;

public class JackPotBonus extends Observable implements Bonus {
    private int timesTriggered;

    public JackPotBonus(){
        this.timesTriggered=0;
    }

    @Override
    public int timesTriggered() {
        return this.timesTriggered;
    }

    @Override
    public void trigger(Game game) {
        this.timesTriggered+=1;
        setChanged();
        notifyObservers(100000);
    }
}
