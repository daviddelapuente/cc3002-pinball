package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.*;

import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;

import logic.controller.Game;
import logic.controller.IGame;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.GameTable;
import logic.table.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Map;


//0.2 de grosor de lados dwel tablero
public class PinballApp extends GameApplication {
    private PinballFactory factory;
    private Entity leftBar;
    private Entity rightBar;


    private boolean leftbool1=false;
    private boolean leftbool2=false;
    private boolean rightbool1=false;
    private boolean rightbool2=false;

    private IGame game;

    private boolean canBallInit=false;
    private Entity ball;
    private boolean ballNull=true;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(700);
        gameSettings.setTitle("David's Pinball");
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("NewBall") {
            @Override
            protected void onActionEnd() {
                if(canBallInit){
                initBall();
                canBallInit=false;
                getGameState().setValue("msg","");
                ballNull=false;
                }
            }
        },KeyCode.B);

        input.addAction(new UserAction("upLeft") {
            @Override
            protected void onAction() {
                if(StaticsMethods.ParseRotationComponent(leftBar.getRotationComponent())>=-30){
                    leftBarComponent.barPhysics.setAngularVelocity(-10);
                }else{
                    leftBarComponent.barPhysics.setAngularVelocity(0);
                }
            }

            @Override
            protected void onActionEnd(){
                leftbool1=true;
            }
        },KeyCode.A);

        input.addAction(new UserAction("upRight") {
            @Override
            protected void onAction() {
                if(StaticsMethods.ParseRotationComponent(rightBar.getRotationComponent())<=30){
                    rightBarComponent.barPhysics.setAngularVelocity(10);
                }else{
                    rightBarComponent.barPhysics.setAngularVelocity(0);
                }
            }

            @Override
            protected void onActionEnd(){rightbool1=true;
            }
        },KeyCode.D);


    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("stageColor", Color.RED);
        vars.put("score", 0);
        vars.put("balls",0);
        vars.put("msg","Press create to generate table");
    }

    @Override
    protected void initGame() {
        factory = new PinballFactory();


        initLeftBar();
        initRightBar();

        initWalls();
        initT();
        initRectangles();
        game=new Game();
    }

    private Entity rleft;
    private Entity rleft2;
    private Entity rleft3;
    private Entity rright;
    private Entity rright2;
    private Entity rright3;

    private Entity displayRectangle;

    private void initRectangles() {
        rleft=factory.newRectangle(-60,542.5,45,282,50, Color.BLACK);
        rleft2=factory.newFakeRectangle(0,645,0,205,55,Color.BLACK);
        rleft3=factory.newFakeRectangle(0,695,0,205,55,Color.BLACK);
        getGameWorld().addEntity(rleft3);
        getGameWorld().addEntity(rleft);
        getGameWorld().addEntity(rleft2);

        rright = factory.newRectangle(378,542.5,-45,282,50,Color.BLACK);
        rright2= factory.newFakeRectangle(400,645,0,205,55,Color.BLACK);
        rright3=factory.newFakeRectangle(400,695,0,205,55,Color.BLACK);
        getGameWorld().addEntity(rright);
        getGameWorld().addEntity(rright2);
        getGameWorld().addEntity(rright3);

        displayRectangle = factory.newRectangle(595,0,0,200,750,Color.BLACK);
        getGameWorld().addEntity(displayRectangle);



    }

    private void initWalls() {
        getGameWorld().addEntity(factory.newWalls());
    }



    private Entity tLeft;
    private Entity tRight;

    public void initT(){
        tLeft=factory.newTriangleLeft(0.0,650.0,200.0,650.0,0.0,450.0);
        tRight=factory.newTriangleRight(400.0,650.0,600.0,650.0,600.0,450.0);
        getGameWorld().addEntity(tLeft);
        getGameWorld().addEntity(tRight);
    }

    public void initBall() {
        ball=factory.newBall(200,400);
        getGameWorld().addEntity(ball);
    }
    //private ArrayList<Entity> viewTargets=new ArrayList<>();
    private ArrayList<Entity> viewDropTargets=new ArrayList<>();
    private ArrayList<Entity> viewSpotTargets=new ArrayList<>();

    public void destroyTargets(){
        if(!(game.getDropTargets()==0)){
            for(Entity e:viewDropTargets){
                e.removeFromWorld();
            }
        }
        viewDropTargets.clear();

        if(!(game.getSpotTargets()==0)){
            for(Entity e:viewSpotTargets){
                e.removeFromWorld();
            }
        }

        viewSpotTargets.clear();
    }

    public void initDropTargets(){
        int dropTargetsInTable=game.getDropTargets();
        double dp=450*1.0/(1+dropTargetsInTable);
        int p=1;
        for (Target t:game.getTargets() ) {
            if (t instanceof DropTarget) {
                Entity tar = factory.newDropTarget(20, dp * p);
                DropTargetComponent dropTargetComponent = new DropTargetComponent(t);
                tar.addComponent(dropTargetComponent);
                viewDropTargets.add(tar);
                p++;
            }
        }
        for (Entity t: viewDropTargets){
            getGameWorld().addEntity(t);
        }
    }

    public void initSpotTargets(){
        int spotTargetsInTable=game.getSpotTargets();
        double ds=450*1.0/(1+spotTargetsInTable);
        int s=1;

        for (Target t:game.getTargets() ){
            if(t instanceof SpotTarget){
                Entity tar=factory.newSpotTarget(530,ds*s);
                SpotTargetComponent SpotTargetComponent= new SpotTargetComponent(t);
                tar.addComponent(SpotTargetComponent);
                viewSpotTargets.add(tar);
                s++;
            }
        }

        for (Entity t: viewSpotTargets){
            getGameWorld().addEntity(t);
        }
    }

    private ArrayList<Entity> viewPopBumpers=new ArrayList<>();
    private ArrayList<Entity> viewKickerBumpers=new ArrayList<>();

    public void destroyBumpers(){
        if(!(game.getPopBumpers()==0)){
            for(Entity e:viewPopBumpers){
                e.removeFromWorld();
            }

            viewPopBumpers.clear();
        }

        if(!(game.getKickerBumpers()==0)){
            for(Entity e:viewKickerBumpers){
                e.removeFromWorld();
            }

            viewKickerBumpers.clear();
        }
    }


    public void initPopBumpers(){
        int popBumpersInTable=game.getPopBumpers();

        double dp=600*1.0/(1+popBumpersInTable);
        int p=1;

        for (Bumper b:game.getBumpers() ){
            if(b instanceof PopBumper){
                Entity bump=factory.newPopBumper(dp*p,85);
                PopBumperComponent PopBumperComponent= new PopBumperComponent(b);
                bump.addComponent(PopBumperComponent);
                viewPopBumpers.add(bump);
                p++;
            }
        }

        for (Entity pb: viewPopBumpers){
            getGameWorld().addEntity(pb);
        }
    }

    public void initKickerBumpers(){
        int kickerBumpersInTable=game.getKickerBumpers();

        double dk=600*1.0/(1+kickerBumpersInTable);
        int k=1;

        for (Bumper b:game.getBumpers() ){
            if(b instanceof KickerBumper){
                Entity bump=factory.newKickerBumper(dk*k,35);
                KickerBumperComponent KickerBumperComponent= new KickerBumperComponent(b);
                bump.addComponent(KickerBumperComponent);
                viewKickerBumpers.add(bump);
                k++;
            }
        }

        for (Entity kb: viewKickerBumpers){
            getGameWorld().addEntity(kb);
        }
    }



    private LeftBarComponent leftBarComponent;

    private void initLeftBar() {
        leftBar = factory.newBar(200-factory.getBarWidht()/2, 645,20);
        leftBar.addComponent(new LeftBarComponent());
        getGameWorld().addEntity(leftBar);
        leftBarComponent = leftBar.getComponent(LeftBarComponent.class);
        leftBarComponent.setActualAngle(0);
        leftBarComponent.setMaxAngle(-45);
        leftBarComponent.setDeltaAngle(0.1);
    }

    private RightBarComponent rightBarComponent;

    public void initRightBar(){
        rightBar=factory.newBar(400-factory.getBarWidht()/2,645,-20);
        rightBar.addComponent(new RightBarComponent());
        getGameWorld().addEntity(rightBar);
        rightBarComponent = rightBar.getComponent(RightBarComponent.class);
        rightBarComponent.setActualAngle(0);
        rightBarComponent.setMaxAngle(45);
        rightBarComponent.setDeltaAngle(0.1);
    }

    @Override
    protected void onUpdate(double tpf){
        if(StaticsMethods.ParseRotationComponent(leftBar.getRotationComponent())<-30&&leftbool1){
            leftBarComponent.barPhysics.setAngularVelocity(0);
            leftbool1=false;
            leftbool2=true;
        }
        if(leftbool2){
            leftBarComponent.barPhysics.setAngularVelocity(10);
            leftbool2=false;
        }
        if (StaticsMethods.ParseRotationComponent(leftBar.getRotationComponent())>5&&!leftbool1){
            leftBarComponent.barPhysics.setAngularVelocity(0);
        }


        if(StaticsMethods.ParseRotationComponent(rightBar.getRotationComponent())>30&&rightbool1){
            rightBarComponent.barPhysics.setAngularVelocity(0);
            rightbool1=false;
            rightbool2=true;
        }
        if(rightbool2){
            rightBarComponent.barPhysics.setAngularVelocity(-10);
            rightbool2=false;
        }
        if (StaticsMethods.ParseRotationComponent(rightBar.getRotationComponent())<-5&&!rightbool1){
            rightBarComponent.barPhysics.setAngularVelocity(0);
        }

        getGameState().setValue("score",game.getCurrentScore());
        getGameState().setValue("balls",game.getAvailableBalls());

        if(game.getCurrentTable().getDropTargets()!=0&&game.getCurrentTable().isPlayableTable()&&game.getCurrentTable().getCurrentlyDroppedDropTargets()==game.getCurrentTable().getNumberOfDropTargets()){
            game.getCurrentTable().resetDropTargets();
            for(Entity e:viewDropTargets){
                e.removeFromWorld();
            }
            viewDropTargets.clear();
            initDropTargets();

        }
        if(game.getCurrentTable().getSpotTargets()!=0&&game.getCurrentTable().isPlayableTable()&&game.getCurrentTable().getCurrentlyDroppedSpotTargets()==game.getCurrentTable().getSpotTargets()){
            game.getCurrentTable().resetSpotTargets();
            for(Entity e:viewSpotTargets){
                e.removeFromWorld();
            }
            viewSpotTargets.clear();
            initSpotTargets();
        }

        if(game.getIsActivateDropBonus()){
            for (Entity k:viewKickerBumpers){
                k.getComponent(KickerBumperComponent.class).upgrade(k);
            }
            for(Entity p:viewPopBumpers){
                p.getComponent(PopBumperComponent.class).upgrade(p);
            }
        }
    }

    ObservableList<Integer> dropTargetList=FXCollections.observableArrayList(0,1,2,3,4,5);
    ObservableList<Integer> spotTargetList=FXCollections.observableArrayList(0,1,2,3,4,5);
    ObservableList<Double> probList=FXCollections.observableArrayList(0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0);
    ObservableList<Integer> bumperList=FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10);

    private ChoiceBox dropBox=new ChoiceBox(dropTargetList);
    private ChoiceBox spotBox=new ChoiceBox(spotTargetList);
    private ChoiceBox probBox=new ChoiceBox(probList);
    private ChoiceBox bumperBox=new ChoiceBox(bumperList);

    @Override
    protected void initUI() {
        Text uiScoreString =getUIFactory().newText("Score:", 20);
        uiScoreString.setTranslateX(getWidth()-200);
        uiScoreString.setTranslateY(25);
        uiScoreString.fillProperty().bind(getGameState().objectProperty("stageColor"));

        Text uiScore = getUIFactory().newText("", 20);
        uiScore.setTranslateX(getWidth() - 200);
        uiScore.setTranslateY(50);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());

        Text uiBalls =getUIFactory().newText("", 20);
        uiBalls.setTranslateX(getWidth()-200);
        uiBalls.setTranslateY(100);
        uiBalls.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiBalls.textProperty().bind(getGameState().intProperty("balls").asString());

        Text uiBallsString =getUIFactory().newText("Balls:", 20);
        uiBallsString.setTranslateX(getWidth()-200);
        uiBallsString.setTranslateY(75);
        uiBallsString.fillProperty().bind(getGameState().objectProperty("stageColor"));

        Text uiMsg =getUIFactory().newText("Press create to generate table",Color.BLUE,20);
        uiMsg.setTranslateX(getWidth()-300);
        uiMsg.setTranslateY(600);
        uiMsg.textProperty().bind(getGameState().stringProperty("msg"));

        getGameScene().addUINode(uiScoreString);
        getGameScene().addUINode(uiScore);
        getGameScene().addUINode(uiBalls);
        getGameScene().addUINode(uiBallsString);
        getGameScene().addUINode(uiMsg);

        Button create=getUIFactory().newButton("Create");
        create.setTranslateX(550);
        create.setTranslateY(620);

        create.setStyle("-fx-background-color: gray");
        create.setPrefSize(150,30);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                destroyBumpers();
                destroyTargets();
                if(!ballNull){
                    destroyBall();
                }
                game=new Game();
                Table table = new GameTable("Table",(int) bumperBox.getValue(),(double) probBox.getValue(), (int)spotBox.getValue(), (int) dropBox.getValue());
                game.setGameTable(table);

                initPopBumpers();
                initKickerBumpers();
                initDropTargets();
                initSpotTargets();

                getGameState().setValue("msg", "Press b to init Ball");
                canBallInit = true;
            }
        });

        getGameScene().addUINode(create);

        Text nDrop =getUIFactory().newText("Number DropTargets:",Color.BLUE,15);
        nDrop.setTranslateX(getWidth()-200);
        nDrop.setTranslateY(190);

        dropBox.setTranslateX(getWidth()-200);
        dropBox.setTranslateY(200);
        dropBox.setValue(3);

        getGameScene().addUINode(dropBox);
        getGameScene().addUINode(nDrop);

        Text nSpot =getUIFactory().newText("Number SpotTargets:",Color.BLUE,15);
        nSpot.setTranslateX(getWidth()-200);
        nSpot.setTranslateY(250);

        spotBox.setTranslateX(getWidth()-200);
        spotBox.setTranslateY(260);
        spotBox.setValue(3);

        getGameScene().addUINode(spotBox);
        getGameScene().addUINode(nSpot);

        Text nProb =getUIFactory().newText("Prob:",Color.BLUE,15);
        nProb.setTranslateX(getWidth()-200);
        nProb.setTranslateY(305);

        probBox.setTranslateX(getWidth()-200);
        probBox.setTranslateY(315);
        probBox.setValue(0.5);

        getGameScene().addUINode(probBox);
        getGameScene().addUINode(nProb);

        Text nBumper =getUIFactory().newText("Number Bumpers:",Color.BLUE,15);
        nBumper.setTranslateX(getWidth()-200);
        nBumper.setTranslateY(365);

        bumperBox.setTranslateX(getWidth()-200);
        bumperBox.setTranslateY(375);
        bumperBox.setValue(6);

        getGameScene().addUINode(bumperBox);
        getGameScene().addUINode(nBumper);


    }

    @Override

    public void initPhysics(){
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.POPBUMPER) {
            protected void onHitBoxTrigger(Entity ball, Entity bumper, HitBox boxBall, HitBox boxBumper) {
                bumper.getComponent(PopBumperComponent.class).hit(bumper);
                getAudioPlayer().playSound("hit.wav");
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.KICKERBUMPER) {
            protected void onHitBoxTrigger(Entity ball, Entity bumper, HitBox boxBall, HitBox boxBumper) {
                bumper.getComponent(KickerBumperComponent.class).hit(bumper);
                getAudioPlayer().playSound("hit.wav");
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.DROPTARGET) {
            protected void onHitBoxTrigger(Entity ball, Entity target, HitBox boxBall, HitBox boxBumper) {
                target.getComponent(DropTargetComponent.class).hit(target,factory,viewDropTargets);
                getAudioPlayer().playSound("hit.wav");
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.SPOTTARGET) {
            protected void onHitBoxTrigger(Entity ball, Entity target, HitBox boxBall, HitBox boxBumper) {
                target.getComponent(SpotTargetComponent.class).hit(target,factory,viewSpotTargets);
                getAudioPlayer().playSound("hit.wav");
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.WALL) {
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                if (boxWall.getName().equals("BOT")) {
                    destroyBall();
                    game.dropBall();
                }

            }
        });
    }

    public void destroyBall(){
        rleft.removeFromWorld();
        rright.removeFromWorld();
        leftBar.removeFromWorld();
        rightBar.removeFromWorld();
        ball.removeFromWorld();
        getGameWorld().addEntity(rleft);
        getGameWorld().addEntity(rright);
        getGameWorld().addEntity(leftBar);
        getGameWorld().addEntity(rightBar);
        canBallInit=true;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
