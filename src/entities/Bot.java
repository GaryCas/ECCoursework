package entities;

/**
 * Created by rd019985 on 02/03/2017.
 */
public abstract class Bot {

    abstract public String GetRunMethod();

    abstract public String GetOnScannedRobot();

    abstract public String GetOnHitByBullet();

    abstract public String GetOnHitWall();

    abstract public String GetOnHitRobot();

    public abstract void init();

    public abstract void createCode();

    public abstract void compile();
}
