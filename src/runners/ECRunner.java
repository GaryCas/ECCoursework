package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import services.FileWritingService;

import java.util.ArrayList;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunner {
    static int genCount = 0;
    public static BotEntity bestSoFar;

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
            POP_SIZE = 10,
            MAX_GENS = 4,
            ROUNDS = 5;

    String botNames[] = new String[POP_SIZE];

    ArrayList<BotEntity> currentGeneration = new ArrayList<>();
    ArrayList<BotEntity> newGeneration = new ArrayList<>();
    ArrayList<BotEntity> survivors = new ArrayList<>();
    ArrayList<BotEntity> parents = new ArrayList<>();

    public void ECLoop() {
        // -- EC loop
        while(genCount < MAX_GENS){

            // creating generation will be done by the breeding service.

            // run battle between the rivals batch and current generation
            //scoreFitnessOnSet(rivalsBatch1);

            // calculate fitness of each bot

            // select survivors, probably rank and throwaway

            // select parents,

            // record results using file writer

            // breed and set current generation


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
                currentGeneration.add(new BotEntity(0, i));
                currentGeneration.get(i).initGenes();
                currentGeneration.get(i).setCode();
                currentGeneration.get(i).compile();
            }
    }

    private void scoreFitnessOnSet(String[] sampleSet){
        // generate battle between member and opponents from samples package
        BattleRunner arena = new BattleRunner();
        arena.runBatchWithSamples(botNames, sampleSet, ROUNDS);
    }

    public void setCurrentGeneration(ArrayList<BotEntity> currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public void setNewGeneration(ArrayList<BotEntity> newGeneration) {
        this.newGeneration = newGeneration;
    }

    public ArrayList<BotEntity> getCurrentGeneration() {
        return currentGeneration;
    }

    public ArrayList<BotEntity> getNewGeneration() {
        return newGeneration;
    }
}
