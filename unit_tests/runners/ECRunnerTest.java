package runners;

import entities.ApplicationVariables;
import entities.BotEntity;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.BotProvider;
import utils.SampleProvider;
import utils.Utils;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunnerTest {
    static int temp;
    ECRunner ecRunner;



    String[] testFileNames = {ApplicationVariables.UTPATH+"\\"+"Test.txt",
            ApplicationVariables.UTPATH+"\\"+"Test1.java",
            ApplicationVariables.UTPATH+"\\"+"Test2.java",
            ApplicationVariables.UTPATH+"\\"+"test.java"
    };
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


    @Mock
    BattleRunner battleRunner;

    @BeforeClass
    public static void setUpClass(){
        temp = ECRunner.POP_SIZE;
        ECRunner.POP_SIZE = 100;
    }

    @Before
    public void setUp() throws IOException {
        ecRunner = new ECRunner();
        MockitoAnnotations.initMocks(this);
        Utils.createFiles(testFileNames);
    }

    @After
    public void tearDown(){
        Utils.deleteFiles(testFileNames);
        Utils.deleteFiles(justDeleteFileNames);
    }

    @AfterClass
    public static void tearDownClass(){
        ECRunner.POP_SIZE = temp;
    }

    @Test
    public void shouldInitGenerationProperlyNames(){
        //given
        int length = 0;
        for (BotEntity botEntity : ecRunner.newGeneration) {
            if(botEntity != null){
                length++;
            }
        }

        assertEquals(0,length);


        //when
        ecRunner.initGeneration();

        //then
        for (int i = 0; i < ecRunner.newGeneration.length; i++) {
            assertEquals("botG"+i+"ID"+i,ecRunner.newGeneration[i].getBotName());
        }
    }

    @Test
    public void shouldInitGenerationProperlyGenome(){
        //given
        int length = 0;
        for (BotEntity botEntity : ecRunner.newGeneration) {
            if(botEntity != null){
                length++;
            }
        }

        assertEquals(0,length);

        //when
        ecRunner.initGeneration();


        length = 0;
        for (BotEntity botEntity : ecRunner.newGeneration) {
            if(botEntity != null){
                length++;
            }
        }

        assertEquals(100,length);

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
    public void shouldImplementRunBattle(){
        //given
        ECRunner ecRunner = new ECRunner();
        BotProvider botProvider = new BotProvider();
        ecRunner.setArena(battleRunner);
        ecRunner.setNewGeneration(botProvider.setUpBots());

        //when
        ecRunner.runBattle(SampleProvider.sittingDucks, ecRunner.getGenbotNames(botProvider.setUpBots()));

        //then
        verify(battleRunner, times(1)).runOneOnOneBattle(any(String[].class), any(String[].class), anyInt());
    }

    @Test
    public void shouldDoECLoop(){
        //given
        ECRunner ecRunner = new ECRunner();
        BotProvider botProvider = new BotProvider();
        ecRunner.setNewGeneration(botProvider.setUpBotsMini());

        //when
        ecRunner.ECLoop(ApplicationVariables.ROBOTPATH);

        //then
      }

    @Test
    public void shouldTemplate(){
        //given
        //when
        //then
    }
}
