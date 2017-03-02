package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class ECRunnerTest {
    static int temp;

    @BeforeClass
    public static void setUpClass(){
        temp = ECRunner.POP_SIZE;
        ECRunner.POP_SIZE = 100;
    }

    @AfterClass
    public static void tearDownClass(){
        ECRunner.POP_SIZE = temp;
    }

    @Test
    public void shouldInitGenerationProperly(){
        //given
        long start = System.currentTimeMillis();
        ECRunner ecRunner = new ECRunner();

        assertNull(ecRunner.currentGeneration[0]);
        assertNull(ecRunner.currentGeneration[50]);

        //when
        ecRunner.initGeneration();

        long end = System.currentTimeMillis();

        //then
        assertNotNull(ecRunner.currentGeneration[0]);
        assertNotNull(ecRunner.currentGeneration[50]);

        assertEquals("botG0ID:0",ecRunner.currentGeneration[0].getBotName());

        System.out.println("that took " + (end - start) + " miliseconds ");
    }

    @Test
    public void shouldTemplate(){
        //given
        //when
        //then
    }
}
