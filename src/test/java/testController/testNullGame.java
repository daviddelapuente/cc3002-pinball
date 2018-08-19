package testController;

import logic.controller.Game;
import logic.controller.nullGame;
import logic.table.GameTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class testNullGame {
    private nullGame game;
    private Table table;

    @Before
    public void setUp() {
        game=new nullGame();
        table =new GameTable("pinball",10,0.5,5,5,0);
    }

    @Test
    public void testNull(){
        assertFalse(game.isPlayableTable());
        game.setGameTable(table);
        assertFalse(game.isPlayableTable());
        assertEquals(null,game.getTableName());
        assertEquals(null,game.getCurrentTable());
        assertEquals(null,game.getBumpers());
        assertEquals(null,game.getTargets());
        assertEquals(null,game.getDropTargetBonus());
        assertEquals(null,game.getExtraBallBonus());
        assertEquals(null,game.getJackPotBonus());

        assertEquals(0,game.getCurrentScore());
        assertEquals(0,game.getAvailableBalls());
        game.dropBall();
        assertEquals(0,game.getAvailableBalls());
        game.addBall();
        assertEquals(0,game.getAvailableBalls());

        assertFalse(game.gameOver());
        game.triggerDropTargetBonus();
        game.triggerGetExtraBallBonus();
        game.triggerJackPotBonus();
        assertEquals(0,game.getAvailableBalls());
        assertEquals(0,game.getCurrentScore());
        game.plusScore(100);
        assertEquals(0,game.getCurrentScore());

        assertEquals(0,game.getPopBumpers());
        assertEquals(0,game.getKickerBumpers());
        assertEquals(0,game.getSpotTargets());
        assertEquals(0,game.getDropTargets());

    }
}
