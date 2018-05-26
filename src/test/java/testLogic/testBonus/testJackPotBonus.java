package testLogic.testBonus;

import controller.Game;
import logic.bonus.Bonus;
import logic.bonus.JackPotBonus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testJackPotBonus {
    private Bonus jackPotBonus;
    private Game game;

    @Before
    public void setUp() {
        jackPotBonus = new JackPotBonus();
        game=new Game();
    }

    @Test
    public void testTimesTriggered() {
        assertTrue(jackPotBonus.timesTriggered()==0);
        jackPotBonus.trigger(game);
        assertTrue(jackPotBonus.timesTriggered()==1);
    }
}
