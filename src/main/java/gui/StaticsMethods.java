package gui;

import com.almasb.fxgl.entity.components.RotationComponent;

public class StaticsMethods {
    public static String parseDoubleProperty(String s){
        String a=s.substring(22,28);
        return a;
    }

    public static double ParseRotationComponent(RotationComponent rotationComponent) {
        return Double.parseDouble(StaticsMethods.parseDoubleProperty(rotationComponent.angleProperty().toString()));
    }
}
