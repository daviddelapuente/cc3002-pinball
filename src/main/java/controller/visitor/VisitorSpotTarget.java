package controller.visitor;

import controller.Game;
import logic.gameelements.target.SpotTarget;

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
        g.plusScore(pts);
    }
}
