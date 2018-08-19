package logic.bonus;

import logic.controller.Game;
/**
 * this class extendes the abstracTarget but this metods are unique to this class
 *
 * @author David de la puente
 */
public class JackPotBonus extends AbstractBonus implements Bonus {
    /**
     * this is the constructor, set the variables
     * timesTrigered to 0
     */
    public JackPotBonus(){
        this.timesTriggered=0;
    }

    /**
     * this metod define the action of trigger a JackPotBonus, sending
     * a message to the game, whit the new score
     * @param game the game logic.controller object
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered+=1;
        setChanged();
        game.plusScore(100000);
    }
}
