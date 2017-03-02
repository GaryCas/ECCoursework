package entities;

import org.junit.Test;
import services.BreedingService;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotEntityTest {

    @Test
    public void shouldConstructProperly(){
        //given

        //when
        BotEntity botEntity = new BotEntity(-1, 0);

        //then
        assertEquals(-1, botEntity.memberGen);
        assertEquals(0, botEntity.memberID);
    }

    @Test
    public void shouldTemplate(){
        //given

        //when

        //then
    }

    @Test
    public void shouldInitEventsIntProperly(){
        //given
        BotEntity botEntity = new BotEntity(0,0);

        int i;

        for(int j = 0; j < 6g; j++) {
            //when
            i = botEntity.initEventsInt();

            //then
            // test that the events encoding is teh fourth digit and between 0 and 4
            assertTrue("The Events gene should be greater than 0", i >= 0);
            assertTrue("The Events gene should be lesser than 5000", i < 60000);
            assertTrue("The Events gene should be a multiple of 1000", i % 10000 == 0);
        }
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
