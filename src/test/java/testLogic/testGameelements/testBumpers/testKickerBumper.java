package testLogic.testGameelements.testBumpers;

import gameLogic.logic.gameelements.bumper.KickerBumper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testKickerBumper extends testBumper{
    @Before
    public void setUp() {
        bumper = new KickerBumper();
    }
    @Test
    public void testGetScore() {
        assertTrue(bumper.getScore()==500);
        assertEquals(500,bumper.hit());
        for(int i=0;i<4;i++){
            bumper.hit();
        }
        assertEquals(1000,bumper.hit());
        assertEquals(1000,bumper.getScore());

    }

    @Test
    public void testRemainingHitsToUpgrade() {
        assertTrue(bumper.remainingHitsToUpgrade()==5);
    }

    @Test
    public void testHit() {
        assertEquals(5,bumper.remainingHitsToUpgrade());
        assertEquals(500,bumper.getBumperMode().getScore());
        assertFalse(bumper.isUpgraded());
        for(int i=4;i>=0;i--){
            bumper.hit();
            assertTrue(bumper.remainingHitsToUpgrade()==i);
            assertEquals(i,bumper.remainingHitsToUpgrade());
        }
        assertEquals(1000,bumper.getBumperMode().getScore());
        assertTrue(bumper.isUpgraded());
        assertEquals(1000,bumper.getScore());
        bumper.hit();
        assertTrue(bumper.remainingHitsToUpgrade()==0);
        assertEquals(0,bumper.remainingHitsToUpgrade());

    }

    @Test
    public void testBumperModes(){
        assertEquals(500,bumper.getBumperMode().getScore());
    }

    @Test
    public void testUpgrades(){
        assertFalse(bumper.isUpgraded());
        bumper.upgrade();
        assertTrue(bumper.isUpgraded());
        bumper.downgrade();
        assertFalse(bumper.isUpgraded());
    }

}
