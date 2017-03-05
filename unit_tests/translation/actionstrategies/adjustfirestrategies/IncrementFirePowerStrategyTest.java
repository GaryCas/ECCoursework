package translation.actionstrategies.adjustfirestrategies;

import org.junit.Before;
import org.junit.Test;
import services.GetterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class IncrementFirePowerStrategyTest {

    @Before
    public void setUp(){
        GetterService.flushSB();
    }

    @Test
    public void shouldReturnThecorrectString(){
        // given
        IncrementFirePowerStrategy incrementFirePowerStrategy = new IncrementFirePowerStrategy();
        // when
        String testString = incrementFirePowerStrategy.translateAction(100, 9);

        // then
        assertEquals("this.firePower = this.firePower++;", testString);
    }
}
