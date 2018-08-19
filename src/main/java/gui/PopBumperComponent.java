package gui;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.bumper.Bumper;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class PopBumperComponent extends Component {
    Bumper b;
    boolean check=true;
    public PopBumperComponent(Bumper b){
        this.b=b;
    }

    public void hit(Entity e){
        b.hit();
        if (b.isUpgraded()&&check){
            upgrade(e);
        }
    }

    public void upgrade(Entity e){
        if(check) {
            e.setView(new Circle(25,Color.BLUE));
            check = false;
        }
    }
}
