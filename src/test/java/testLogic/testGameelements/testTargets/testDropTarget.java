package testLogic.testGameelements.testTargets;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testDropTarget extends testTargets{
    @Before
    public void setUp() {
        this.target = new DropTarget();
    }

    @Test
    public void testGetScore() {
        assertTrue(this.target.getScore()==100);
    }
}