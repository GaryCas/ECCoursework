package translation.actionstrategies.movementstrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class TurnAndMoveStrategyTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        TurnAndMoveStrategy turnAndMoveStrategy = new TurnAndMoveStrategy();
        // when
        String testString = turnAndMoveStrategy.translateGenotype(90, 9);

        // then
        assertEquals("turnLeft(90);ahead(810);this.pause=false;", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        TurnAndMoveStrategy turnAndMoveStrategy = new TurnAndMoveStrategy();
        // when
        String testString = turnAndMoveStrategy.translateGenotype(10, 9);

        // then
        assertEquals("turnLeft(10);ahead(90);this.pause=false;", testString);
    }
}
