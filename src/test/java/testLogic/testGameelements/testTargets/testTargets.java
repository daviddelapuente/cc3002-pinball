package testLogic.testGameelements.testTargets;

import logic.gameelements.target.Target;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class testTargets{
    protected Target target;
    @Test
    public void testIsActive() {
        assertTrue(target.isActive());
        target.hit();
        assertFalse(target.isActive());
        target.hit();
        assertFalse(target.isActive());
        target.reset();
        assertTrue(target.isActive());
        target.reset();
        assertTrue(target.isActive());
    }
}
