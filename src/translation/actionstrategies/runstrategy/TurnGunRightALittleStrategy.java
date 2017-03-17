package translation.actionstrategies.runstrategy;

import services.GetterService;

/**
 * Created by rd019985 on 17/03/2017.
 */
public class TurnGunRightALittleStrategy extends RunStrategy{

    @Override
    public String translateAction(int largeValue, int smallValue) {
        GetterService.stringBuilder.append("turnGunRight("+smallValue+");}");

        return GetterService.stringBuilder.toString();
    }

}