package entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import strategies.adjustfirestrategies.DecrementFirePowerStrategy;
import strategies.adjustfirestrategies.IncrementFirePowerStrategy;
import strategies.firestrategies.FireNormal;
import strategies.firestrategies.SmartFire;
import strategies.movementstrategies.SquareMovementStrategy;
import strategies.movementstrategies.TurnAndMoveStrategy;

import static entities.BotEntity.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotEntityTest {

    BotEntity botEntity;

    @Mock
    BotEntity.GeneInitialiser mockGeneInitialiser;

    @Mock
    BotEntity.GeneEvaluator mockGeneEvaluator;

    @Before
    public void setUp() {
        botEntity = new BotEntity(0,0);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConstructProperly() {
        //given

        //when
        BotEntity botEntity = new BotEntity(-1, 0);

        //then
        assertEquals(-1, botEntity.memberGen);
        assertEquals(0, botEntity.memberID);
    }

    @Test
    public void shouldTemplate() {
        //given

        //when

        //then
    }

    @Test
    public void shouldInvokeInitGenesProperly() {
        //given
        botEntity.setGeneInitialiser(mockGeneInitialiser);

        //when
        botEntity.initGene();

        //then
        verify(mockGeneInitialiser, times(1)).initEventsInt();
        verify(mockGeneInitialiser, times(1)).initActionsInt();
        verify(mockGeneInitialiser, times(1)).initValuesInt();
    }

    @Test
    public void shouldInitSingleGeneProperly() {
        //given

        //when
        int gene = botEntity.initGene();

        //then
        assertTrue(gene < 10000);
        assertTrue(gene > 0);
    }

    @Test
    public void shouldInitGenesProperly() {
        //given

        //when
        int[] genes = botEntity.initGenes();

        //then
        assertTrue(genes[0] < 10001);
        assertTrue(genes[0] > -1);

        assertTrue(genes[1] < 20001);
        assertTrue(genes[1] > 9999);

        assertTrue(genes[2] < 30001);
        assertTrue(genes[2] > 19999);

        assertTrue(genes[3] < 40001);
        assertTrue(genes[3] > 29999);

        assertTrue(genes[4] < 50001);
        assertTrue(genes[4] > 39999);

        assertTrue(genes[5] < 60001);
        assertTrue(genes[5] > 49999);
    }

    @Test
    public void shouldInitEventsIntProperly() {
        //given
        int i;

        for (int j = 0; j < 6; j++) {
            //when
            i = botEntity.geneInitialiser.initEventsInt();

            //then
            // test that the events encoding is teh fourth digit and between 0 and 4
            assertTrue("The Events gene should be greater than 0", i >= 0);
            assertTrue("The Events gene should be lesser than 5000", i < 60000);
            assertTrue("The Events gene should be a multiple of 1000", i % 10000 == 0);
        }
    }

    @Test
    public void shouldInterpretEventsIntProperly() {
        //given

        //when

        //then
        assertEquals(SquareMovementStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(111).getClass());
        assertEquals(TurnAndMoveStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(1111).getClass());

        assertEquals(SquareMovementStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(10111).getClass());
        assertEquals(TurnAndMoveStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(11111).getClass());

        assertEquals(SquareMovementStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(20111).getClass());
        assertEquals(TurnAndMoveStrategy.class,botEntity.geneEvaluator.getBehaviourStrategy(21111).getClass());

        assertEquals(FireNormal.class ,botEntity.geneEvaluator.getBehaviourStrategy(30111).getClass());
        assertEquals(SmartFire.class ,botEntity.geneEvaluator.getBehaviourStrategy(31111).getClass());

        assertEquals(IncrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(40111).getClass());
        assertEquals(DecrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(41111).getClass());

        assertEquals(IncrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(50111).getClass());
        assertEquals(DecrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(51111).getClass());
    }

    @Test
    public void shouldInitActionsIntProperly() {
        //given
        int i;

        //when
        for (int j = 0; j < 100; j++) {
            i = botEntity.geneInitialiser.initActionsInt();

            //then
            assertTrue(i <= 1000);
            assertTrue(i >= 0);
        }
    }

    @Test
    public void shouldInterpretActionsIntProperly() {
        //given

        //when
        int test = botEntity.geneEvaluator.extractActionNBase(1111);

        //then
        assertEquals(1000, test);
    }

    @Test
    public void shouldInitValuesIntProperly() {
        //given
        int i;

        //when
        for (int j = 0; j < 100; j++) {
            i = botEntity.geneInitialiser.initValuesInt();

            //then test that the values int is between 0 and 1000
            assertTrue(i < 1000);
            assertTrue(i >= 0);
        }
    }

    @Test
    public void shouldInterpretValuesIntProperly() {
        //given

        //when

        //then
    }

    @Test
    public void shouldInitGeneCorrectly() {

    }

//    @Test
//    public void shouldReturnPathOnCompile() throws IOException {
//        //given
//        BotEntity botEntity = new BotEntity(-1, 0);
//        String expectedString = BotEntity.PATH.concat("\\"+botEntity.getBotName()+".class");
//
//        //when
//        String testString = botEntity.compile();
//
//        //then
//        assertEquals(testString, expectedString);
//    }
}