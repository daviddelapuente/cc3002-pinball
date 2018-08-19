package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;

import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class PinballFactory {
    private double barWidht=150;
    private double barHeight=10;
    private Polygon p1=new Polygon();
    private Polygon p2=new Polygon();

    private PhysicsComponent leftBarPC;

    public Entity newTriangleLeft(double x1,double y1, double x2, double y2, double x3, double y3){
        p1.getPoints().addAll(new Double[]{
                x1, y1,
                x2, y2,
                x3, y3 });

        Entity triangle =Entities.builder()
                .viewFromNodeWithBBox(p1)
                .type(EntityType.TRIANGLE)
                .build();

        return triangle;
    }

    public Entity newTriangleRight(double x1, double y1, double x2, double y2, double x3, double y3) {
        p2.getPoints().addAll(new Double[]{
                x1, y1,
                x2, y2,
                x3, y3 });
        Entity triangle =Entities.builder()
                .viewFromNode(p2)
                .build();
        return triangle;
    }

    public Entity newBall(double x, double y){
        PhysicsComponent ballPhysics = new PhysicsComponent();
        ballPhysics.setBodyType(BodyType.DYNAMIC);
        ballPhysics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity ball = Entities.builder()
                .at(x, y)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNodeWithBBox(new Circle(10,Color.BLACK))
                .type(EntityType.BALL)
                .with(ballPhysics, new CollidableComponent(true))
                .build();

        return ball;
    }

    public Entity newBar(double x, double y,double a) {
        leftBarPC = new PhysicsComponent();
        leftBarPC.setBodyType(BodyType.KINEMATIC);
        leftBarPC.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity bar = Entities.builder()
                .at(x, y)
                .rotate(a)
                .bbox(new HitBox("Bar", BoundingShape.box(barWidht,barHeight)))
                .viewFromNode(new Rectangle(barWidht, barHeight, Color.BLACK))
                .with(leftBarPC,new CollidableComponent(true))
                .build();
        bar.setX(x);
        bar.setY(y);
        return bar;
    }

    public Entity newPopBumper(double x, double y){
        PhysicsComponent bumperPC = new PhysicsComponent();
        bumperPC.setBodyType(BodyType.STATIC);
        bumperPC.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity bumper = Entities.builder()
                .at(x,y)
                .bbox(new HitBox("Bumper",BoundingShape.circle(20)))
                .viewFromNodeWithBBox(new Circle(20,Color.LIGHTBLUE))
                .with(bumperPC,new CollidableComponent(true))
                .type(EntityType.POPBUMPER)
                .build();
        return bumper;
    }

    public Entity newKickerBumper(double x, double y){
        PhysicsComponent bumperPC = new PhysicsComponent();
        bumperPC.setBodyType(BodyType.STATIC);
        bumperPC.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity bumper = Entities.builder()
                .at(x,y)
                .bbox(new HitBox("Bumper",BoundingShape.circle(20)))
                .viewFromNodeWithBBox(new Circle(20,Color.PINK))
                .with(bumperPC,new CollidableComponent(true))
                .type(EntityType.KICKERBUMPER)
                .build();
        return bumper;
    }

    public Entity newRectangle(double x,double y,double a,double width,double height,Color c){
        PhysicsComponent rectanglePhysic=new PhysicsComponent();
        rectanglePhysic.setBodyType(BodyType.STATIC);
        rectanglePhysic.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity rectangle=Entities.builder()
                .at(x,y)
                .rotate(a)
                .bbox(new HitBox("rectangle", BoundingShape.box(width,height)))
                .viewFromNode(new Rectangle(width,height,c))
                .with(rectanglePhysic,new CollidableComponent(true))
                .build();
        return rectangle;
    }

    public Entity newFakeRectangle(double x,double y,double a,double width,double height,Color c){
        Entity rectangle=Entities.builder()
                .at(x,y)
                .rotate(a)
                .viewFromNode(new Rectangle(width,height,c))
                .build();
        return rectangle;
    }

    public Entity newBackground(double x, double y){
        Entity bg = Entities.builder()
                .at(x,y)
                .viewFromNode(new ImageView("imgs/pinbalBG.png"))
                .build();
        return bg;
    }

    public double getBarWidht(){
        return this.barWidht;
    }

    public double getBarHeight(){
        return this.barHeight;
    }


    public Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(10);
        walls.setType(EntityType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public Entity newFakeTarget(double x, double y,Color c) {
        Entity fakeTarget = Entities.builder()
                .at(x,y)
                .viewFromNodeWithBBox(new Rectangle(10,20,c))
                .build();
        return fakeTarget;
    }

    public Entity newDropTarget(double x, double y) {
        PhysicsComponent targetPC = new PhysicsComponent();
        targetPC.setBodyType(BodyType.STATIC);
        targetPC.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity target = Entities.builder()
                .at(x,y)
                .bbox(new HitBox("Bumper",BoundingShape.box(50,20)))
                .viewFromNodeWithBBox(new Rectangle(50,20,Color.BLACK))
                .with(targetPC,new CollidableComponent(true))
                .type(EntityType.DROPTARGET)
                .build();
        return target;
    }

    public Entity newSpotTarget(double x, double y) {
        PhysicsComponent targetPC = new PhysicsComponent();
        targetPC.setBodyType(BodyType.STATIC);
        targetPC.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));

        Entity target = Entities.builder()
                .at(x,y)
                .bbox(new HitBox("Bumper",BoundingShape.box(50,20)))
                .viewFromNodeWithBBox(new Rectangle(50,20,Color.BROWN))
                .with(targetPC,new CollidableComponent(true))
                .type(EntityType.SPOTTARGET)
                .build();
        return target;
    }
}
