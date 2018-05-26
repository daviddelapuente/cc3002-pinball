package logic.gameelements.bumper.bumpermode;

public abstract class AbstractBumperMode implements BumperMode {
    protected int score;

    public abstract boolean getIsUpgrade();

    public int getScore(){
        return this.score;
    }
}
