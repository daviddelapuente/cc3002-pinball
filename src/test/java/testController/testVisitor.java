package testController;

import gameLogic.logic.controller.visitor.Visitor;
import gameLogic.logic.gameelements.bumper.KickerBumper;
import gameLogic.logic.gameelements.bumper.PopBumper;
import gameLogic.logic.gameelements.target.DropTarget;
import gameLogic.logic.gameelements.target.SpotTarget;
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
}
