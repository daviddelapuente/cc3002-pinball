package controller.visitor;

import controller.Game;

public class VisitorExtraBallBonus extends Visitor{
    @Override
    public void makeTheMagic(Game game) {
        game.triggerGetExtraBallBonus();
    }
}
