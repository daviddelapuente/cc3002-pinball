package logic.gameelements.target;

import controller.Game;
import controller.IGame;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
    protected boolean isActive;
    protected IGame game;

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void reset() {
        this.isActive=true;
    }

    @Override
    public abstract  int hit();

    @Override
    public void setGame(Game game){
        this.game=game;
    }
}
