package logic.gameelements.bumper;

import controller.Game;
import controller.IGame;
import logic.gameelements.bumper.bumpermode.BumperMode;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;

import java.util.Observable;
import java.util.Random;

/**
 * Abstract Class that has some variables and methods in common of KickerBumpers
 * and popBumpers
 *
 * <p>Objets that are Bumpers should extends this interface</p>
 *
 * @author David de la puente
 * @see logic.gameelements.bumper.KickerBumper
 * @see logic.gameelements.bumper.PopBumper
 */
public abstract class AbstractBumper extends Observable implements Bumper{
    protected int hitsToUpgrade;
    protected BumperMode bumperMode;
    protected BumperModeNotUpgrade bmnu;
    protected BumperModeUpgrade bmu;
    protected IGame game;

    @Override
    public int remainingHitsToUpgrade() {
        return this.hitsToUpgrade;
    }
    @Override
    public boolean isUpgraded() {
        return this.bumperMode.getIsUpgrade();
    }

    /**
     * this metod upgrade a bumper, so it change the bumperMode
     * @see logic.gameelements.bumper.bumpermode
     */
    @Override
    public void upgrade() {
        this.bumperMode=bmu;
    }

    /**
     * this metod downgrade a bumper, so it change the bumperMode
     * @see logic.gameelements.bumper.bumpermode
     */
    @Override
    public void downgrade() {
        this.bumperMode= bmnu;
        this.resetHitsToUpgrade();
    }

    /**
     * reset to 0 the remaining hits to upgrade
     */
    protected abstract void resetHitsToUpgrade();

    @Override
    public abstract int hit();

    @Override
    public abstract int hit(int seed);

    @Override
    public int getScore() {
        return this.bumperMode.getScore();
    }

    /**
     * this metod acts like double dispatch, so the bumper
     * can tell the game if it had to trigger a bonus
     * @param game the game controller object
     */
    @Override
    public void setGame(Game game){
        this.game=game;
    }

    public BumperMode getBumperMode(){
        return this.bumperMode;
    }

    public void bonusOfHit(int seed){
        if(this.remainingHitsToUpgrade()==0&&!this.isUpgraded()){

            //seed=900000
            Random generetor = new Random(900000);
            double a=generetor.nextDouble();
            if(a<=0.1){
                this.game.triggerGetExtraBallBonus();
            }
            this.upgrade();
        }
    }

    public void bonusOfHit(){
        if(this.remainingHitsToUpgrade()==0&&!this.isUpgraded()){
            Random generetor = new Random();
            double a=generetor.nextDouble();
            if(a<=0.1){
                this.game.triggerGetExtraBallBonus();
            }
            this.upgrade();
        }
    }
}
