package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import robocode.BattleResults;
import services.BreedingService;
import services.CompilationService;
import services.FileWritingService;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunner extends Application {
    static int genCount = 0;
    public static BotEntity bestSoFar;

    final static String[] rivalsBatch1 = {
            "sample.Tracker"
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
            MAX_GENS = 50,
            ROUNDS = 10;

    String botNames[] = new String[POP_SIZE];
    BattleRunner arena = new BattleRunner();

    BotEntity[] currentGeneration = new BotEntity[POP_SIZE];
    BotEntity[] newGeneration = new BotEntity[POP_SIZE];

    static double[] avgResults = new double[MAX_GENS];
    static double[] bestResults = new double[MAX_GENS];
    BotEntity botEntity = new BotEntity(-1,-1);

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
            BattleResults[] results = runBattle(genBots, rivalsBatch1, newGeneration);

            // publish fitness results
            publishResults(genCount, results);

            // write to file
            for (BotEntity botEntity : newGeneration) {
                if(botEntity.getFitness() > bestSoFar.getFitness()){
                    bestSoFar = botEntity;
                }
            }

            genCount++;
        }

        FileWritingService.writeBot(bestSoFar, "best so far", ApplicationVariables.PATH, "bestSoFar.txt");

        launch();
        System.out.println();
    }

    private void publishResults(int genCount, BattleResults[] results) {
        avgResults[genCount] = calculateAvgFitness(results);

        bestResults[genCount] = calculateBestFitness(results);
    }

    private double calculateBestFitness(BattleResults[] results) {
        double bestScore = 0.0;

        for (BattleResults result : results) {
            if(result.getScore() > bestScore){
                bestScore = result.getScore();
            }
        }

        return bestScore;
    }

    private double calculateAvgFitness(BattleResults[] results) {
        double totalScore = 0.0;

        for (BattleResults result : results) {
            totalScore += result.getScore();
        }

        return totalScore / results.length;
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

    BattleResults[] runBattle(String[] bots, String[] sampleSet, BotEntity[] botEntities){
        return arena.runOneOnOneBattle(bots, sampleSet, ROUNDS, botEntities);
    }

    public void setArena(BattleRunner arena) {
        this.arena = arena;
    }


    public void setNewGeneration(BotEntity[] newGeneration) {
        this.newGeneration = newGeneration;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Line Chart Sample");

        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Generation");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Fitness");

        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis,yAxis);

        XYChart.Series avgFitnessSeries = new XYChart.Series();
        avgFitnessSeries.setName("Average fitness Series");

        XYChart.Series bestFitnessSeries = new XYChart.Series();
        bestFitnessSeries.setName("Best Fitness Series");

        for (int i = 0; i < avgResults.length; i++) {
            avgFitnessSeries.getData().add(new XYChart.Data(i, avgResults[i]));
            bestFitnessSeries.getData().add(new XYChart.Data(i, bestResults[i]));
        }

        lineChart.getData().add(avgFitnessSeries);
        lineChart.getData().add(bestFitnessSeries);

        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);

        stage.show();
    }

    public double[] getAvgResults() {
        return avgResults;
    }

    public void setAvgResults(double[] avgResults) {
        this.avgResults = avgResults;
    }

    public double[] getBestResults() {
        return bestResults;
    }

    public void setBestResults(double[] bestResults) {
        this.bestResults = bestResults;
    }
}
