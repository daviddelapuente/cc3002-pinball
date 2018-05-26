package testController;

import controller.IGame;
import controller.NullGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class testNullGame {
    IGame nullGame;

    @Before
    public void setUp() {
        nullGame=new NullGame();
    }

    @Test
    public void testNullMetods(){
        assertEquals(null,nullGame.getTableName());
        assertFalse(nullGame.isPlayableTable());
    }
}
