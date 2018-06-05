package testController;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class testGame {
    private Game game;
    private Table table;

    @Before
    public void setUp() {
        game=new Game();
        table =new GameTable("pinball",10,0.5,5,5,0);
    }

    @Test
    public void  testIsPlayableTable(){
        assertFalse(game.isPlayableTable());

        Table nullTable=new NullGameTable();
        game.setGameTable(nullTable);
        assertFalse(game.isPlayableTable());

        game.setGameTable(table);
        assertTrue(game.isPlayableTable());
    }

    @Test
    public void  testGeters(){
        game.setGameTable(table);
        assertEquals("pinball",game.getTableName());
        assertEquals(table,game.getCurrentTable());
        assertEquals(5,game.getCurrentTable().getNumberOfDropTargets());
    }

    @Test
    public void testGetersSeed(){
        game.setGameTable(table);
        List<Bumper> expectedBumpers=new ArrayList<>();

        List<Bumper> actualBumpers=game.getBumpers();

        PopBumper pop=new PopBumper();
        KickerBumper kicker= new KickerBumper();

        //these are the bumpers that make the seed
        expectedBumpers.add(kicker);
        expectedBumpers.add(pop);
        expectedBumpers.add(kicker);
        expectedBumpers.add(kicker);
        expectedBumpers.add(kicker);
        expectedBumpers.add(pop);
        expectedBumpers.add(pop);
        expectedBumpers.add(kicker);
        expectedBumpers.add(kicker);
        expectedBumpers.add(kicker);

        for(int i=0;i<10;i++){
            Bumper b1=expectedBumpers.get(i);
            Bumper b2= actualBumpers.get(i);
            boolean p=(b1 instanceof PopBumper)==(b2 instanceof PopBumper);
            assertTrue(p);
        }

        for (int i=0;i<5;i++){
            assertTrue(game.getTargets().get(i) instanceof SpotTarget);
            assertTrue(game.getTargets().get(i+5) instanceof DropTarget);
        }

    }

    @Test
    public void  testHitBumpers(){
        game.setGameTable(table);
        assertEquals(game.getCurrentScore(),0);

        //the first bumper is a kicker
        game.getCurrentTable().getBumpers().get(0).hit(900000);
        assertEquals(500,game.getCurrentScore());

        //the second is a pop
        game.getCurrentTable().getBumpers().get(1).hit(900000);
        assertEquals(600,game.getCurrentScore());
    }

    @Test
    public void testBumpersUpgrade(){
        game.setGameTable(table);
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit(900000);
        }
        assertEquals(2500,game.getCurrentScore());

        //now it should be upgrade
        game.getCurrentTable().getBumpers().get(0).hit(900000);
        assertEquals(3500,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit(900000);
        assertEquals(4500,game.getCurrentScore());

        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit(900000);
        }
        assertEquals(4800,game.getCurrentScore());

        //now it should be upgrade
        game.getCurrentTable().getBumpers().get(1).hit(900000);
        assertEquals(5100,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(1).hit(900000);
        assertEquals(5400,game.getCurrentScore());

        //now we should reset the bumpers
        game.getCurrentTable().getBumpers().get(0).downgrade();
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit(900000);
        }
        assertEquals(7900,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit(900000);

        assertEquals(8900,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(0).hit(900000);
        assertEquals(9900,game.getCurrentScore());

        game.getCurrentTable().getBumpers().get(1).downgrade();
        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit(900000);
        }
        assertEquals(10200,game.getCurrentScore());

        game.getCurrentTable().getBumpers().get(1).hit(900000);
        assertEquals(10500,game.getCurrentScore());
        game.getCurrentTable().getBumpers().get(1).hit(900000);
        assertEquals(10800,game.getCurrentScore());
    }

    @Test
    public void testTargetBonus(){
        game.setGameTable(table);
        assertEquals(game.getCurrentScore(),0);
        assertEquals(0,game.getJackPotBonus().timesTriggered());
        //the first target is a spot
        game.getCurrentTable().getTargets().get(0).hit(900000);
        assertEquals(100000,game.getCurrentScore());
        assertEquals(1,game.getJackPotBonus().timesTriggered());

        //now we check tha the Targets are desactivated
        game.getCurrentTable().getTargets().get(0).hit(900000);
        assertFalse(game.getCurrentTable().getTargets().get(0).isActive());
        assertEquals(100000,game.getCurrentScore());
        assertEquals(1,game.getJackPotBonus().timesTriggered());

        //the second is a drop
        assertEquals(0,game.getExtraBallBonus().timesTriggered());
        game.getCurrentTable().getTargets().get(5).hit(900000);
        assertEquals(100100,game.getCurrentScore());
        assertEquals(4,game.getAvailableBalls());
        assertEquals(1,game.getExtraBallBonus().timesTriggered());

        //now we check tha the Targets are desactivated
        game.getCurrentTable().getTargets().get(5).hit(900000);
        assertFalse(game.getCurrentTable().getTargets().get(0).isActive());
        assertEquals(100100,game.getCurrentScore());
        assertEquals(4,game.getAvailableBalls());
        assertEquals(1,game.getExtraBallBonus().timesTriggered());


        //now we check the DropTargetBonus
        assertEquals(0,game.getDropTargetBonus().timesTriggered());
        for(int i=6;i<9;i++){
            game.getCurrentTable().getTargets().get(i).hit(900000);
            assertEquals(100100+100*(i-5),game.getCurrentScore());
            assertEquals(4+(i-5),game.getAvailableBalls());
        }
        game.getCurrentTable().getTargets().get(9).hit(900000);
        assertEquals(8,game.getAvailableBalls());
        assertEquals(1100500,game.getCurrentScore());
        assertEquals(1,game.getDropTargetBonus().timesTriggered());
        assertEquals(5,game.getExtraBallBonus().timesTriggered());

        //now we test thar al the bumpers are upgrade
        for(Bumper b : game.getCurrentTable().getBumpers()){
            assertTrue(b.isUpgraded());
        }

        game.getCurrentTable().resetDropTargets();
        for(int i=5;i<10;i++){
            assertTrue(game.getCurrentTable().getTargets().get(i).isActive());
        }
    }

    @Test
    public void testBumperBonus(){
        game.setGameTable(table);
        assertEquals(0,game.getCurrentScore());

        //the first bumper is a kicker
        for(int i=0;i<5;i++){
            game.getCurrentTable().getBumpers().get(0).hit(900000);
        }
        //it should be upgrade, therefore the extraBallBonus was triggered
        //and the random seed, make it hapen
        assertEquals(4,game.getAvailableBalls());

        //the second bumper is a pop
        for(int i=0;i<3;i++){
            game.getCurrentTable().getBumpers().get(1).hit(900000);
        }
        //it should be upgrade, therefore the extraBallBonus was triggered
        assertEquals(5,game.getAvailableBalls());
    }

    @Test
    public void testBonus(){
        game.setGameTable(table);
        assertEquals(0,game.getCurrentScore());
        game.triggerDropTargetBonus();
        assertEquals(1000000,game.getCurrentScore());
        game.triggerJackPotBonus();
        assertEquals(1100000,game.getCurrentScore());
        assertEquals(3,game.getAvailableBalls());
        game.triggerGetExtraBallBonus();
        assertEquals(4,game.getAvailableBalls());
    }

    @Test
    public void gameOverTest(){
        game.setGameTable(table);
        assertFalse(game.gameOver());
        game.dropBall();
        game.dropBall();
        game.dropBall();
        assertTrue(game.gameOver());
    }

    @Test
    public void testTargetReset(){
        game.setGameTable(table);
        assertEquals(0,game.getCurrentTable().getCurrentlyDroppedDropTargets());
        game.getCurrentTable().getTargets().get(5).hit();
        game.getCurrentTable().getTargets().get(6).hit();
        assertEquals(2,game.getCurrentTable().getCurrentlyDroppedDropTargets());
        game.getCurrentTable().getTargets().get(6).reset();
        assertEquals(1,game.getCurrentTable().getCurrentlyDroppedDropTargets());

    }
}
