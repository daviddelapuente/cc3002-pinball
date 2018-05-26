package controller.visitor;

import logic.bonus.DropTargetBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

public class Visitor {
    int pts;

    public void visitKickerBumper(KickerBumper k) { }

    public void visitPopBumper(PopBumper p){}

    public void visitSpotTarget(SpotTarget s){}

    public void visitDropTarget(DropTarget d){}

    public void visitJackPotBonus(JackPotBonus j){}

    public void visitDropTargetBonus(DropTargetBonus d){}

    public int getPts(){
        return pts;
    }
}
