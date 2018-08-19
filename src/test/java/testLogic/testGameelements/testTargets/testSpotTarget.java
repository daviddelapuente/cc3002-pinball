package testLogic.testGameelements.testTargets;

import gameLogic.logic.gameelements.target.SpotTarget;
import gameLogic.logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testSpotTarget extends testDropTarget{
    @Before
    public void setUp() {
        this.target = new SpotTarget();
    }

    @Test
    public void testGetScore() {
        assertTrue(target.getScore()==0);
        assertEquals(0, target.hit());
        assertEquals(0,target.hit());
    }
}
