package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import services.BreedingService;
import services.CompilationService;
import services.FileWritingService;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunner {
    static int genCount = 0;
    public static BotEntity bestSoFar;
    ECRunner.ECExecutor ecExecutor = new ECRunner.ECExecutor();

    final static String[] rivalsBatch1 = {
            "sample.SittingDuck"
            //"sample.SuperCrazy",
            //"sample.SuperTracker"
            //"sample.SuperTrackFire",
            //"sample.SuperRamFire",
            //"ary.micro.Weak 1.2"
            //"sheldor.nano.Sabreur_1.1.1"
            //"sample.Sabreur"
            //"mld.LittleBlackBook_1.69e"
            //"mld.Moebius_2.9.3"
    };

    final static String[] rivalsBatch2 = {
            "sample.SuperRamfire"
    };

    public static int
            POP_SIZE = 20,
            MAX_GENS = 10,
            ROUNDS = 5;

    String botNames[] = new String[POP_SIZE];

    BotEntity[] currentGeneration = new BotEntity[POP_SIZE];
    BotEntity[] newGeneration = new BotEntity[POP_SIZE];

    public void ECLoop() {
        // -- EC loop
        while(genCount < MAX_GENS){

            currentGeneration = newGeneration;

            newGeneration = BreedingService.createNewGeneration(currentGeneration);


            CompilationService.compile(newGeneration, ApplicationVariables.PATH);

            // run battle between the rivals batch and new generation
                // scoreFitnessOnSet(rivalsBatch1);

            // calculate fitness of each bot
                // calclateFitness(newGeneration)


//            System.out.println("\nROUND " + genCount
//                    + "\nAvg. Fitness:\t" + avgFitness + "\t Avg # of nodes: "+avgNumNodes[genCount]
//                    + "\nBest In Round:\t" + survivors[genCount].getBotName() +" - "+ survivors[genCount].getFitness()
//                  //  + "\t# nodes " + candidates[genCount].nodeCount
//                    + "\nBest So Far:\t" + bestSoFar.getBotName() +" - "+ bestSoFar.getFitness() +"\n");

        genCount++;
        }
    }

    public void initGeneration() {
            for (int i = 0; i < POP_SIZE; i++) {
                newGeneration[i] = new BotEntity(i, i);
                newGeneration[i].initGenes();
                newGeneration[i].setCode();
                newGeneration[i].compile();
            }
    }

    private void scoreFitnessOnSet(String[] sampleSet){
        // generate battle between member and opponents from samples package
        BattleRunner arena = new BattleRunner();
        arena.runBatchWithSamples(botNames, sampleSet, ROUNDS);
    }

    public BotEntity[] getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(BotEntity[] currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public BotEntity[] getNewGeneration() {
        return newGeneration;
    }

    public void setNewGeneration(BotEntity[] newGeneration) {
        this.newGeneration = newGeneration;
    }

    public class ECExecutor{


    }
}
