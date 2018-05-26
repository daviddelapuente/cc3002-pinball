package logic.gameelements.bumper;

import controller.Game;
import controller.IGame;
import logic.gameelements.bumper.bumpermode.BumperMode;
import logic.gameelements.bumper.bumpermode.BumperModeNotUpgrade;
import logic.gameelements.bumper.bumpermode.BumperModeUpgrade;

import java.util.Observable;

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

    @Override
    public void upgrade() {
        this.bumperMode=bmu;
    }

    @Override
    public void downgrade() {
        this.bumperMode= bmnu;
        this.resetHitsToUpgrade();
    }

    protected abstract void resetHitsToUpgrade();

    @Override
    public abstract int hit();

    @Override
    public int getScore() {
        return this.bumperMode.getScore();
    }

    @Override
    public void setGame(Game game){
        this.game=game;
    }

    public BumperMode getBumperMode(){
        return this.bumperMode;
    }

}
