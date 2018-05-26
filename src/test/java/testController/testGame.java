package testController;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class testGame {
    private Game game;
    private Table table;

    @Before
    public void setUp() {
        game=new Game();
        table =new GameTable("pinball",10,0.5,5,5);
    }

    @Test
    public void  testIsPlayableTable(){
        assertFalse(game.isPlayableTable());

        // probamos que al pasarle una null table, no se puede jugar
        Table nullTable=new NullGameTable();
        game.setGameTable(nullTable);
        assertFalse(game.isPlayableTable());

        // probamos que al pasarle una table buena se puede jugar
        game.setGameTable(table);
        assertTrue(game.isPlayableTable());
    }

    @Test
    public void  testGeters(){
        game.setGameTable(table);
        assertEquals("pinball",game.getTableName());
        assertEquals(table,game.getCurrentTable());
    }

    @Test
    public void  testHitBumpers(){
        game.setGameTable(table);
        assertEquals(game.getCurrentScore(),0);

        //the first bumper is a kicker
        game.getCurrentTable().getBumpers().get(0).hit();
        assertEquals(500,game.getCurrentScore());

        //the second is a pop
        game.getCurrentTable().getBumpers().get(1).hit();
        assertEquals(600,game.getCurrentScore());
    }

    @Test
    public void testBumpersUpgrade(){
        game.setGameTable(table);
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit();
        }
        assertEquals(2500,game.getCurrentScore());

        //now it should be upgrade
        game.getCurrentTable().getBumpers().get(0).hit();
        assertEquals(3500,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit();
        assertEquals(4500,game.getCurrentScore());

        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit();
        }
        assertEquals(4800,game.getCurrentScore());

        //now it should be upgrade
        game.getCurrentTable().getBumpers().get(1).hit();
        assertEquals(5100,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(1).hit();
        assertEquals(5400,game.getCurrentScore());

        //now we should reset the bumpers
        game.getCurrentTable().getBumpers().get(0).downgrade();
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit();
        }
        assertEquals(7900,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit();

        assertEquals(8900,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit();
        assertEquals(9900,game.getCurrentScore());

        game.getCurrentTable().getBumpers().get(1).downgrade();
        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit();
        }
        assertEquals(10200,game.getCurrentScore());

        game.getCurrentTable().getBumpers().get(1).hit();
        assertEquals(10500,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(1).hit();
        assertEquals(10800,game.getCurrentScore());
    }

    @Test
    public void testTargetBonus(){
        game.setGameTable(table);
        assertEquals(game.getCurrentScore(),0);

        //the first target is a spot
        game.getCurrentTable().getTargets().get(0).hit();
        assertEquals(100000,game.getCurrentScore());

        //now we check tha the Targets are desactivated
        game.getCurrentTable().getTargets().get(0).hit();
        assertFalse(game.getCurrentTable().getTargets().get(0).isActive());
        assertEquals(100000,game.getCurrentScore());

        //the second is a drop
        game.getCurrentTable().getTargets().get(5).hit();
        assertEquals(100100,game.getCurrentScore());
        assertEquals(4,game.getAvailableBalls());

        //now we check tha the Targets are desactivated
        game.getCurrentTable().getTargets().get(5).hit();
        assertFalse(game.getCurrentTable().getTargets().get(0).isActive());
        assertEquals(100100,game.getCurrentScore());
        assertEquals(4,game.getAvailableBalls());


        //now we check the DropTargetBonus
        for(int i=6;i<9;i++){
            game.getCurrentTable().getTargets().get(i).hit();
            assertEquals(100100+100*(i-5),game.getCurrentScore());
            assertEquals(4+(i-5),game.getAvailableBalls());
        }
        game.getCurrentTable().getTargets().get(9).hit();
        assertEquals(8,game.getAvailableBalls());
        assertEquals(1100500,game.getCurrentScore());

        //now we test thar al the bumpers are upgrade
        for(Bumper b : game.getCurrentTable().getBumpers()){
            assertTrue(b.isUpgraded());
        }
    }

    @Test
    public void testBumperBonus(){
        game.setGameTable(table);
        assertEquals(0,game.getCurrentScore());

        //the first bumper is a kicker
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit();
        }
        //it should be upgrade, therefore the extraBallBonus was triggered
        //and the random seed, make it hapen
        assertEquals(4,game.getAvailableBalls());

        //the second bumper is a pop
        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit();
        }
        //it should be upgrade, therefore the extraBallBonus was triggered
        assertEquals(5,game.getAvailableBalls());
    }
}
