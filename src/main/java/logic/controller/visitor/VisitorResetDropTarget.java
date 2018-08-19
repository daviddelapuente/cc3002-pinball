package logic.controller.visitor;

import logic.controller.Game;

/**
 * visitor class, this class is created when a dropTarget
 * is reset, then the observers, can call the makeTheMagic metod.
 *
 * @author David de la puente
 */
public class VisitorResetDropTarget extends Visitor {
    /**
     * make the double dispatch in the game
     * and decreese the currentlyDroppedDropTarget in 1
     * @param game
     */
    @Override
    public void makeTheMagic(Game game) {
        game.getCurrentTable().decreseDroppedDropTarget();
    }
}
