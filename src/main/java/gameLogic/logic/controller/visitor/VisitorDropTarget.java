package gameLogic.logic.controller.visitor;

import gameLogic.logic.controller.Game;
import gameLogic.logic.gameelements.target.DropTarget;
/**
 * visitor class, this class is created when a dropTarget
 * is hit, then the observers, can call the makeTheMagic metod.
 *
 * @author David de la puente
 */
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
