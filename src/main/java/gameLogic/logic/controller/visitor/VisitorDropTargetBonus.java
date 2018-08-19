package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;
/**
 * visitor class, this class is created when a dropTarget
 * is hit, then the observers, can call the makeTheMagic metod.
 * when all the dropTargets are dropped
 *
 * @author David de la puente
 */
public class VisitorDropTargetBonus extends Visitor {
    /**
     * if the droped targets are equals to the number of targets
     * the a droptarget bonus is triggered
     * @param game
     */
    @Override
    public void makeTheMagic(Game game) {
        if (game.getCurrentTable().getCurrentlyDroppedDropTargets()==game.getCurrentTable().getNumberOfDropTargets()){
            game.triggerDropTargetBonus();
        }
    }
}
