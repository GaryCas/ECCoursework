package translation.actionstrategies.adjustfirestrategies;

import translation.actionstrategies.BehaviourStrategy;

/**
 * Created by rd019985 on 03/03/2017.
 */
public abstract class AdjustFireStrategy extends BehaviourStrategy {

    @Override
    public abstract String translateAction(int value1, int value2);

    @Override
    public AdjustFireStrategy getBehaviourStrategy(int action){
        return evaluateAction(action);
    }

    public static AdjustFireStrategy evaluateAction(int action){
        System.out.println();

        switch (action){
            case 0:
                return new IncrementFirePowerStrategy();
            case 1000:
                return new DecrementFirePowerStrategy();
            default:
                System.out.println("That strategy does not exist in the AdjustFireStrategies defautling to increment power");
                return new IncrementFirePowerStrategy();
        }
    }
}
