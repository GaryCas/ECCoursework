package translation.actionstrategies.runstrategy;

import translation.actionstrategies.BehaviourStrategy;
import translation.actionstrategies.movementstrategies.MovementStrategy;
import translation.actionstrategies.movementstrategies.SquareMovementStrategy;

/**
 * Created by rd019985 on 17/03/2017.
 */
public abstract class RunStrategy extends BehaviourStrategy {


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
    public RunStrategy getBehaviourStrategy(int action){
        return evaluateAction(action);
    }

    public static RunStrategy evaluateAction(int action) {
        switch (action) {
            case 0:
                return new TurnGunRightAlotStrategy();
            case 1000:
                return new TurnGunRightALittleStrategy();
            case 2000:
                return new TrackStrategy();
            case 3000:
                return new SquareMovementAndTurnGunALittleStrategy();
            case 4000:
                return new SquareMovementAndTurnGunAlotStrategy();
            case 5000:
                return new SquareMovementAndTrackStrategy();

        }
            return null;
    }
}
