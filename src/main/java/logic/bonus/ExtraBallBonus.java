package logic.bonus;

import logic.controller.Game;
/**
 * this class extendes the abstracTarget but this metods are unique to this class
 *
 * @author David de la puente
 */
public class ExtraBallBonus extends AbstractBonus implements Bonus{

    /**
     * this is the constructor, set the variables
     * timesTrigered to 0
     */
    public ExtraBallBonus(){
        this.timesTriggered=0;
    }

    /**
     * this metod define the action of trigger an ExtraBall bonus, adding a ball
     * to the game
     * @param game the game logic.controller object
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered+=1;
        game.addBall();
    }
}
