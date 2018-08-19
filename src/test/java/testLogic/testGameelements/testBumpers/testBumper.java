package testLogic.testGameelements.testBumpers;
import gameLogic.logic.gameelements.bumper.Bumper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class testBumper {
    protected Bumper bumper;

    @Test
    public void testUpgrades() {
        assertFalse(bumper.isUpgraded());
        bumper.upgrade();
        assertTrue(bumper.isUpgraded());
        bumper.upgrade();
        assertTrue(bumper.isUpgraded());

        bumper.downgrade();
        assertFalse(bumper.isUpgraded());
        bumper.downgrade();
        assertFalse(bumper.isUpgraded());
    }
}
