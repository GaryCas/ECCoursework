package translation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class EventCodeTranslatorTest {

    EventCodeTranslator eventCodeTranslator;

    @Test
    public void shouldgetRunCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getRunCode();

        //then
        assertEquals("public void run() {", testString);
    }

    @Test
    public void shouldGetOnBulletHitCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getOnBulletHitMethod();

        //then
        assertEquals("public void onBulletHit(BulletHitEvent e) {", testString);
    }

    @Test
    public void shouldGetOnBulletMissCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getOnBulletMissedMethod();

        //then
        assertEquals("public void onBulletMissed(BulletMissedEvent e) {", testString);
    }

    @Test
    public void shouldGetOnHitByBulletCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getOnHitByBulletMethod();

        //then
        assertEquals("public void onHitByBullet(HitByBulletEvent e) {", testString);
    }


    @Test
    public void shouldGetOnScannedRobotCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getOnScannedRobotMethod();

        //then
        assertEquals("public void onScannedRobot(ScannedRobotEvent e) {", testString);
    }

    @Test
    public void shouldGetOnWallHitCode(){
        //given
        eventCodeTranslator = new EventCodeTranslator();

        //when
        String testString = eventCodeTranslator.getOnWallHitMethod();

        //then
        assertEquals("public void onHitWall(HitWallEvent e) {", testString);
    }
}
