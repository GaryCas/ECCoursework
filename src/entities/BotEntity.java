package entities;

import services.GetterService;
import translation.EventCodeTranslator;
import translation.actionstrategies.BehaviourStrategy;
import translation.actionstrategies.adjustfirestrategies.AdjustFireStrategy;
import translation.actionstrategies.firestrategies.FireStrategy;
import translation.actionstrategies.movementstrategies.MovementStrategy;
import translation.actionstrategies.runstrategy.RunStrategy;

import java.util.Random;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotEntity extends Bot {

   int memberGen = 0, memberID = 0;

    private Random randy;

    private String botName = "";
    private String packageName = "";
    String sourceCode = "";

    private boolean isSurvivor = false;

    BotEntity.GeneInitialiser geneInitialiser = new BotEntity.GeneInitialiser();
    BotEntity.GeneEvaluator geneEvaluator = new BotEntity.GeneEvaluator();

    // Phenome phome
    private String phenotype = "";
    private String code = "";


    // Genome geneome
    // the genome will be encoded by values e, a and v.
    private int[] genome = new int[6];
    int e = -10000;
    int geneCounter = 0;

    private double fitness;

    public BotEntity(int memberGen, int memberID) {
        this.memberGen = memberGen;
        this.memberID = memberID;
        this.botName = "botG" + memberGen + "ID"+ memberID;
        this.packageName = "robot";

        BotEntity.GeneInitialiser geneInitialiser = new BotEntity.GeneInitialiser();
        BotEntity.GeneEvaluator geneEvaluator = new BotEntity.GeneEvaluator();

       // botCompiler = new BotCompiler(botName, memberGen, memberID, this);
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
    public void compile() {
        //botCompiler.compile();
    }

    public String setCode() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClassCode());
        sb.append(translateGenotype());
        sb.append("}");

        code = sb.toString();

        return code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String translateGenotype(){
        StringBuilder sb = new StringBuilder();

        for (int gene : genome) {
            sb.append(geneEvaluator.translateGene(gene));
        }

        phenotype = sb.toString();

        return phenotype;
    }

    public String getPhenotype() {
        return phenotype;
    }

    public int getMemberGen() {
        return memberGen;
    }

    String getClassCode() {
        GetterService.stringBuilder.append("package robot;");
        GetterService.stringBuilder.append("import robocode.*;");

        GetterService.stringBuilder.append("public class " + getBotName() + " extends Robot {");
        GetterService.stringBuilder.append("int firePower = 1;");
        GetterService.stringBuilder.append("boolean pause;");
        GetterService.stringBuilder.append("int gunTurnAmt = 10;");

        String classCode = GetterService.stringBuilder.toString();
        GetterService.flushSB();

        return classCode;
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

    public String getFileName() {
        return botName+".java";
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return botName+".class";
    }


    public int[] getGenome() {
        return genome;
    }

    public void setMemberGen(int memberGen) {
        this.memberGen = memberGen;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public boolean isSurvivor() {
        return isSurvivor;
    }

    public void setSurvivor(boolean survivor) {
        isSurvivor = survivor;
    }

    public void setBotName() {
        this.botName = "botG" + memberGen + "ID"+ memberID;
    }

    void setGeneInitialiser(GeneInitialiser geneInitialiser) {
        this.geneInitialiser = geneInitialiser;
    }

    public void setGenome(int[] genome) {
        this.genome = genome;
    }

    // if I can separate these into their own classes then unit tests and setting might be a bit cleaner
    class GeneEvaluator{
        BehaviourStrategy behaviourStrategy;
        private EventCodeTranslator eventCodeTranslator;

        public GeneEvaluator() {
            eventCodeTranslator = new EventCodeTranslator();
        }

        String translateGene(int gene){
            int smallValue = extractSmallValue(gene);
            int largeValue = extractLargeValue(gene - smallValue);
            int eventNBase = extractEventNBase(gene);
            int actionNBase = extractActionNBase(gene - eventNBase);

            return getPhenome(eventNBase, actionNBase, largeValue, smallValue);
        }

        private String getPhenome(int eventNBase, int actionNBase, int largeValue, int smallValue) {
            getEventCode(eventNBase);
            getBehaviourStrategy(eventNBase, actionNBase).translateAction(largeValue, smallValue);
            GetterService.stringBuilder.append("}");

            String phenotype = GetterService.stringBuilder.toString();

            GetterService.flushSB();

            return phenotype;
        }

        void getEventCode(int eventNBase) {
            switch (eventNBase){
                case 0:
                    GetterService.stringBuilder.append(eventCodeTranslator.getRunCode());
                    break;
                case 10000:
                    GetterService.stringBuilder.append(eventCodeTranslator.getOnHitByBulletMethod());
                    break;
                case 20000:
                    GetterService.stringBuilder.append(eventCodeTranslator.getOnWallHitMethod());
                    break;
                case 30000:
                    GetterService.stringBuilder.append(eventCodeTranslator.getOnScannedRobotMethod());
                    break;
                case 40000:
                    GetterService.stringBuilder.append(eventCodeTranslator.getOnBulletHitMethod());
                    break;
                case 50000:
                    GetterService.stringBuilder.append(eventCodeTranslator.getOnBulletMissedMethod());
                    break;
                default:
                    break;
            }
        }

        BehaviourStrategy getBehaviourStrategy(int eventNBase, int actionNBase){
            actionNBase = actionNBase - (actionNBase % 1000);
            actionNBase = actionNBase%2000;

            switch (eventNBase){
                case 0:
                    behaviourStrategy = RunStrategy.evaluateAction(actionNBase);
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

        int extractSmallValue(int gene) {
            return gene % 10;
        }

        int extractLargeValue(int gene) {
            return (gene % 1000)/10;
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

        Random getRandom() {
            if (randy == null) {
                randy = new Random();
            }
            return randy;
        }
}

}

