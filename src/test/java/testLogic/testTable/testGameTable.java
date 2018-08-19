package testLogic.testTable;

import gameLogic.logic.gameelements.bumper.PopBumper;
import gameLogic.logic.gameelements.target.DropTarget;
import gameLogic.logic.gameelements.target.SpotTarget;
import gameLogic.logic.table.GameTable;
import gameLogic.logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testGameTable {
    Table gameTable;
    Table randomGameTable;

    @Before
    public void setUp() {
        gameTable =new GameTable("pinball",5,1,5,5,0);
        randomGameTable=new GameTable("pinballRandom",5,1,5,5);
    }

    @Test
    public void testGeters(){
        assertEquals("pinball",gameTable.getTableName());
    }
    @Test
    public void testIsPlayable(){
        assertFalse(gameTable.isPlayableTable());
        gameTable.makePlayable();
        assertTrue(gameTable.isPlayableTable());
    }
    @Test
    public void testSeedGeters(){
        List bumpers=gameTable.getBumpers();
        for (int i=0;i<5;i++){
            assertTrue(bumpers.get(i) instanceof PopBumper);
        }

        List targets=gameTable.getTargets();
        for(int i=0;i<5;i++){
            assertTrue(targets.get(i) instanceof SpotTarget);
        }
        for(int i=5;i<9;i++){
            assertTrue(targets.get(i) instanceof DropTarget);
        }
    }
}
