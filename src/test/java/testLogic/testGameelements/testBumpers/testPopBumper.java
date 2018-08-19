package testLogic.testGameelements.testBumpers;

import gameLogic.logic.gameelements.bumper.PopBumper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class testPopBumper extends testBumper {
    @Before
    public void setUp() {
        this.bumper = new PopBumper();
    }

    @Test
    public void testGetScore() {
        assertTrue(bumper.getScore()==100);
        assertEquals(100,bumper.hit());
        for(int i=0;i<2;i++){
            bumper.hit();
        }
        assertEquals(300,bumper.hit());
        assertEquals(300,bumper.getScore());
    }

    @Test
    public void testRemainingHitsToUpgrade() {
        assertTrue(bumper.remainingHitsToUpgrade()==3);
    }

    @Test
    public void testHit() {

        assertEquals(3,bumper.remainingHitsToUpgrade());
        assertEquals(100,bumper.getBumperMode().getScore());
        assertFalse(bumper.isUpgraded());
        for(int i=2;i>=0;i--){
            bumper.hit();
            assertTrue(bumper.remainingHitsToUpgrade()==i);
            assertEquals(i,bumper.remainingHitsToUpgrade());
        }
        assertEquals(300,bumper.getBumperMode().getScore());
        assertTrue(bumper.isUpgraded());
        assertEquals(300,bumper.getScore());
        bumper.hit();
        assertTrue(bumper.remainingHitsToUpgrade()==0);
        assertEquals(0,bumper.remainingHitsToUpgrade());

    }


}