package translation.actionstrategies;

import services.GetterService;

public abstract class BehaviourStrategy {

    protected StringBuilder stringBuilder = GetterService.getStringBuilder();

    abstract public BehaviourStrategy getBehaviourStrategy(int action);

    abstract public String translateGenotype(int value1, int value2);

}
