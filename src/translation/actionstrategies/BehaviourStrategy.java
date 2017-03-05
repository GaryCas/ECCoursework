package translation.actionstrategies;

import services.GetterService;

public abstract class BehaviourStrategy {

    abstract public BehaviourStrategy getBehaviourStrategy(int action);

    abstract public String translateAction(int value1, int value2);

}
