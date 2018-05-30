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

/**
 * this class is a normal table that the driver is suposed to use
 * so every game class should implement this class
 *
 * @author David de la puente
 */
public class GameTable extends GeneralTable{
    private boolean isPlayable=false;
    private String name;

    private int numberOfBumpers;

    private int numberOfDropTargets;
    private int numberOfSpotTargets;

    private double prob;

    private List<Bumper> bumpers;
    private List<Target> targets;

    /**
     * the constructor, that resive the initial parameters, and a seed, the seed make the random things, pseudo random
     * so this class can be tested
     * @param  name the name off the table
     * @param numberOfBumpers how many bumpers
     * @param prob the probabilty of the PopBumpers
     * @param numberOfSpotTargets cuantity of spot
     * @param numberOfDropTargets cuantity of Drop
     * @param seed the sed of the random generator
     */
    public GameTable(String name,int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets, int seed){
        this.name=name;
        this.numberOfBumpers=numberOfBumpers;

        this.numberOfSpotTargets=numberOfSpotTargets;
        this.numberOfDropTargets=numberOfDropTargets;
        this.prob=prob;

        this.currentlyDroppedDropTargets=0;
        this.bumpers=new ArrayList<>();
        this.targets=new ArrayList<>();


        Random generator = new Random(seed);
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


    /**
     * the constructor, that resive the initial parameters but dont have a seed so this is random
     * it cant be deterministic tested
     * @param  name the name off the table
     * @param numberOfBumpers how many bumpers
     * @param prob the probabilty of the PopBumpers
     * @param numberOfSpotTargets cuantity of spot
     * @param numberOfDropTargets cuantity of Drop
     */
    public GameTable(String name,int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        this.name=name;
        this.numberOfBumpers=numberOfBumpers;

        this.numberOfSpotTargets=numberOfSpotTargets;
        this.numberOfDropTargets=numberOfDropTargets;
        this.prob=prob;

        this.currentlyDroppedDropTargets=0;
        this.bumpers=new ArrayList<>();
        this.targets=new ArrayList<>();


        Random generator = new Random();
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
    public void increseDroppedDropTargets(){
        this.currentlyDroppedDropTargets+=1;
    }

    @Override
    public void resetDropTargets() {
        int numberOfTargets=this.numberOfSpotTargets+this.numberOfDropTargets;
        for(int i=this.numberOfSpotTargets;i<numberOfTargets;i++){
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

    /**
     * this metod set The game in every bumper and target, so them
     * can send the respective information to the bonus
     * @see logic.bonus
     * @param game the current game
     */
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
