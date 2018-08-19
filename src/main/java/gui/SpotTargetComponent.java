package gui;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import gameLogic.logic.gameelements.target.Target;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SpotTargetComponent extends Component{
    Target t;
    boolean check=true;

    public SpotTargetComponent(Target t){
        this.t=t;
    }

    public void hit(Entity e, PinballFactory f, ArrayList a){
        Entity fakeTarget=f.newFakeTarget(e.getX()+40,e.getY(), Color.BROWN);
        a.remove(e);
        a.add(fakeTarget);
        e.getWorld().addEntity(fakeTarget);
        e.getWorld().removeEntity(e);
        t.hit();
    }
}
