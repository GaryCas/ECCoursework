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
        assertEquals(0,ecRunner.newGeneration.size());

        //when
        ecRunner.initGeneration();

        //then
        for (int i = 0; i < ecRunner.newGeneration.size(); i++) {
            assertEquals("botG"+i+"ID"+i,ecRunner.newGeneration.get(i).getBotName());
        }
    }

    @Test
    public void shouldInitGenerationProperlyGenome(){
        //given
        assertEquals(0,ecRunner.newGeneration.size());

        //when
        ecRunner.initGeneration();

        //then
        for (BotEntity botEntity : ecRunner.newGeneration) {
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
