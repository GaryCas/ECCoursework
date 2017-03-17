package translation.actionstrategies.runstrategy;

import services.GetterService;

/**
 * Created by rd019985 on 17/03/2017.
 */
public class TrackStrategy extends RunStrategy{

    @Override
    public String translateAction(int largeValue, int smallValue) {
        GetterService.stringBuilder.append("turnGunRight(gunTurnAmt);\tcount++;\tif (count > "+smallValue/2+") {gunTurnAmt = "+largeValue+";\t}if (count > 5) {\tgunTurnAmt = "+largeValue+";}");

        return GetterService.stringBuilder.toString();
    }

}