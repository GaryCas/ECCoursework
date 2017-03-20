package services;

import entities.BotEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rd019985 on 14/03/2017.
 */
public class MeosisServiceTest {

    MeosisService meosisService = new MeosisService();

    @Test
    public void shouldCrossover(){
        //given
        BotEntity b1 = new BotEntity(0,0);
        int[] g1 = {1,1,1,1,1,1};
        b1.setGenome(g1);

        BotEntity b2 = new BotEntity(0,1);
        int[] g2 = {2,2,2,2,2,2};
        b2.setGenome(g2);

        //when
        meosisService.singlePointCrossOver(b1,b2,3);

        //then
    }

    @Test
    public void shouldGetCorrectlySplicedMotherGene(){
        //given
        BotEntity b1 = new BotEntity(0,0);
        int[] g1 = {1,1,1,1,1,1};
        b1.setGenome(g1);

        //when
        int[] actual = meosisService.getMotherGene(b1, 3);
        int[] expected = {1,1,1};

        //then

        assertTrue(actual.length == 3);
        for (int i : actual) {
            assertEquals(actual[i], expected[i]);
        }
    }

    @Test
    public void shouldGetCorrectlySplicedFatherGene(){
        //given
        BotEntity b2 = new BotEntity(0,1);
        int[] g2 = {2,2,2,2,2,2};
        b2.setGenome(g2);

        //when
        int[] actual = meosisService.getFatherGene(b2, 3);
        int[] expected = {2,2,2};

        //then
        assertTrue(actual.length == 3);
        for (int i : actual) {
            assertEquals(actual[i], expected[i]);
        }
    }

    @Test
    public void shouldCombineGenesCorrectly(){
        //given
        int[] g1 = {1,1,1};
        int[] g2 = {2,2,2};

        //when
        int[] actual = meosisService.combineGenes(g1, g2);
        int[] expected = {1,1,1,2,2,2};

        //then
        assertTrue(actual.length == 6);
        for (int i : actual) {
            assertEquals(actual[i], expected[i]);
        }
    }

    @Test
    public void shouldMutateCorrectly(){
        //given
        int[] g1 = {1,1,1,1,1,1};

        //when
        int[] actual = meosisService.mutate(g1, 2,2);
        int[] expected = {1,1,2,1,1,1};

        //then
        assertTrue(actual.length == 6);
        for (int i : actual) {
            assertEquals(actual[i], expected[i]);
        }
    }

    @Test
    public void shouldMutateAndRetainEventInfo() {
        //given
        int[] g1 = {1111, 21111, 31111, 41111, 51111, 61111};
        BreedingService.mutationFreq = 1;

        for (int i : g1) {
            assertEquals(1111, i % 10000);
        }

        //when then
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < g1.length; j++) {
                int[] actual = meosisService.mutate(g1, j, 9999);

                assertEquals(actual[j] % 10000, g1[j] % 10000);
                assertEquals(actual[j] % 10000, 9999);

                // assert that the event information is retained
                if(j != 1) {
                    assertEquals(actual[j] / 10000, g1[j] / 10000);
                }
            }
        }

    }
}
