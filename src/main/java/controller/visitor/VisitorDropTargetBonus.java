package controller.visitor;

import controller.Game;

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
