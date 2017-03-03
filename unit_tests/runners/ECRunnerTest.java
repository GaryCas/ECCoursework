package runners;

import entities.BotEntity;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunnerTest {
    static int temp;
    ECRunner ecRunner;

    @BeforeClass
    public static void setUpClass(){
        temp = ECRunner.POP_SIZE;
        ECRunner.POP_SIZE = 100;
    }

    @Before
    public void setUp(){
        ecRunner = new ECRunner();
    }

    @AfterClass
    public static void tearDownClass(){
        ECRunner.POP_SIZE = temp;
    }

    @Test
    public void shouldInitGenerationProperlyNames(){
        //given
        assertNull(ecRunner.currentGeneration[0]);
        assertNull(ecRunner.currentGeneration[50]);

        //when
        ecRunner.initGeneration();

        //then
        for (int i = 0; i < ecRunner.currentGeneration.length; i++) {
            assertEquals("botG"+0+"ID"+0,ecRunner.currentGeneration[0].getBotName());
        }
    }

    @Test
    public void shouldInitGenerationProperlyGenome(){
        //given
        assertNull(ecRunner.currentGeneration[0]);
        assertNull(ecRunner.currentGeneration[50]);

        //when
        ecRunner.initGeneration();

        //then
        for (BotEntity botEntity : ecRunner.currentGeneration) {
            assertEquals(6, botEntity.getGenome().length);

            // test genes
            for (int i = 0; i < botEntity.getGenome().length ; i++) {
                int geneMax = (((i+1) * 10000) - 1);
                int geneMin = geneMax - 10000;

                assertTrue(botEntity.getGenome()[i] < geneMax);
                assertTrue(botEntity.getGenome()[i] > geneMin);
            }
        }
    }

    @Test
    public void shouldTemplate(){
        //given
        //when
        //then
    }
}
