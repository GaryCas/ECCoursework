package runners;

import entities.BotEntity;
import services.FileWritingService;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunner {
    static int genCount = 0, best;
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

    BotEntity[] currentGeneration = new BotEntity[POP_SIZE];
    BotEntity[] newGeneration = new BotEntity[POP_SIZE];
    BotEntity[] winners = new BotEntity[POP_SIZE];

    static double
            PROB_CROSSOVER = 0.85,
            PROB_REPLICATION = 0.05,
            PROB_MUTATION = 0.1,
            PROB_ARCHITECTURE = 0.0,

    PROB_INTERNAL_NODE = 0.9,
            PROB_ANY_NODE = 0.1,

    fitnesses[] = new double[POP_SIZE],
            slice[] = new double[POP_SIZE],
            totalFitness,
            avgFitness,
            allAvgFitnesses[] = new double[MAX_GENS],
            avgNumNodes[] = new double[MAX_GENS],
            avgNodeCount;

    public void ECLoop() {
        // -- EC loop
        while(genCount < MAX_GENS){


            for(int i = 0; i < POP_SIZE; i++)
                botNames[i] = currentGeneration[i].PACKAGE + "."+currentGeneration[i].getBotName();

            scoreFitnessOnSet(rivalsBatch1);

            totalFitness = 0;
            avgFitness = 0;
            best = 0;
            avgNodeCount = 0;

            // get total fitness
            for(int i=0; i<POP_SIZE; i++){

            }

            //
            avgNumNodes[genCount] = (avgNodeCount /= POP_SIZE);

            avgFitness = totalFitness/POP_SIZE;
            allAvgFitnesses[genCount] = avgFitness;

            // store the best-in-generation
            winners[genCount] = currentGeneration[best];
            if(currentGeneration[best].getFitness() > bestSoFar.getFitness()) bestSoFar = currentGeneration[best];

            System.out.println("\nROUND " + genCount
                    + "\nAvg. Fitness:\t" + avgFitness + "\t Avg # of nodes: "+avgNumNodes[genCount]
                    + "\nBest In Round:\t" + winners[genCount].getBotName() +" - "+ winners[genCount].getFitness()
                  //  + "\t# nodes " + candidates[genCount].nodeCount
                    + "\nBest So Far:\t" + bestSoFar.getBotName() +" - "+ bestSoFar.getFitness() +"\n");

            FileWritingService.outputRunData(genCount, avgFitness, currentGeneration[best].getFitness(), avgNodeCount, currentGeneration[best].fileName);

            //if(++genCount == MAX_GENS) break;
            genCount++;


            // breed next generation
            System.out.println("In breeding stage");
            breedPool();

            // set newPool as pool, clear newPool
            currentGeneration = newGeneration;
            newGeneration = new BotEntity[POP_SIZE];

            compilePool();

            // delete all old files?

        }
    }



    private static void compilePool() {

    }

    private static void breedPool() {

    }

    public void initGeneration() {
            for (int i = 0; i < POP_SIZE; i++) {
                currentGeneration[i] = new BotEntity(0, i);
                currentGeneration[i].initGenes();
                currentGeneration[i].setCode();
                currentGeneration[i].compile();
            }
    }

    private void scoreFitnessOnSet(String[] sampleSet){
        // generate battle between member and opponents from samples package
        BattleRunner arena = new BattleRunner();
        arena.runBatchWithSamples(botNames, sampleSet, ROUNDS);
    }

}
