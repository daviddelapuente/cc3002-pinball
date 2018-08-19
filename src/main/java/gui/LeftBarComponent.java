package gui;

import com.almasb.fxgl.physics.PhysicsComponent;

public class LeftBarComponent extends BarComponent {
    protected PhysicsComponent barPhysics;

    public void up(){

        barPhysics.setAngularVelocity(-10);
    }

    public void stop(){
        barPhysics.setAngularVelocity(0);
    }

    public void down(){
        barPhysics.setAngularVelocity(10);
    }
}
