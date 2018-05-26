package testLogic.testBonus;

import controller.Game;
import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testDropTargetBonus {
    private Bonus dropTargetBonus;
    private Game game;

    @Before
    public void setUp() {
        dropTargetBonus = new DropTargetBonus();
        game=new Game();
    }

    @Test
    public void testTimesTriggered() {
        assertTrue(dropTargetBonus.timesTriggered()==0);
        dropTargetBonus.trigger(game);
        assertTrue(dropTargetBonus.timesTriggered()==1);
    }
}
