package entities;

import java.util.Arrays;
import java.util.Comparator;
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

    String botName = "";
    String sourceCode = "";

    BotCompiler botCompiler;

    // Phenome phome


    // Genome geneome
    // the genome will be encoded by values e, a and v.
    private int[] genome;
    int e = -10000;
    int a;
    int v;

    public String fileName;

    private double fitness;

    public BotEntity(int memberGen, int memberID) {
        this.memberGen = memberGen;
        this.memberID = memberID;
        this.botName = "botG" + memberGen + "ID"+ memberID;

        botCompiler = new BotCompiler(botName, memberGen, memberID, this);
    }

    // copy from the first thing
    public void init() {
        initGenoType();
    }

    private void initGenoType() {
        genome = new int[6];
        for (int i : genome) {
            i = initGene();
        }

    }

    private int initGene() {
        initEventsInt();
        initActionsGene();
        return 0;
    }

    private void initActionsGene() {

    }


    protected int initEventsInt() {
        e += 10000;
        return e;
    }

    @Override
    public void createCode(){
        botCompiler.createBot();
    }

    @Override
    public void compile() {
        botCompiler.Compile();
    }


    public String getBotName() {
        return botName;
    }

    @Override
    public String GetRunMethod() {
        return null;
    }

    @Override
    public String GetOnScannedRobot() {
        return null;
    }

    @Override
    public String GetOnHitByBullet() {
        return null;
    }

    @Override
    public String GetOnHitWall() {
        return null;
    }

    @Override
    public String GetOnHitRobot() {
        return null;
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


}

