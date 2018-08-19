package controller;

import controller.visitor.Visitor;
import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
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
public class Game implements Observer{
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

    /**
     * @return if the table of the game is playable
     */

    public boolean isPlayableTable(){
        return table.isPlayableTable();
    }

    /**
     * when a game is created is inicialized whit a null table
     * so this metod, set the valeu of the table to the param
     * @param newTable the new table
     */

    public void setGameTable(Table newTable) {
        NullGameTable t=(NullGameTable) newTable;
        t.addObserver(this);
        this.table= t;

        table.makePlayable();
        this.availableBalls=3;
        this.currentScore=0;
    }

    /**
     * @return the table name
     */

    public String getTableName() {
        return table.getTableName();
    }

    /**
     * @return the actual table which is being played
     */
    public Table getCurrentTable() {
        return this.table;
    }

    /**
     * @return the bumpers of the table
     */
    public List<Bumper> getBumpers() {
        return table.getBumpers();
    }

    /**
     * @return the targets of the table
     */

    public List<Target> getTargets() {
        return table.getTargets();
    }

    /**
     * @return the DropTargetBonus variable that has the game
     */
    public Bonus getDropTargetBonus() {
        return this.dropTargetBonus;
    }

    /**
     * @return the ExtraBallBonusThat has the game
     */
    public Bonus getExtraBallBonus() {
        return this.extraBallBonus;
    }

    /**
     * @return the jackPotBonus That has the game
     */
    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    /**
     * @return the number of balls that has the game
     */
    public int getAvailableBalls() {
        return this.availableBalls;
    }

    /**
     * @return the score of the game
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    /**
     * decreese in one the number of ballsavailable in the game
     */
    public void dropBall() {
        this.availableBalls-=1;
    }

    /**
     * increese in one the numer of balls available in the game
     */
    public void addBall(){this.availableBalls+=1;}

    /**
     * @return the state of the game, if the game ends, this will return true
     */
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

    /**
     * trigger the extraBallBonus that the game has
     */
    public void triggerGetExtraBallBonus(){
        this.extraBallBonus.trigger(this);
    }

    /**
     * trigger the jackPotBonus that the game has
     */
    public void triggerJackPotBonus() {
        this.jackPotBonus.trigger(this);
    }

    /**
     * trigger the DropTargetBonus that the game has
     */
    public void triggerDropTargetBonus() {
        this.dropTargetBonus.trigger(this);
    }

    /**
     * set the game score=score+i
     * @param score the points
     */
    public void plusScore(int score) {
        this.currentScore+=score;
    }
}
