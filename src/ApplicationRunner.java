import entities.BotEntity;
import runners.ECRunner;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ApplicationRunner {
    static ECRunner ecRunner = new ECRunner();



    public static void main(String args[]){



        // initial values
        ECRunner.bestSoFar = new BotEntity(-1 , 0);
        ECRunner.bestSoFar.setFitness(0);
        System.out.println("Initializing population");

        // intialise the first generation
        ecRunner.initGeneration();

        // run the main ECLoop
        ecRunner.ECLoop();
    }




}
