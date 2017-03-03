package entities;

import strategies.BehaviourStrategy;
import strategies.adjustfirestrategies.AdjustFireStrategy;
import strategies.firestrategies.FireStrategy;
import strategies.movementstrategies.MovementStrategy;

import java.util.Random;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotEntity extends Bot {

    public final static
    String PATH = new String("C:\\robocode\\robots\\testingEC");

    private static Process process;
    public static String PACKAGE = new String("testingEC");
    String JARS = new String("C:\\robocode\\libs\\robocode.jar;");

    int memberGen = 0, memberID = 0;

    Random randy;

    String botName = "";
    String sourceCode = "";

    BotCompiler botCompiler;

    BotEntity.GeneInitialiser geneInitialiser = new BotEntity.GeneInitialiser();
    BotEntity.GeneEvaluator geneEvaluator = new BotEntity.GeneEvaluator();

    // Phenome phome
    String phenotype;


    // Genome geneome
    // the genome will be encoded by values e, a and v.
    private int[] genome = new int[6];
    int e = -10000;
    int geneCounter = 0;

    public String fileName;

    private double fitness;
    private int random;

    public BotEntity(int memberGen, int memberID) {
        this.memberGen = memberGen;
        this.memberID = memberID;
        this.botName = "botG" + memberGen + "ID"+ memberID;

        BotEntity.GeneInitialiser geneInitialiser = new BotEntity.GeneInitialiser();
        BotEntity.GeneEvaluator geneEvaluator = new BotEntity.GeneEvaluator();

        botCompiler = new BotCompiler(botName, memberGen, memberID, this);
    }

      public int[] initGenes() {
        for (int i = 0; i < genome.length; i++) {
            this.genome[i] = initGene();
        }

        return genome;
    }

    int initGene() {
        int gene;
        gene =  geneInitialiser.initEventsInt();
        gene += geneInitialiser.initActionsInt();
        gene += geneInitialiser.initValuesInt();

        return gene;
    }


    @Override
    public String translateGenotype(){
        for (int gene : genome) {
            geneEvaluator.translateGene(gene);
        }

        return phenotype;
    }

    @Override
    public void compile() {
        //botCompiler.Compile();
    }

    public String getBotName() {
        return botName;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getMemberID() {
        return memberID;
    }

    public int[] getGenome() {
        return genome;
    }

    public void setGeneInitialiser(GeneInitialiser geneInitialiser) {
        this.geneInitialiser = geneInitialiser;
    }

    public void setGeneEvaluator(GeneEvaluator geneEvaluator) {
        this.geneEvaluator = geneEvaluator;
    }

    class GeneEvaluator{
        BehaviourStrategy behaviourStrategy;

        String translateGene(int gene){
            int smallValue = extractSmallValue(gene);
            int largeValue = extractLargeValue(gene - smallValue);

            return getBehaviourStrategy(gene).translateGenotype(largeValue, smallValue);
        }

        BehaviourStrategy getBehaviourStrategy(int gene){
            int eventNBase = extractEventNBase(gene);
            int actionNBase = extractActionNBase(gene - eventNBase);

            switch (eventNBase){
                case 0:
                    behaviourStrategy = MovementStrategy.evaluateAction(actionNBase);
                    break;
                case 10000:
                    behaviourStrategy = MovementStrategy.evaluateAction(actionNBase);
                    break;
                case 20000:
                    behaviourStrategy = MovementStrategy.evaluateAction(actionNBase);
                    break;
                case 30000:
                    behaviourStrategy = FireStrategy.evaluateAction(actionNBase);
                    break;
                case 40000:
                    behaviourStrategy = AdjustFireStrategy.evaluateAction(actionNBase);
                    break;
                case 50000:
                    behaviourStrategy = AdjustFireStrategy.evaluateAction(actionNBase);
                    break;
                 default:
                     System.out.println("That event has not currently been encoded in the gene ");
            }

            return behaviourStrategy;
        }


        private int extractSmallValue(int gene) {
            return gene - (gene % 10);
        }

        private int extractLargeValue(int gene) {
            return gene - (gene % 100);
        }

        int extractActionNBase(int gene) {
            return gene - (gene % 1000);
        }

        int extractEventNBase(int gene) {
            return gene - (gene % 10000);
        }


    }

    class GeneInitialiser {

    int initEventsInt () {
        e += 10000;
        return e;
    }

    int initActionsInt() {
        return getRandom().nextInt(2)*1000;
    }

    int initValuesInt() {
        return getRandom().nextInt(1000);
    }


    public Random getRandom() {
        if (randy == null) {
            randy = new Random();
        }
        return randy;
    }
}

}

