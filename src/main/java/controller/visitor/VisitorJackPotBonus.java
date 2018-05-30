package controller.visitor;

import controller.Game;

public class VisitorJackPotBonus extends Visitor{
    /**
     * make the double dispatch in the game
     * an triger a jackpotbonus
     * @param game
     */
    @Override
    public void makeTheMagic(Game game) {
        game.triggerJackPotBonus();
    }
}
