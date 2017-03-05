package translation;

import services.GetterService;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class EventCodeTranslator {


    public String getRunCode(){
        return "public void run() {";
    }

    public String getOnWallHitMethod() {
        return "public void onHitWall(HitWallEvent e) {";
    }

    public String getOnScannedRobotMethod() {
        return "public void onScannedRobot(ScannedRobotEvent e) {";
    }

    public String getOnHitByBulletMethod() {
        return "public void onHitByBullet(HitByBulletEvent e) {";
    }

    public String getOnBulletMissedMethod() {
        return "public void onBulletMissed(BulletMissedEvent e) {";
    }

    public String getOnBulletHitMethod() {
        return "public void onBulletHit(BulletHitEvent e) {";
    }
}
