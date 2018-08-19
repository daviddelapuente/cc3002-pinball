package gameLogic.logic.bonus;

import gameLogic.logic.controller.Game;

import java.util.Observable;
/**
 * Abstract Class that has some variables and methods in common of DropTargetBonus
 * ExtraBallBonus and JackPotBonus
 *
 * <p>Objets that are Bonus should extends this interface</p>
 *
 * @author David de la puente
 * @see gameLogic.logic.bonus.DropTargetBonus
 * @see gameLogic.logic.bonus.ExtraBallBonus
 * @see gameLogic.logic.bonus.JackPotBonus
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
