package translation.actionstrategies.firestrategies;

import org.junit.Before;
import org.junit.Test;
import services.GetterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class FireNormalTest {


    @Before
    public void setUp(){
        GetterService.flushSB();
    }

    @Test
    public void shouldReturnThecorrectString(){
        // given
        FireNormal fireNormal = new FireNormal();
        // when
        String testString = fireNormal.translateAction(100, 9);

        // then
        assertEquals("fire(this.firepower);", testString);
    }
}
