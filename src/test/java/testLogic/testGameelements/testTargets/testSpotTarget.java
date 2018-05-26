package testLogic.testGameelements.testTargets;

import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testSpotTarget {
    private Target spotTarget;

    @Before
    public void setUp() {
        spotTarget = new SpotTarget();
    }

    @Test
    public void testGetScore() {
        assertTrue(spotTarget.getScore()==0);
    }

    @Test
    public void testIsActive() {
        assertTrue(spotTarget.isActive());
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
        spotTarget.reset();
        assertTrue(spotTarget.isActive());
        spotTarget.reset();
        assertTrue(spotTarget.isActive());
    }
}
