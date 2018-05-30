package controller.visitor;

import controller.Game;
import logic.gameelements.bumper.PopBumper;

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
