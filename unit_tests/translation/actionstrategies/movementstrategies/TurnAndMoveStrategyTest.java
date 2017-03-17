package translation.actionstrategies.movementstrategies;

import org.junit.Before;
import org.junit.Test;
import services.GetterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class TurnAndMoveStrategyTest {
    @Before
    public void setUp(){
        GetterService.flushSB();
    }

    @Test
    public void shouldReturnThecorrectString(){
        // given
        TurnAndMoveStrategy turnAndMoveStrategy = new TurnAndMoveStrategy();
        // when
        String testString = turnAndMoveStrategy.translateAction(90, 9);

        // then
        assertEquals("turnLeft(90);ahead(810);this.pause=false;turnRadarRight(90);", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        TurnAndMoveStrategy turnAndMoveStrategy = new TurnAndMoveStrategy();
        // when
        String testString = turnAndMoveStrategy.translateAction(10, 9);

        // then
        assertEquals("turnLeft(10);ahead(90);this.pause=false;turnRadarRight(10);", testString);
    }
}
