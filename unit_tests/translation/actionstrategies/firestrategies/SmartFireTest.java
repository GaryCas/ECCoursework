package translation.actionstrategies.firestrategies;

import org.junit.Before;
import org.junit.Test;
import services.GetterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class SmartFireTest {

    @Before
    public void setUp(){
        GetterService.flushSB();
    }

    @Test
    public void shouldReturnThecorrectString(){
        // given
        SmartFire smartFire = new SmartFire();
        // when
        String testString = smartFire.translateAction(100, 9);

        // then
        assertEquals("if(robotDistance >100 || getEnergy() < 15){fire(this.firePower);}else if (robotDistance > 50) {fire(this.firePower+1);}else{fire(this.firePower+2);}", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        SmartFire smartFire = new SmartFire();
        // when
        String testString = smartFire.translateAction(200, 9);

        // then
        assertEquals("if(robotDistance >200 || getEnergy() < 15){fire(this.firePower);}else if (robotDistance > 100) {fire(this.firePower+1);}else{fire(this.firePower+2);}", testString);
    }
}
