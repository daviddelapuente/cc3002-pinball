package testLogic.testBonus;

import logic.bonus.AbstractBonus;
import logic.controller.Game;
import logic.bonus.Bonus;
import logic.bonus.JackPotBonus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testJackPotBonus {
    private Bonus jackPotBonus;
    private Game game;
    private Bonus abs;
    @Before
    public void setUp() {
        jackPotBonus = new JackPotBonus();
        game=new Game();
        abs=new AbstractBonus();
    }

    @Test
    public void testTimesTriggered() {
        assertTrue(jackPotBonus.timesTriggered()==0);
        jackPotBonus.trigger(game);
        assertTrue(jackPotBonus.timesTriggered()==1);
    }

    //este test solo lo puse porque por alguna razon el coverage no me contaba al abstractBonus
    @Test
    public void testAbstractBonus(){
        assertEquals(0,abs.timesTriggered());
        abs.trigger(game);
        assertEquals(0,abs.timesTriggered());
    }

}
