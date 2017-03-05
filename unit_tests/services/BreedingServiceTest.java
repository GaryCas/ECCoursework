package services;

import entities.BotEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 02/03/2017.
 */
public class BreedingServiceTest {
    BotEntity b1;
    BotEntity b2;
    BotEntity b3;
    BotEntity b4;

    @Before
    public void setUp(){
        b1 = new BotEntity(0, 0);
        b2 = new BotEntity(0, 1);
        b3 = new BotEntity(0, 2);
        b4 = new BotEntity(0, 3);

        b1.setFitness(20.0);
        b2.setFitness(21.0);
        b3.setFitness(22.0);
        b4.setFitness(23.0);
    }

    @Test
    public void shouldCompareCorrectly(){
        //given
        BotEntity b1 = new BotEntity(0, 0);
        BotEntity b2 = new BotEntity(0, 1);
        BotEntity b3 = new BotEntity(0, 2);
        BotEntity b4 = new BotEntity(0, 3);
        b1.setFitness(20.0);
        b2.setFitness(21.0);
        b3.setFitness(22.0);
        b4.setFitness(23.0);
        BotEntity[] botEntities = {b1, b2, b3, b4};

        assertEquals(0, botEntities[0].getMemberID());
        assertEquals(1, botEntities[1].getMemberID());
        assertEquals(2, botEntities[2].getMemberID());
        assertEquals(3, botEntities[3].getMemberID());

        //when
        BotEntity[] testBotEntities = BreedingService.sortByFitness(botEntities);

        //then
        assertEquals(3, testBotEntities[0].getMemberID());
        assertEquals(2, testBotEntities[1].getMemberID());
        assertEquals(1, testBotEntities[2].getMemberID());
        assertEquals(0, testBotEntities[3].getMemberID());
    }

    @Test
    public void shouldReturnStrongest(){
        //given
        BotEntity[] botEntities = {b4, b3, b2, b1};

        assertEquals(3, botEntities[0].getMemberID());
        assertEquals(2, botEntities[1].getMemberID());
        assertEquals(1, botEntities[2].getMemberID());
        assertEquals(0, botEntities[3].getMemberID());

        //when
        BotEntity[] testBotEntities = BreedingService.returnStrongest(botEntities);

        //then
        assertEquals(2, testBotEntities.length);

        assertEquals(3, testBotEntities[0].getMemberID());
        assertEquals(2, testBotEntities[1].getMemberID());
    }

    /**
     * Rememeber that the breeding service needs to select which individuals should make it to the next generation
     * AND which individuals will become parents
     */

}
