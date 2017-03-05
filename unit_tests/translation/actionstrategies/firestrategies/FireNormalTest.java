package translation.actionstrategies.firestrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class FireNormalTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        FireNormal fireNormal = new FireNormal();
        // when
        String testString = fireNormal.translateGenotype(100, 9);

        // then
        assertEquals("fire(this.firepower)", testString);
    }
}
