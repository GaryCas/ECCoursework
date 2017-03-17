package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.BotProvider;
import utils.Utils;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by rd019985 on 16/03/2017.
 */
public class BattleRunnerTest {

    String[] testFileNames = { };

    String[] justDeleteFileNames = {ApplicationVariables.UTPATH+"\\"+"test.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID0.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID1.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID2.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID3.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID4.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID5.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID6.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID7.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID8.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID9.java",
            ApplicationVariables.UTPATH+"\\"+"botG0ID0.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID1.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID2.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID3.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID4.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID5.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID6.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID7.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID8.class",
            ApplicationVariables.UTPATH+"\\"+"botG0ID9.class"
    };

    final static String[] sittingDuck = {
            "sample.SittingDuck"
    };

    final static String[] sittingDucks = {
            "sample.SittingDuck",
            "sample.SittingDuck",
            "sample.SittingDuck",
            "sample.SittingDuck",
            "sample.SittingDuck"
    };

    final static String[] mixedSamples = {
            "sample.SittingDuck",
            "sample.Fire",
            "sample.SpinBot",
            "sample.Target"
    };

    final static String[] tracker = {
            "sample.Tracker"
    };

    final static String[] trackers = {
            "sample.Tracker",
            "sample.Tracker",
            "sample.Tracker",
            "sample.Tracker",
            "sample.Tracker"
    };

    final static String[] rivalsBatch1 = {
            "sample.Tracker"
            //"sample.Crazy",
            //"sample.SuperTracker"
            //"sample.SuperTrackFire",
            //"sample.SuperRamFire",
            //"ary.micro.Weak 1.2"
            //"sheldor.nano.Sabreur_1.1.1"
            //"sample.Sabreur"
            //"mld.LittleBlackBook_1.69e"
            //"mld.Moebius_2.9.3"
    };


    @Before
    public void setUp() throws IOException {
        Utils.createFiles(justDeleteFileNames);
    }

    @After
    public void tearDown(){
        Utils.deleteFiles(justDeleteFileNames);
    }

    @Test
    public void shouldRunBattleBetweenTwoSamples() throws IOException {
        // given

        // when
        BattleRunner battleRunner = new BattleRunner();
        double[] actualFitness = battleRunner.runOneOnOneBattle(tracker, sittingDuck, ECRunner.ROUNDS);

        // then
        assertTrue(actualFitness[0]== 1.0);
    }

    @Test
    public void shouldRunBattleBetweenManySamples() throws IOException {
        // given

        // when
        BattleRunner battleRunner = new BattleRunner();
        double[] actualFitness = battleRunner.runOneOnOneBattle(trackers, sittingDucks, 2);

        // then
        assertTrue(actualFitness[0]== 1.0);
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
            botEntityNames[i] = botEntities[i].getPackageName() + "." + botEntities[i].getBotName()+"*";
        }

        // when
        BattleRunner battleRunner = new BattleRunner();
        double[] actualFitness = battleRunner.runOneOnOneBattle(botEntityNames, sittingDuck, 1);

        // then
        assertTrue(actualFitness[0]== 1.0);
    }


}
