package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.bumper.Bumper;

public class BumperComponent extends Component {
    Bumper b;
    public BumperComponent(Bumper b){
        this.b=b;
    }

    public void hit(){
        b.hit();
    }

}
