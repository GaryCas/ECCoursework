package translation;

import services.GetterService;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class EventCodeTranslator {


    public String getRunCode(){
        return "public void run() {while(true){";
    }

    public String getOnWallHitMethod() {
        return "public void onHitWall(HitWallEvent e) {System.out.println(\"getOnWallHitMethod\");";
    }

    public String getOnScannedRobotMethod() {
        return "public void onScannedRobot(ScannedRobotEvent e) {System.out.println(\"getOnScannedRobotMethod\");";
    }

    public String getOnHitByBulletMethod() {
        return "public void onHitByBullet(HitByBulletEvent e) {System.out.println(\"getOnHitByBulletMethod\");";
    }

    public String getOnBulletMissedMethod() {
        return "public void onBulletMissed(BulletMissedEvent e) {System.out.println(\"getOnBulletMissedMethod\");";
    }

    public String getOnBulletHitMethod() {
        return "public void onBulletHit(BulletHitEvent e) {System.out.println(\"getOnBulletHitMethod\");";
    }
}
