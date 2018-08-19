package gui;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import gameLogic.logic.gameelements.target.Target;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DropTargetComponent extends Component{
    Target t;
    boolean check=true;

    public DropTargetComponent(Target t){
        this.t=t;
    }

    public void hit(Entity e, PinballFactory f, ArrayList a){
        Entity fakeTarget=f.newFakeTarget(e.getX(),e.getY(),Color.BLACK);
        a.remove(e);
        a.add(fakeTarget);
        e.getWorld().addEntity(fakeTarget);
        e.getWorld().removeEntity(e);
        t.hit();
    }
}
