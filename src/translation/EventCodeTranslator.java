package translation;

import services.GetterService;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class EventCodeTranslator {

    StringBuilder stringBuilder;

    public EventCodeTranslator() {
        this.stringBuilder = GetterService.getStringBuilder();
    }

    public String getRunCode(){
        stringBuilder.append("public void run() {");
        return stringBuilder.toString();
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
