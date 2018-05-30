package controller.visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;

public class VisitorDropTarget extends Visitor {
    @Override
    public void visitDropTarget(DropTarget d) {
        this.pts=d.getScore();
    }

    @Override
    public void makeTheMagic(Game g){
        g.getCurrentTable().increseDroppedDropTargets();
        g.plusScore(pts);
    }
}
