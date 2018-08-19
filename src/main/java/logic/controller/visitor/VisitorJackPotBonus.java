package logic.controller.visitor;

import logic.controller.Game;

/**
 * visitor class, this class is created when a spotTarget
 * is hit, then the observers, can call the makeTheMagic metod.
 *
 * @author David de la puente
 */
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
