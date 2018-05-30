package controller.visitor;

import controller.Game;

public class VisitorDropTargetBonus extends Visitor {
    @Override
    public void makeTheMagic(Game game) {
        if (game.getCurrentTable().getCurrentlyDroppedDropTargets()==game.getCurrentTable().getNumberOfDropTargets()){
            game.triggerDropTargetBonus();
        }
    }
}
