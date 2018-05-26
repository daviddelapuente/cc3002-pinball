package controller.visitor;

import logic.gameelements.target.SpotTarget;

public class VisitorSpotTarget extends Visitor {
    @Override
    public void visitSpotTarget(SpotTarget s) {
        this.pts=s.getScore();
    }
}
