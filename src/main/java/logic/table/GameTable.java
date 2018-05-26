package logic.table;

import controller.Game;
import controller.visitor.Visitor;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.*;

public class GameTable extends GeneralTable{
    private boolean isPlayable=false;
    private String name;

    private int numberOfBumpers;

    private int numberOfDropTargets;
    private int numberOfSpotTargets;

    private double prob;

    private List<Bumper> bumpers;
    private List<Target> targets;

    public GameTable(String name,int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        this.name=name;
        this.numberOfBumpers=numberOfBumpers;

        this.numberOfSpotTargets=numberOfSpotTargets;
        this.numberOfDropTargets=numberOfDropTargets;
        this.prob=prob;

        this.currentlyDroppedDropTargets=0;
        this.bumpers=new ArrayList<>();
        this.targets=new ArrayList<>();


        Random generator = new Random(0);
        double a;
        for (int i=0;i<this.numberOfBumpers;i++){
            a=generator.nextDouble();
            if(a<=prob){
                Bumper b=new PopBumper();
                ((PopBumper) b).addObserver((Observer) this);
                this.bumpers.add(b);

            }else{
                Bumper b=new KickerBumper();
                ((KickerBumper) b).addObserver((Observer) this);
                this.bumpers.add(b);
            }
        }

        for(int i=0;i<numberOfSpotTargets;i++){
            Target t=new SpotTarget();
            ((SpotTarget) t).addObserver((Observer) this);
            this.targets.add(t);
        }
        int numberOfTargets=numberOfDropTargets+numberOfSpotTargets;
        for(int i=numberOfSpotTargets;i<numberOfTargets;i++){
            Target t= new DropTarget();
            ((DropTarget) t).addObserver((Observer) this);
            ((DropTarget)t).setNumberOfDropTargets(this.numberOfDropTargets);
            this.targets.add(t);
        }
    }

    @Override
    public String getTableName() {
        return this.name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return this.numberOfDropTargets;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return this.currentlyDroppedDropTargets;
    }

    @Override
    public List<Bumper> getBumpers() {
        return this.bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return this.targets;
    }

    @Override
    public void resetDropTargets() {
        for(int i=0;i<this.numberOfDropTargets;i++){
            targets.get(i).reset();
        }
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper b : bumpers){
            b.upgrade();
        }
    }

    @Override
    public boolean isPlayableTable() {
        return isPlayable;
    }

    @Override
    public int getNumberOfBumpers(){
        return this.numberOfBumpers;
    }

    @Override
    public double getProp() {
        return this.prob;
    }

    public void makePlayable(){
        this.isPlayable=true;
    }

    @Override
    public void update(Observable o, Object arg) {
        int pts;

        Visitor v=(Visitor) arg;
        pts=v.getPts();

        setChanged();
        notifyObservers(pts);
    }

    public void setGame(Game game){
        this.game=game;
        for(Bumper b :bumpers){
            b.setGame(this.game);
        }

        for(Target t:targets){
            t.setGame(this.game);
        }
    }
}
