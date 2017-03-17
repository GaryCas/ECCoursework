package translation.actionstrategies.firestrategies;

import services.GetterService;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class FireNormal extends FireStrategy {
    @Override
    public String translateAction(int distance, int power) {
        GetterService.stringBuilder.append("fire(this.firePower);");

        return GetterService.stringBuilder.toString();
    }
}
