package translation.actionstrategies.movementstrategies;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class SquareMovementStrategy extends MovementStrategy {

    @Override
    public String translateGenotype(int largeValue, int smallValue) {
        stringBuilder.append("turnLeft(90);");
        stringBuilder.append("ahead("+largeValue * smallValue+");");
        stringBuilder.append("this.pause=false;");

        return stringBuilder.toString();
    }
}
