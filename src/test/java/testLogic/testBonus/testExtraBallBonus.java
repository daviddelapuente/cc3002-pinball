package testLogic.testBonus;

import controller.Game;
import logic.bonus.Bonus;
import logic.bonus.ExtraBallBonus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testExtraBallBonus {
    private Bonus extraBallBonus;
    private Game game;

    @Before
    public void setUp() {
        extraBallBonus = new ExtraBallBonus();
        game=new Game();
    }

    @Test
    public void testTimesTriggered() {
        assertTrue(extraBallBonus.timesTriggered()==0);
        extraBallBonus.trigger(game);
        assertTrue(extraBallBonus.timesTriggered()==1);
    }
}
