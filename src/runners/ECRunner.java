package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import robocode.BattleResults;
import services.BreedingService;
import services.CompilationService;
import services.FileWritingService;

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
    BattleRunner arena = new BattleRunner();

    BotEntity[] currentGeneration = new BotEntity[POP_SIZE];
    BotEntity[] newGeneration = new BotEntity[POP_SIZE];

    public void ECLoop(String path) {
        // -- EC loop
        while(genCount < MAX_GENS){
            // set the current generation
            currentGeneration = newGeneration;

            // create the new generation
            newGeneration = BreedingService.createNewGeneration(currentGeneration);

            // compile the new children
            CompilationService.compile(newGeneration, path);

            String[] genBots = getGenbotNames(newGeneration);

            // run battle between the rivals batch and new generation
            runBattle(genBots, rivalsBatch1);

            // calculate fitness of each bot
                // calclateFitness(newGeneration)

            // write to file

//            System.out.println("\nROUND " + genCount
//                    + "\nAvg. Fitness:\t" + avgFitness + "\t Avg # of nodes: "+avgNumNodes[genCount]
//                    + "\nBest In Round:\t" + survivors[genCount].getBotName() +" - "+ survivors[genCount].getFitness()
//                  //  + "\t# nodes " + candidates[genCount].nodeCount
//                    + "\nBest So Far:\t" + bestSoFar.getBotName() +" - "+ bestSoFar.getFitness() +"\n");

        genCount++;
        }
    }

    String[] getGenbotNames(BotEntity[] newGeneration) {
        String[] genBotNames = new String[newGeneration.length];
        for (int i = 0; i < genBotNames.length; i++) {
            genBotNames[i] = newGeneration[i].getPackageName() + "."+ newGeneration[i].getBotName() + "*";
        }

        return genBotNames;
    }

    public void initGeneration() {
            for (int i = 0; i < POP_SIZE; i++) {
                newGeneration[i] = new BotEntity(i, i);
                newGeneration[i].initGenes();
                newGeneration[i].setCode();
                newGeneration[i].compile();
            }
    }

    BattleResults[] runBattle(String[] bots, String[] sampleSet){
        return arena.runOneOnOneBattle(bots, sampleSet, ROUNDS);
    }

    public void setArena(BattleRunner arena) {
        this.arena = arena;
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
