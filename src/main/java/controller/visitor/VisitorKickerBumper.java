package controller.visitor;

import controller.Game;
import logic.gameelements.bumper.KickerBumper;

public class VisitorKickerBumper extends Visitor{
    @Override
    public void visitKickerBumper(KickerBumper k) {
        this.pts=k.getScore();
    }

    /**
     * set the socre of the game
     * @param g
     */
    @Override
    public void makeTheMagic(Game g){
        g.plusScore(pts);
    }
}
