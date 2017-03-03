package strategies.firestrategies;

import strategies.BehaviourStrategy;
import strategies.movementstrategies.MovementStrategy;

/**
 * Created by rd019985 on 03/03/2017.
 */
public abstract class FireStrategy extends BehaviourStrategy{

    @Override
    public abstract String translateGenotype(int distance, int power);

    @Override
    public FireStrategy getBehaviourStrategy(int action){
        return evaluateAction(action);
    }

    public static FireStrategy evaluateAction(int action){
        switch (action){
            case 0:
                return new FireNormal();
            case 1000:
                return new SmartFire();
            default:
                System.out.println("That strategy does not exist in the FireStrategies defautling to FireNormal");
                return new FireNormal();
        }
    }
}
