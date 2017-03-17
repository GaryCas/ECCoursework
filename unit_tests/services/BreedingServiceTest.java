package services;

import entities.BotEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import runners.ECRunner;
import utils.BotProvider;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.verify;

/**
 * Created by rd019985 on 02/03/2017.
 */
public class BreedingServiceTest {
    @Mock
    MeosisService mockMeosisService;

    BotProvider botProvider = new BotProvider();
    BotEntity[] botEntities = botProvider.setUpBots();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCompareCorrectly(){
        //given
        botEntities[0].setFitness(20.0);
        botEntities[1].setFitness(21.0);
        botEntities[2].setFitness(22.0);
        botEntities[3].setFitness(23.0);

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
        botEntities[0].setFitness(1.0);
        botEntities[1].setFitness(2.0);
        botEntities[2].setFitness(3.0);
        botEntities[3].setFitness(4.0);

        BotEntity[] smallerBotEntities = {
                botEntities[0],
                botEntities[1],
                botEntities[2],
                botEntities[3]
        };
        assertEquals(3, smallerBotEntities[3].getMemberID());
        assertEquals(2, smallerBotEntities[2].getMemberID());
        assertEquals(1, smallerBotEntities[1].getMemberID());
        assertEquals(0, smallerBotEntities[0].getMemberID());

        //when
        BotEntity[] testBotEntities = BreedingService.returnParents(smallerBotEntities);

        //then
        assertEquals(2, testBotEntities.length);

        assertEquals(3, testBotEntities[0].getMemberID());
        assertEquals(2, testBotEntities[1].getMemberID());
    }


    @Test
    public void shouldImplementMeosis(){
        //given
        BreedingService breedingService = new BreedingService();
        breedingService.setMeosisService(mockMeosisService);
        breedingService.setCrossoverFreq(1);

        //when
        BreedingService.doMeosis(botEntities[0],botEntities[1]);

        //then
       // verify(mockMeosisService).positionBasedCrossover((BotEntity) any(), (BotEntity) any(),anyInt());
       // verify(mockMeosisService).mutate((int[]) any(), anyInt(), anyInt());
    }

    @Test
    public void shouldCreateNewGenerationIntegrationTest(){
        //given
        BotProvider botProvider = new BotProvider();

        botEntities = botProvider.setUpBots();

        ECRunner.POP_SIZE = 10;
       BotEntity[] actualBotEntities;
       BotEntity[] survivorBotEntities = new BotEntity[ECRunner.POP_SIZE];
       BotEntity[] childBotEntities = new BotEntity[ECRunner.POP_SIZE];

        //when
        actualBotEntities = BreedingService.createNewGeneration(botEntities);

        for (int i = 0; i < actualBotEntities.length; i++) {
            if(actualBotEntities[i].isSurvivor()){
                survivorBotEntities[i] = actualBotEntities[i];
            } else {
                childBotEntities[i] = actualBotEntities[i];
            }
        }

        //then
        assertEquals(10, actualBotEntities.length);

        for (int i = 1; i < actualBotEntities.length; i++) {
            // generation number has incremented
            assertEquals(1, actualBotEntities[i]
                    .getMemberGen());

            // ID number is assigned correctly
            assertEquals(i, actualBotEntities[i]
                    .getMemberID());

            // name is assigned correctly
            assertTrue(actualBotEntities[i]
                    .getBotName().startsWith("botG1ID"));

            // genotype has be produced
            assertNotNull(actualBotEntities[i]
                    .getGenome());

            // genotype has been translated
            assertNotNull(actualBotEntities[i]
                    .getPhenotype());
        }


        // assert that crossover has happened with the child entities
        for (BotEntity childBotEntity : childBotEntities) {

            if(childBotEntity != null) {
                int unexpected = childBotEntity.getGenome()[0] * 6;
                int actual = 0;

                for (int i : childBotEntity.getGenome()) {
                    actual += i;
                }
                assertNotEquals(actual, unexpected);
                assertNotEquals("", childBotEntity.getPhenotype());
            }
        }

        // when 2
        BotEntity[] botEntitiesGen2 = new BotEntity[10];

        for (int i = 0; i < actualBotEntities.length; i++) {
            botEntitiesGen2[i] = actualBotEntities[i];
        }


        BotEntity[] actualBotEntitiesGen2 = BreedingService.createNewGeneration(botEntitiesGen2);

        survivorBotEntities = new BotEntity[ECRunner.POP_SIZE];
        childBotEntities =  new BotEntity[ECRunner.POP_SIZE];

        for (int i = 0; i < actualBotEntities.length; i++) {
            if(actualBotEntities[i].isSurvivor()){
                survivorBotEntities[i] = actualBotEntities[i];
            } else {
                childBotEntities[i] = actualBotEntities[i];
            }
        }

        // then 2
        assertEquals(10, actualBotEntitiesGen2.length);

        for (int i = 1; i < actualBotEntities.length; i++) {
            // generation number has incremented
            assertEquals(2, actualBotEntitiesGen2[i]
                    .getMemberGen());

            // ID number is assigned correctly
            assertEquals(i, actualBotEntitiesGen2[i]
                    .getMemberID());

            // name is assigned correctly
            assertTrue(actualBotEntitiesGen2[i]
                    .getBotName().startsWith("botG2ID"));

            // genotype has be produced
            assertNotNull(actualBotEntitiesGen2[i]
                    .getGenome());

            // genotype has been translated
            assertNotNull(actualBotEntitiesGen2[i]
                    .getPhenotype());
            assertNotEquals("",actualBotEntitiesGen2[i]
                    .getPhenotype());
        }

        ECRunner.POP_SIZE = 20;
    }




    /**
     * Rememeber that the breeding service needs to select which individuals should make it to the next generation
     * AND which individuals will become parents
     */

}
