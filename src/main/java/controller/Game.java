package controller;

import controller.visitor.Visitor;
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
 * @author David de la puente
 */
public class Game implements IGame,Observer{
    private Table table;
    private Bonus dropTargetBonus;
    private ExtraBallBonus extraBallBonus;
    private Bonus jackPotBonus;
    private int availableBalls;
    private int currentScore;

    /**
     * the constructor of the class, it inisialize the bonus, and the table as a null table
     */
    public Game(){
        this.dropTargetBonus=new DropTargetBonus();
        this.extraBallBonus= new ExtraBallBonus();
        this.jackPotBonus=new JackPotBonus();

        ((JackPotBonus) this.jackPotBonus).addObserver(this);
        ((DropTargetBonus) this.dropTargetBonus).addObserver(this);

        this.table= new NullGameTable();
    }

    @Override
    public boolean isPlayableTable(){
        return table.isPlayableTable();
    }

    @Override
    public void setGameTable(Table newTable) {
        GeneralTable t=(GeneralTable) newTable;
        t.addObserver(this);
        this.table= t;

        table.makePlayable();
        this.availableBalls=3;
        this.currentScore=0;
    }

    @Override
    public String getTableName() {
        return table.getTableName();
    }

    @Override
    public Table getCurrentTable() {
        return this.table;
    }

    @Override
    public List<Bumper> getBumpers() {
        return table.getBumpers();
    }

    @Override
    public List<Target> getTargets() {
        return table.getTargets();
    }

    @Override
    public Bonus getDropTargetBonus() {
        return this.dropTargetBonus;
    }

    @Override
    public Bonus getExtraBallBonus() {
        return this.extraBallBonus;
    }

    @Override
    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    @Override
    public int getAvailableBalls() {
        return this.availableBalls;
    }

    @Override
    public int getCurrentScore() {
        return this.currentScore;
    }

    @Override
    public void dropBall() {
        this.availableBalls-=1;
    }

    @Override
    public void addBall(){this.availableBalls+=1;}

    @Override
    public boolean gameOver() {
        if (availableBalls<=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Visitor v=(Visitor) arg;
        v.makeTheMagic(this);
    }

    @Override
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

    @Override
    public void plusScore(int score) {
        this.currentScore+=score;
    }
}
