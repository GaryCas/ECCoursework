package translation.actionstrategies.movementstrategies;

import org.junit.Before;
import org.junit.Test;
import services.GetterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class SquareMovementStrategyTest {
    @Before
    public void setUp(){
        GetterService.flushSB();
    }

    @Test
    public void shouldReturnThecorrectString(){
        // given
        SquareMovementStrategy squareMovementStrategy = new SquareMovementStrategy();
        // when
        String testString = squareMovementStrategy.translateAction(100, 9);

        // then
        assertEquals("turnLeft(90);ahead(900);this.pause=false;", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        SquareMovementStrategy squareMovementStrategy = new SquareMovementStrategy();
        // when
        String testString = squareMovementStrategy.translateAction(10, 9);

        // then
        assertEquals("turnLeft(90);ahead(90);this.pause=false;", testString);
    }
}
