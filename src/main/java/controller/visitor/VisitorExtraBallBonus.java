package controller.visitor;

import controller.Game;

public class VisitorExtraBallBonus extends Visitor{
    /**
     * this visitor make the double dispatch then trigger an extrabalbonus
     * in the game
     * @param game
     */
    @Override
    public void makeTheMagic(Game game) {
        game.triggerGetExtraBallBonus();
    }
}
