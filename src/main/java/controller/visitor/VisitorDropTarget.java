package controller.visitor;

import logic.gameelements.target.DropTarget;

public class VisitorDropTarget extends Visitor {
    @Override
    public void visitDropTarget(DropTarget d) {
        this.pts=d.getScore();
    }
}
