package logic.bonus;

import logic.controller.Game;

import java.util.Observable;
/**
 * Abstract Class that has some variables and methods in common of DropTargetBonus
 * ExtraBallBonus and JackPotBonus
 *
 * <p>Objets that are Bonus should extends this interface</p>
 *
 * @author David de la puente
 * @see logic.bonus.DropTargetBonus
 * @see logic.bonus.ExtraBallBonus
 * @see logic.bonus.JackPotBonus
 */

public class AbstractBonus extends Observable implements Bonus {
    protected int timesTriggered;

    @Override
    public int timesTriggered() {
        return this.timesTriggered;
    }

    @Override
    public void trigger(Game game) {

    }
}
