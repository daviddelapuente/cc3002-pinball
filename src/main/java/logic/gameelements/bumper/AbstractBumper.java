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

    /**
     * it should be used in hit() to know if a Bumper need to upgrade
     *
     * @return the number of hits that this class needs to upgrade
     */
    @Override
    public int remainingHitsToUpgrade() {
        return this.hitsToUpgrade;
    }

    /**
     * defines is a bumper is upgrade or not
     * it should be used in hit()
     * @return if a bumper is alredy upgrade
     */
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
     * this metod change the variable hits to upgrade to the incial value of this variable
     */
    protected abstract void resetHitsToUpgrade();

    /**
     * explained in the hittable interface
     * @return
     */
    @Override
    public abstract int hit();

    /**
     * @return the score of the actual game
     */
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

    /**
     * alredy explained in bumper interface
     * @return the mode of a bumper
     */
    public BumperMode getBumperMode(){
        return this.bumperMode;
    }

    /**
     * this metod is in charge to probably trigger a bonus
     */
    public void bonusOfHit(){
        if(this.remainingHitsToUpgrade()==0&&!this.isUpgraded()){
            Random generetor = new Random(900000);
            double a=generetor.nextDouble();
            if(a<=0.1){
                this.game.triggerGetExtraBallBonus();
            }
            this.upgrade();
        }
    }
}
