package translation.actionstrategies.runstrategy;

import services.GetterService;

/**
 * Created by rd019985 on 17/03/2017.
 */
public class SquareMovementAndTrackStrategy extends RunStrategy {

    @Override
    public String translateAction(int largeValue, int smallValue) {
        GetterService.stringBuilder.append("turnLeft(90);");
        GetterService.stringBuilder.append("ahead("+largeValue * smallValue+");");
        GetterService.stringBuilder.append("this.pause=false;");
        GetterService.stringBuilder.append("turnRadarRight(1.0);");
        GetterService.stringBuilder.append("turnGunRight(gunTurnAmt);\tcount++;\tif (count > \"+smallValue/2+\") {gunTurnAmt = \"+largeValue+\";\\t}if (count > 5) {\\tgunTurnAmt = "+largeValue+";}}");

        return GetterService.stringBuilder.toString();
    }
}
