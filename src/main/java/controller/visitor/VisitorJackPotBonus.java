package controller.visitor;

import logic.bonus.JackPotBonus;

public class VisitorJackPotBonus extends Visitor {
    @Override
    public void visitJackPotBonus(JackPotBonus j) {
        this.pts=100000;
    }
}
