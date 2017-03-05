package translation.actionstrategies.adjustfirestrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class IncrementFirePowerStrategyTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        IncrementFirePowerStrategy incrementFirePowerStrategy = new IncrementFirePowerStrategy();
        // when
        String testString = incrementFirePowerStrategy.translateGenotype(100, 9);

        // then
        assertEquals("this.firePower = this.firePower++;", testString);
    }
}
