package services;

import entities.BotEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        meosisService.positionBasedCrossover(b1,b2,3);

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
}
