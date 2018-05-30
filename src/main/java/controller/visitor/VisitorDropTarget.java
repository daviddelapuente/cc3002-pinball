package controller.visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;

public class VisitorDropTarget extends Visitor {
    @Override
    public void visitDropTarget(DropTarget d) {
        this.pts=d.getScore();
    }

    /**
     * this visitor make the double dispatch in the droptargets
     * so increse a ball and set the score in the game
     * @param g
     */
    @Override
    public void makeTheMagic(Game g){
        g.getCurrentTable().increseDroppedDropTargets();
        g.plusScore(pts);
    }
}
