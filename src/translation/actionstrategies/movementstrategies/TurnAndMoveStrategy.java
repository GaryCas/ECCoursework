package translation.actionstrategies.movementstrategies;

import services.GetterService;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class TurnAndMoveStrategy extends MovementStrategy {

    @Override
    public String translateAction(int largeValue, int smallValue) {
        GetterService.stringBuilder.append("turnLeft("+largeValue+");");
        GetterService.stringBuilder.append("ahead("+largeValue * smallValue+");");
        GetterService.stringBuilder.append("this.pause=false;");
        GetterService.stringBuilder.append("turnRadarRight("+largeValue+");");

        return GetterService.stringBuilder.toString();
    }
}
