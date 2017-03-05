package translation.actionstrategies.adjustfirestrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class DecrementFirePowerStrategyTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        DecrementFirePowerStrategy decrementFirePowerStrategy = new DecrementFirePowerStrategy();
        // when
        String testString = decrementFirePowerStrategy.translateGenotype(100, 9);

        // then
        assertEquals("this.firePower = this.firePower--;", testString);
    }
}
