package controller.visitor;

import logic.gameelements.bumper.PopBumper;

public class VisitorPopBumper extends Visitor {
    @Override
    public void visitPopBumper(PopBumper p) {
        this.pts=p.getScore();
    }
}
