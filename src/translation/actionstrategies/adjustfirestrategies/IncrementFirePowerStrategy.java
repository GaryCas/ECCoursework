package translation.actionstrategies.adjustfirestrategies;

import services.GetterService;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class IncrementFirePowerStrategy extends AdjustFireStrategy {

    @Override
    public String translateAction(int v1, int v2) {
        GetterService.stringBuilder.append("this.firePower = this.firePower++;");

        return GetterService.stringBuilder.toString();
    }
}
