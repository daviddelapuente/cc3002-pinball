package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;

/**
 * visitor class, this class is created when an extraBallBonus is needed
 * then the observers, can call the makeTheMagic metod. to trigger the bonus
 * and make changes in the game
 *
 * @author David de la puente
 */
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
