package translation.actionstrategies.movementstrategies;

import services.GetterService;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class SquareMovementStrategy extends MovementStrategy {

    @Override
    public String translateAction(int largeValue, int smallValue) {
        GetterService.stringBuilder.append("turnLeft(90);");
        GetterService.stringBuilder.append("ahead("+largeValue * smallValue+");");
        GetterService.stringBuilder.append("this.pause=false;");

        return GetterService.stringBuilder.toString();
    }
}
