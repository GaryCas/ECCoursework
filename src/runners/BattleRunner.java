package runners;

import robocode.BattleResults;
import robocode.Robot;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BattleRunner {

    RobocodeEngine engine;
    BattlefieldSpecification battlefield;
    BattleObserver battleObserver;

    public BattleRunner() {
        engine = new RobocodeEngine(new java.io.File("C:/Robocode"));
        battleObserver = new BattleObserver();
        engine.addBattleListener(battleObserver);
        engine.setVisible(false);
        battlefield = new BattlefieldSpecification(800, 600);
    }

    // based on example from Robocode Control API JavaDocs
    class BattleObserver extends BattleAdaptor {

        robocode.BattleResults[] results;

        public void onBattleCompleted(BattleCompletedEvent e) {
            results = e.getIndexedResults();
        }

        public void onBattleError(BattleErrorEvent e) {
            System.out.println("Error running battle: " + e.getError());
        }

        public BattleResults[] getResults() {
            return results;
        }
    }

    public double[] runOneOnOneBattle(String bots[], String samples[], int rounds) {
        engine = getEngine();
        double fitnesses[] = new double[bots.length];

        String bot, opponent;
        BattleResults[] results;

        System.out.println("Running battles against sample batch");
        for (int i = 0; i < bots.length; i++) {
            double fitnessScore = 0;

            for (int j = 0; j < samples.length; j++) {
                bot = bots[i];
                opponent = samples[j];

                results = runBattle(engine, rounds, bot, opponent);

                int myBot = (results[0].getTeamLeaderName().equals(bots[i]) ? 0 : 1);
                int opBot = (myBot == 1 ? 0 : 1);
                int botScore = results[myBot].getScore();

                double totalScore = botScore + results[opBot].getScore();
                // orginal -- double roundFitness = (botScore + BATTLE_HANDICAP)/(totalScore+BATTLE_HANDICAP);
                double roundFitness = (botScore) / (totalScore);

                fitnessScore += roundFitness;
            }
            fitnesses[i] = fitnessScore / samples.length;    // take average of each round score

        }

        return fitnesses;
    }

    private BattleResults[] runBattle(RobocodeEngine engine, int rounds, String bot, String opponent) {
        RobotSpecification[] selectedBots = engine.getLocalRepository(bot + ", " + opponent);
        BattleSpecification battleSpec = new BattleSpecification(rounds, battlefield, selectedBots);
        engine.setVisible(true);
        engine.runBattle(battleSpec, true);

        return battleObserver.getResults();
    }

    /**
     * performance enchaning measure
     *
     * @return
     */
    public RobocodeEngine getEngine() {
        if(engine == null){
            engine = new RobocodeEngine(new java.io.File("C:/Robocode"));
        }
        return engine;
    }
}
