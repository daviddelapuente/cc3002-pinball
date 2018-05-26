package testLogic.testTable;

import logic.table.GameTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testGameTable {
    private GameTable gameTable;
    private GameTable gameTable2;

    @Before
    public void setUp() {
        gameTable = new GameTable("pinball",10,0.5,5,5);
        gameTable2 = new GameTable("pinball2",0,1,3,0);
    }

    @Test
    public void testGetTableName() {
        String expected=gameTable.getTableName();
        assertEquals(expected,"pinball");
        assertTrue(gameTable.getNumberOfDropTargets()==5);
    }
}
