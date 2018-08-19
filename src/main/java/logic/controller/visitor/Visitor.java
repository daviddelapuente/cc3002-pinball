package logic.controller.visitor;

import logic.controller.Game;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
/**
 * visitor class, that is in charge of the double dispatch
 * of the observed objects whit the observers
 *
 * @author David de la puente
 */
public class Visitor {
    int pts;

    /**
     * this metod is in charge, for make the double dispatch and trigger
     * the accept metod of the kickerBumper
     * @param k the kickerBumper
     */
    public void visitKickerBumper(KickerBumper k) { }

    /**
     * this metod is in charge, for make the double dispatch and trigger
     * the accept metod of the popBumper
     * @param p the popBumper
     */
    public void visitPopBumper(PopBumper p){}

    /**
     * this metod is in charge, for make the double dispatch and trigger
     * the accept metod of the spotTarget
     * @param s the spotTarget
     */
    public void visitSpotTarget(SpotTarget s){}

    /**
     * this metod is in charge, for make the double dispatch and trigger
     * the accept metod of the DropTarget
     * @param d the dropTarget
     */
    public void visitDropTarget(DropTarget d){}



    /**
     *
     * @return the points that every visitor keeps, so then they can send a message to the game
     * so this can update the score
     */
    public int getPts(){
        return pts;
    }

    /**
     * this metods, make a diferent action depending on the type of visitors, in bonnus visitors
     * it will trigger the bonuses
     * in hittable visitors it will plus score to the game
     * @param game
     */
    public void makeTheMagic(Game game) {
    }
}
