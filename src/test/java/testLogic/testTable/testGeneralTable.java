package testLogic.testTable;

import logic.table.NullGameTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(null,table.getTableName());
        assertEquals(0,table.getNumberOfDropTargets());
        assertEquals(0,table.getCurrentlyDroppedDropTargets());
        assertEquals(null,table.getBumpers());
        assertEquals(null,table.getTargets());
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
