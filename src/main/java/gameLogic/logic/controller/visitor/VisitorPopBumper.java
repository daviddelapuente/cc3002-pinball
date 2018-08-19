package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;
import gameLogic.logic.gameelements.bumper.PopBumper;

/**
 * visitor class, this class is created when a popBumper
 * is hit, then the observers, can call the makeTheMagic metod.
 *
 * @author David de la puente
 */
public class VisitorPopBumper extends Visitor {
    @Override
    public void visitPopBumper(PopBumper p) {
        this.pts=p.getScore();
    }

    /**
     * set the score of the game
     * @param g
     */
    @Override
    public void makeTheMagic(Game g){
        g.plusScore(pts);
    }
}
