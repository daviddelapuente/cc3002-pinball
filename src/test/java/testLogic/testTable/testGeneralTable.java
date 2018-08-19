package testLogic.testTable;

import gameLogic.logic.table.NullGameTable;
import gameLogic.logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class testGeneralTable {
    Table table;
    @Before
    public void setUp() {
        table = new NullGameTable();
    }

    @Test
    public void testGeters(){
        assertEquals("",table.getTableName());
        assertEquals(0,table.getNumberOfDropTargets());
        assertEquals(0,table.getCurrentlyDroppedDropTargets());
        assertEquals(new ArrayList<>(),table.getBumpers());
        assertEquals(new ArrayList<>(),table.getTargets());
    }

    @Test
    public void testIsPlayable(){
        assertFalse(table.isPlayableTable());
        table.makePlayable();
        assertFalse(table.isPlayableTable());
    }

    @Test
    public void testIncreseDroppedTargets(){
        table.resetDropTargets();
        table.upgradeAllBumpers();
        assertEquals(0,table.getCurrentlyDroppedDropTargets());
        table.increseDroppedDropTargets();
        assertEquals(0,table.getCurrentlyDroppedDropTargets());
    }


}
