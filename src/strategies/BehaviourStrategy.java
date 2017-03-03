package strategies;

import strategies.movementstrategies.MovementStrategy;

public abstract class BehaviourStrategy {
    abstract public BehaviourStrategy getBehaviourStrategy(int action);

    abstract public String translateGenotype(int value1, int value2);
}
