package testController;

import logic.controller.Game;
import logic.controller.visitor.Visitor;
import logic.controller.visitor.VisitorResetSpotTarget;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.GameTable;
import logic.table.Table;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testVisitor {
    Visitor visitor =new Visitor();


    @Test
    public void testVisits(){
        assertEquals(0,visitor.getPts());
        KickerBumper k= new KickerBumper();
        PopBumper p= new PopBumper();
        DropTarget d= new DropTarget();
        SpotTarget s= new SpotTarget();

        visitor.visitKickerBumper(k);
        visitor.visitPopBumper(p);
        visitor.visitDropTarget(d);
        visitor.visitSpotTarget(s);

        assertEquals(0,visitor.getPts());
    }

    @Test
    public void testVisitorResetSpotTarget(){
        Game g=new Game();
        Table t=new GameTable("",0,0,5,0,0);
        visitor.makeTheMagic(g);
        g.setGameTable(t);
        VisitorResetSpotTarget v= new VisitorResetSpotTarget();
        v.makeTheMagic(g);
        assertEquals(0,g.getCurrentTable().getCurrentlyDroppedSpotTargets());
    }
}
