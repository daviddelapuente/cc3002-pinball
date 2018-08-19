package gameLogic.logic.gameelements.target;

import gameLogic.logic.controller.visitor.Visitor;
import gameLogic.logic.controller.visitor.VisitorResetDropTarget;

import java.util.Observable;
/**
 * Abstract Class that has some variables and methods in common of spotTargets
 * and DropTargets
 *
 * <p>Objets that are targets should extends this interface</p>
 *
 * @author David de la puente
 * @see gameLogic.logic.gameelements.target.DropTarget
 * @see gameLogic.logic.gameelements.target.SpotTarget
 */
public abstract class AbstractTarget extends Observable implements Target {
    protected boolean isActive;
    protected int score;

    @Override
    public boolean isActive() {
        return this.isActive;
    }


    @Override
    public abstract  int hit();

    @Override
    public abstract int hit(int seed);
}
