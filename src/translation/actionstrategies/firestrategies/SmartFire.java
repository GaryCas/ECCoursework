package translation.actionstrategies.firestrategies;

/**
 * Created by rd019985 on 03/03/2017.
 */
public class SmartFire extends FireStrategy{

    @Override
    public String translateGenotype(int distance, int power) {

        stringBuilder.append("if(robotDistance >"+distance+" || getEnergy() < 15){");
        stringBuilder.append("fire(this.firePower);}");
        stringBuilder.append("else if (robotDistance > "+distance/2+") {fire(this.firePower+1);}");
        stringBuilder.append("else{fire(this.firePower+2);}");

        return stringBuilder.toString();
    }
}
