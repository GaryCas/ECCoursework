package translation.actionstrategies.movementstrategies;

import translation.actionstrategies.BehaviourStrategy;

/**
 * Created by rd019985 on 03/03/2017.
 */
public abstract class MovementStrategy extends BehaviourStrategy {


    /**
     * Actions determining the behaviour of a robot on response to a particular event.
     *
     * @param largeValue a value that will determine a truning angle by itself
     * @param smallValue a value that will be multiplied with the large value to get a movement distance
     * @return code base for a new strategy
     */
    @Override
    public abstract String translateAction(int largeValue, int smallValue);

    @Override
    public MovementStrategy getBehaviourStrategy(int action){
        return evaluateAction(action);
    }

    public static MovementStrategy evaluateAction(int action){

        switch (action){
            case 0:
                return new SquareMovementStrategy();
            case 1000:
                return new TurnAndMoveStrategy();
            default:
                System.out.println("That strategy does not exist in the MovementStrategies defaulting to Square movement");
                return new SquareMovementStrategy();
        }
    }
}
