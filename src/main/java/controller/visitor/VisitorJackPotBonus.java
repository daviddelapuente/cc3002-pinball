package controller.visitor;

import controller.Game;

public class VisitorJackPotBonus extends Visitor{
    @Override
    public void makeTheMagic(Game game) {
        game.triggerJackPotBonus();
    }
}
