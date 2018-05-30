package testController;

import controller.IGame;
import controller.NullGame;
import logic.table.GameTable;
import logic.table.NullGameTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testNullGame {
    IGame nullGame;

    @Before
    public void setUp() {
        nullGame=new NullGame();
    }

    @Test
    public void testNullMetods(){
        assertFalse(nullGame.isPlayableTable());
        Table table=new GameTable("pin",5,1,5,5,0);
        nullGame.setGameTable(table);
        assertEquals(null,nullGame.getTableName());
        assertTrue(nullGame.getCurrentTable() instanceof NullGameTable);
        assertEquals(null,nullGame.getBumpers());
        assertEquals(null,nullGame.getTargets());
        assertEquals(null,nullGame.getDropTargetBonus());
        assertEquals(null,nullGame.getExtraBallBonus());
        assertEquals(null,nullGame.getJackPotBonus());
        assertEquals(0,nullGame.getAvailableBalls());
        assertEquals(0,nullGame.getCurrentScore());
        nullGame.addBall();
        assertEquals(0,nullGame.getAvailableBalls());
        nullGame.dropBall();
        assertEquals(0,nullGame.getAvailableBalls());
        assertFalse(nullGame.gameOver());
        nullGame.triggerGetExtraBallBonus();
        nullGame.triggerDropTargetBonus();
        nullGame.triggerJackPotBonus();
        assertEquals(0,nullGame.getCurrentScore());
        assertEquals(0,nullGame.getAvailableBalls());


    }
}
