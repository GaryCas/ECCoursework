package translation.actionstrategies.adjustfirestrategies;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class IncrementFirePowerStrategy extends AdjustFireStrategy {

    @Override
    public String translateGenotype(int v1, int v2) {
        return "this.firePower = this.firePower++;";
    }
}
