package logic.bonus;

import controller.Game;
/**
 * this class extendes the abstracTarget but this metods are unique to this class
 *
 * @author David de la puente
 */
public class DropTargetBonus extends AbstractBonus implements Bonus{

    /**
     * this is the constructor, set the variables
     * timesTrigered to 0
     */
    public DropTargetBonus(){
        this.timesTriggered=0;
    }

    /**
     * this metod define the action of trigger a dropTargetBonus, upgrading
     * all the bumpers and sending a message to the game whit the new score
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered+=1;
        game.getCurrentTable().upgradeAllBumpers();
        setChanged();
        game.plusScore(1000000);
    }
}
