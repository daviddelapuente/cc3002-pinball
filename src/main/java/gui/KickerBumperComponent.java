package gui;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import gameLogic.logic.gameelements.bumper.Bumper;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class KickerBumperComponent extends Component {
    Bumper b;
    boolean check=true;

    public KickerBumperComponent(Bumper b){
        this.b=b;
    }

    public void hit(Entity e){
        b.hit();
        if (b.isUpgraded()&&check){
            this.upgrade(e);
        }
    }

    public void upgrade(Entity e){
        if(check) {
            e.setView(new Circle(25, Color.RED));
            check = false;
        }
    }
}
