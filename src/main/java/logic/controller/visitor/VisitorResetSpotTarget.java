package logic.controller.visitor;

import logic.controller.Game;

public class VisitorResetSpotTarget extends Visitor {
    @Override
    public void makeTheMagic(Game game) {
        game.getCurrentTable().decreseDroppedSpotTarget();
    }
}