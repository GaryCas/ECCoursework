package translation.actionstrategies.firestrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class SmartFireTest {

    @Test
    public void shouldReturnThecorrectString(){
        // given
        SmartFire smartFire = new SmartFire();
        // when
        String testString = smartFire.translateGenotype(100, 9);

        // then
        assertEquals("if(robotDistance >100 || getEnergy() < 15){fire(this.firePower);}else if (robotDistance > 50) {fire(this.firePower+1);}else{fire(this.firePower+2);}", testString);
    }

    @Test
    public void shouldReturnThecorrectString2(){
        // given
        SmartFire smartFire = new SmartFire();
        // when
        String testString = smartFire.translateGenotype(200, 9);

        // then
        assertEquals("if(robotDistance >200 || getEnergy() < 15){fire(this.firePower);}else if (robotDistance > 100) {fire(this.firePower+1);}else{fire(this.firePower+2);}", testString);
    }
}
