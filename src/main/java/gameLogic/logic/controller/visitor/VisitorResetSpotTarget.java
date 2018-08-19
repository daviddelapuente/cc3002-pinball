package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;

public class VisitorResetSpotTarget extends Visitor {
    @Override
    public void makeTheMagic(Game game) {
        game.getCurrentTable().decreseDroppedSpotTarget();
    }
}