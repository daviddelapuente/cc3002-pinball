package controller.visitor;

import logic.gameelements.bumper.KickerBumper;

public class VisitorKickerBumper extends Visitor{
    @Override
    public void visitKickerBumper(KickerBumper k) {
        this.pts=k.getScore();
    }
}
