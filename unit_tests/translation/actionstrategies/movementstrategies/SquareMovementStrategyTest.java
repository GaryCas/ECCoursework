package translation.actionstrategies.movementstrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class SquareMovementStrategyTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        SquareMovementStrategy squareMovementStrategy = new SquareMovementStrategy();
        // when
        String testString = squareMovementStrategy.translateGenotype(100, 9);

        // then
        assertEquals("turnLeft(90);ahead(900);this.pause=false;", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        SquareMovementStrategy squareMovementStrategy = new SquareMovementStrategy();
        // when
        String testString = squareMovementStrategy.translateGenotype(10, 9);

        // then
        assertEquals("turnLeft(90);ahead(90);this.pause=false;", testString);
    }
}
