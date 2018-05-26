package testLogic.testGameelements.testTargets;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testDropTarget {
    private Target dropTarget;

    @Before
    public void setUp() {
        dropTarget = new DropTarget();
    }

    @Test
    public void testGetScore() {
        assertTrue(dropTarget.getScore()==100);
    }

    @Test
    public void testIsActive() {
        assertTrue(dropTarget.isActive());
        dropTarget.hit();
        assertFalse(dropTarget.isActive());
        dropTarget.hit();
        assertFalse(dropTarget.isActive());
        dropTarget.reset();
        assertTrue(dropTarget.isActive());
        dropTarget.reset();
        assertTrue(dropTarget.isActive());
    }
}
