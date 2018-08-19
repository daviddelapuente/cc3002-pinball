package gui;

import com.almasb.fxgl.entity.component.Component;

public class BarComponent extends Component {
    private double maxAngle;
    private double actualAngle;
    private double deltaAngle;

    public void setMaxAngle(double a){
        this.maxAngle=a;
    }

    public void setActualAngle(double a){
        this.actualAngle=a;
    }

    public void setDeltaAngle(double da){
        this.deltaAngle=da;
    }
}
