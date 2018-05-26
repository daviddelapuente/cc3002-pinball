package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.GeneralTable;
import logic.table.NullGameTable;
import logic.table.Table;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements IGame,Observer{
    private Table table;
    private Bonus dropTargetBonus;
    private ExtraBallBonus extraBallBonus;
    private Bonus jackPotBonus;
    private int availableBalls;
    private int currentScore;

    public Game(){
        this.dropTargetBonus=new DropTargetBonus();
        this.extraBallBonus= new ExtraBallBonus();
        this.jackPotBonus=new JackPotBonus();

        ((JackPotBonus) this.jackPotBonus).addObserver(this);
        ((DropTargetBonus) this.dropTargetBonus).addObserver(this);

        this.table= new NullGameTable();
    }

    public boolean isPlayableTable(){
        return table.isPlayableTable();
    }

    public void setGameTable(Table newTable) {
        GeneralTable t=(GeneralTable) newTable;
        t.addObserver(this);
        this.table= t;

        this.table.setGame(this);
        table.makePlayable();
        this.availableBalls=3;
        this.currentScore=0;
    }

    public String getTableName() {
        return table.getTableName();
    }

    public Table getCurrentTable() {
        return this.table;
    }

    public List<Bumper> getBumpers() {
        return table.getBumpers();
    }

    public List<Target> getTargets() {
        return table.getTargets();
    }

    public Bonus getDropTargetBonus() {
        return this.dropTargetBonus;
    }

    public Bonus getExtraBallBonus() {
        return this.extraBallBonus;
    }

    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    public int getAvailableBalls() {
        return this.availableBalls;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public void dropBall() {
        this.availableBalls-=1;
    }

    public void addBall(){this.availableBalls+=1;}


    public boolean gameOver() {
        if (availableBalls<=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        int a= (int) arg;
        this.currentScore+=a;
    }

    public void triggerGetExtraBallBonus(){
        this.extraBallBonus.trigger(this);
    }

    @Override
    public void triggerJackPotBonus() {
        this.jackPotBonus.trigger(this);
    }

    @Override
    public void triggerDropTargetBonus() {
        this.dropTargetBonus.trigger(this);
    }
}
