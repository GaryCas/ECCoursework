package translation.actionstrategies.firestrategies;

import services.GetterService;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class SmartFire extends FireStrategy{

    @Override
    public String translateAction(int distance, int power) {

        GetterService.stringBuilder.append("if(robotDistance >"+distance+" || getEnergy() < 15){");
        GetterService.stringBuilder.append("fire(this.firePower);}");
        GetterService.stringBuilder.append("else if (robotDistance > "+distance/2+") {fire(this.firePower+1);}");
        GetterService.stringBuilder.append("else{fire(this.firePower+2);}");

        return GetterService.stringBuilder.toString();
    }
}
