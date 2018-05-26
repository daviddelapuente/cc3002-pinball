package controller.visitor;

import logic.bonus.DropTargetBonus;

public class VisitorDropTargetBonus extends Visitor {

    @Override
    public void visitDropTargetBonus(DropTargetBonus d) {
        this.pts=1000000;
    }
}
