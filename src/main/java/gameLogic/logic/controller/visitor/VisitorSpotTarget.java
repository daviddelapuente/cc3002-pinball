package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;
import gameLogic.logic.gameelements.target.SpotTarget;

/**
 * visitor class, this class is created when a spotTarget
 * is hit, then the observers, can call the makeTheMagic metod.
 *
 * @author David de la puente
 */
public class VisitorSpotTarget extends Visitor {
    @Override
    public void visitSpotTarget(SpotTarget s) {
        this.pts=s.getScore();
    }

    /**
     * set the score of the game
     * @param g
     */
    @Override
    public void makeTheMagic(Game g){
        g.getCurrentTable().increseDroppedSpotTargets();
        g.plusScore(pts);
    }
}
