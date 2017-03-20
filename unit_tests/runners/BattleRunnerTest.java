package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import robocode.BattleResults;
import services.BreedingService;
import utils.BotProvider;
import utils.SampleProvider;
import utils.Utils;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by rd019985 on 16/03/2017.
 */
public class BattleRunnerTest {


    String[] justDeleteFileNames = {ApplicationVariables.UTPATH + "\\" + "test.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID0.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID1.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID2.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID3.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID4.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID5.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID6.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID7.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID8.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID9.java",
            ApplicationVariables.UTPATH + "\\" + "botG0ID0.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID1.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID2.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID3.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID4.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID5.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID6.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID7.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID8.class",
            ApplicationVariables.UTPATH + "\\" + "botG0ID9.class"
    };


    @Before
    public void setUp() throws IOException {
        Utils.createFiles(justDeleteFileNames);
    }

    @After
    public void tearDown() {
        Utils.deleteFiles(justDeleteFileNames);
    }

    @Test
    public void shouldRunBattleBetweenTwoSamples() throws IOException {
        // given

        // when
        BattleRunner battleRunner = new BattleRunner();
        BattleResults[] battleResults = battleRunner.runOneOnOneBattle(SampleProvider.tracker, SampleProvider.sittingDuck, ECRunner.ROUNDS, new BotProvider().setUpBotsMini());

        // then
        assertEquals(1, battleResults.length);
    }

    @Test
    public void shouldRunBattleBetweenManySamples() throws IOException {
        // given

        // when
        BattleRunner battleRunner = new BattleRunner();
        BattleResults[] actualFitness = battleRunner.runOneOnOneBattle(SampleProvider.trackers, SampleProvider.sittingDucks, 2, new BotProvider().setUpBotsMini());

        // then
        assertEquals(5, actualFitness.length);
    }

    @Test
    public void shouldRunBattleWithG0Robots() throws IOException {
        // given
        BotProvider botProvider = new BotProvider();
        BotEntity[] botEntities = botProvider.setUpBots();

        for (BotEntity botEntity : botEntities) {
            botEntity.compile();
        }

        String botEntityNames[] = new String[10];

        for (int i = 0; i < botEntities.length; i++) {
            botEntityNames[i] = botEntities[i].getPackageName() + "." + botEntities[i].getBotName() + "*";
        }

        // when
        BattleRunner battleRunner = new BattleRunner();
        BattleResults[] battleResults = battleRunner.runOneOnOneBattle(botEntityNames, SampleProvider.sittingDuck, 1, botEntities);

        // then
        assertEquals(10, battleResults.length);
    }

    @Test
    public void shouldRunBattleWithManyG0Robots() throws IOException {
        // given
        BotProvider botProvider = new BotProvider();
        BotEntity[] botEntities = botProvider.setUpBots();

        for (BotEntity botEntity : botEntities) {
            botEntity.compile();
        }

        String botEntityNames[] = new String[10];

        for (int i = 0; i < botEntities.length; i++) {
            botEntityNames[i] = botEntities[i].getPackageName() + "." + botEntities[i].getBotName() + "*";
        }

        // when
        BattleRunner battleRunner = new BattleRunner();
        battleRunner.runManytoManyBattles(1, botEntityNames);

        // then
    }


    @Test
    public void shouldReturnProportionalFitnessBenchmarkArray(){
        // given
        BotProvider botProvider = new BotProvider();
        BotEntity[] botEntities = botProvider.setUpBotsMini();
        botEntities[0].setFitness(4.0);
        botEntities[1].setFitness(4.0);
        botEntities[2].setFitness(2.0);
        botEntities[3].setFitness(2.0);

        // when
        double[] results = BreedingService.defineBenchmarkArray(botEntities);

        // then
        assertEquals(0,(int) results[0]);
        assertEquals(33,(int) results[1]);
        assertEquals(66,(int) results[2]);
        assertEquals(83,(int) results[3]);

        assertEquals(0, BreedingService.fitnessProportionateSelection(results, 5));
        assertEquals(0, BreedingService.fitnessProportionateSelection(results, 30));
        assertEquals(1, BreedingService.fitnessProportionateSelection(results, 40));
        assertEquals(2, BreedingService.fitnessProportionateSelection(results, 70));
        assertEquals(3, BreedingService.fitnessProportionateSelection(results, 90));
    }

}
