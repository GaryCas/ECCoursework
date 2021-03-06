package entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import translation.actionstrategies.adjustfirestrategies.DecrementFirePowerStrategy;
import translation.actionstrategies.adjustfirestrategies.IncrementFirePowerStrategy;
import translation.actionstrategies.firestrategies.FireNormal;
import translation.actionstrategies.firestrategies.SmartFire;
import translation.actionstrategies.movementstrategies.SquareMovementStrategy;
import translation.actionstrategies.movementstrategies.TurnAndMoveStrategy;
import translation.actionstrategies.runstrategy.TurnGunRightALittleStrategy;
import translation.actionstrategies.runstrategy.TurnGunRightAlotStrategy;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotEntityTest {

    BotEntity botEntity;

    @Mock
    BotEntity.GeneInitialiser mockGeneInitialiser;

    @Mock
    BotEntity.GeneEvaluator mockGeneEvaluator;

    @Before
    public void setUp() {
        botEntity = new BotEntity(0,0);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConstructProperly() {
        //given

        //when
        BotEntity botEntity = new BotEntity(-1, 0);

        //then
        assertEquals(-1, botEntity.memberGen);
        assertEquals(0, botEntity.memberID);
    }

    @Test
    public void shouldTemplate() {
        //given

        //when

        //then
    }

    @Test
    public void shouldInvokeInitGenesProperly() {
        //given
        botEntity.setGeneInitialiser(mockGeneInitialiser);

        //when
        botEntity.initGene();

        //then
        verify(mockGeneInitialiser, times(1)).initEventsInt();
        verify(mockGeneInitialiser, times(1)).initActionsInt();
        verify(mockGeneInitialiser, times(1)).initValuesInt();
    }

    @Test
    public void shouldInitSingleGeneProperly() {
        //given

        //when
        int gene = botEntity.initGene();

        //then
        assertTrue(gene < 10000);
        assertTrue(gene > 0);
    }

    @Test
    public void shouldInitGenesProperly() {
        //given

        //when
        int[] genes = botEntity.initGenes();

        //then
        assertTrue(genes[0] < 10001);
        assertTrue(genes[0] > -1);

        assertTrue(genes[1] < 20001);
        assertTrue(genes[1] > 9999);

        assertTrue(genes[2] < 30001);
        assertTrue(genes[2] > 19999);

        assertTrue(genes[3] < 40001);
        assertTrue(genes[3] > 29999);

        assertTrue(genes[4] < 50001);
        assertTrue(genes[4] > 39999);

        assertTrue(genes[5] < 60001);
        assertTrue(genes[5] > 49999);
    }

    @Test
    public void shouldInitEventsIntProperly() {
        //given
        int i;

        for (int j = 0; j < 6; j++) {
            //when
            i = botEntity.geneInitialiser.initEventsInt();

            //then
            // test that the events encoding is teh fourth digit and between 0 and 4
            assertTrue("The Events gene should be greater than 0", i >= 0);
            assertTrue("The Events gene should be lesser than 5000", i < 60000);
            assertTrue("The Events gene should be a multiple of 1000", i % 10000 == 0);
        }
    }

    @Test
    public void shouldInterpretEventsIntProperly() {
        //given

        //when

        //then
        assertEquals(TurnGunRightAlotStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(0, 100).getClass());
        assertEquals(TurnGunRightALittleStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(0, 1111).getClass());

        assertEquals(SquareMovementStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(10000, 111).getClass());
        assertEquals(TurnAndMoveStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(10000, 1111).getClass());

        assertEquals(SquareMovementStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(20000, 111).getClass());
        assertEquals(TurnAndMoveStrategy.class,botEntity.geneEvaluator.getBehaviourStrategy(20000, 1111).getClass());

        assertEquals(FireNormal.class ,botEntity.geneEvaluator.getBehaviourStrategy(30000, 111).getClass());
        assertEquals(SmartFire.class ,botEntity.geneEvaluator.getBehaviourStrategy(30000, 1111).getClass());

        assertEquals(IncrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(40000, 111).getClass());
        assertEquals(DecrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(40000, 1111).getClass());

        assertEquals(IncrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(50000, 111).getClass());
        assertEquals(DecrementFirePowerStrategy.class ,botEntity.geneEvaluator.getBehaviourStrategy(50000, 1111).getClass());
    }

    @Test
    public void shouldInitActionsIntProperly() {
        //given
        int i;

        //when
        for (int j = 0; j < 100; j++) {
            i = botEntity.geneInitialiser.initActionsInt();

            //then
            assertTrue(i <= 1000);
            assertTrue(i >= 0);
        }
    }

    @Test
    public void shouldInterpretActionsIntProperly() {
        //given

        //when
        int test = botEntity.geneEvaluator.extractActionNBase(1111);

        //then
        assertEquals(1000, test);
    }

    @Test
    public void shouldInitValuesIntProperly() {
        //given
        int i;

        //when
        for (int j = 0; j < 100; j++) {
            i = botEntity.geneInitialiser.initValuesInt();

            //then test that the values int is between 0 and 1000
            assertTrue(i < 1000);
            assertTrue(i >= 0);
        }
    }

    @Test
    public void shouldExtractSmallValueInt() {
        //given

        //when

        //then
        assertEquals(1,botEntity.geneEvaluator.extractSmallValue(11111));
        assertEquals(11,botEntity.geneEvaluator.extractLargeValue(11111));

    }

    @Test
    public void shouldTranslateGene111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(111);

        //then
    //    assertEquals("public void run() {turnLeft(90);ahead(11);this.pause=false;}", testString);
    }

    @Test
    public void shouldTranslateGene1111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(1111);

        //then
//        assertEquals("public void run() {turnLeft(11);ahead(11);this.pause=false;}", testString);
    }

    @Test
    public void shouldTranslateGene10111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(10111);

        //then
        assertEquals("public void onHitByBullet(HitByBulletEvent e) {System.out.println(\"getOnHitByBulletMethod\");turnLeft(90);ahead(11);this.pause=false;turnRadarRight(1.0);}", testString);
    }

    @Test
    public void shouldTranslateGene11111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(11111);

        //then
        assertEquals("public void onHitByBullet(HitByBulletEvent e) {System.out.println(\"getOnHitByBulletMethod\");turnLeft(11);ahead(11);this.pause=false;turnRadarRight(11);}", testString);
    }

    @Test
    public void shouldTranslateGene20111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(20111);

        //then
        assertEquals("public void onHitWall(HitWallEvent e) {System.out.println(\"getOnWallHitMethod\");turnLeft(90);ahead(11);this.pause=false;turnRadarRight(1.0);}", testString);
    }

    @Test
    public void shouldTranslateGene21111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(21111);

        //then
        assertEquals("public void onHitWall(HitWallEvent e) {System.out.println(\"getOnWallHitMethod\");turnLeft(11);ahead(11);this.pause=false;turnRadarRight(11);}", testString);
    }

    @Test
    public void shouldTranslateGene30111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(30111);

        //then
        assertEquals("public void onScannedRobot(ScannedRobotEvent e) {System.out.println(\"getOnScannedRobotMethod\");fire(this.firePower);}", testString);
    }

    @Test
    public void shouldTranslateGene31111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(31121);

        //then
        assertEquals("public void onScannedRobot(ScannedRobotEvent e) {System.out.println(\"getOnScannedRobotMethod\");if(e.getDistance() >12 || getEnergy() < 15){fire(this.firePower);}else if (e.getDistance() > 6) {fire(this.firePower+1);}else{fire(this.firePower+2);}}", testString);
    }

    @Test
    public void shouldTranslateGene40111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(40111);

        //then
        assertEquals("public void onBulletHit(BulletHitEvent e) {System.out.println(\"getOnBulletHitMethod\");this.firePower = this.firePower++;}", testString);
    }

    @Test
    public void shouldTranslateGene41111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(41121);

        //then
        assertEquals("public void onBulletHit(BulletHitEvent e) {System.out.println(\"getOnBulletHitMethod\");this.firePower = this.firePower--;}", testString);
    }

    @Test
    public void shouldTranslateGene50111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(50111);

        //then
        assertEquals("public void onBulletMissed(BulletMissedEvent e) {System.out.println(\"getOnBulletMissedMethod\");this.firePower = this.firePower++;}", testString);
    }

    @Test
    public void shouldTranslateGene51111(){
        //given

        //when
        String testString = botEntity.geneEvaluator.translateGene(51121);

        //then
        assertEquals("public void onBulletMissed(BulletMissedEvent e) {System.out.println(\"getOnBulletMissedMethod\");this.firePower = this.firePower--;}", testString);
    }

    /**
     * Need to bad event and action alleles
     *
     * event 6-9
     * action 2-9
     *
     */

    // SQMovement turnLeft(90);ahead(11);this.pause=false;}
    // FireNormal {fire(this.firepower);}
    // IncrementP this.firePower = this.firePower++;
    // decrementP this.firePower = this.firePower--;

    @Test
    public void shouldTranslateGenoTypeWith0Actions(){
        // given
        int[] genome = {111, 10111, 20111, 30111, 40111, 50111};

        BotEntity botEntity = new BotEntity(0,0);
        botEntity.setGenome(genome);

        // when
        String phenotype = botEntity.translateGenotype();
        String expectedPhenotype = "public void run() {while(true){turnGunRight(11);}}public void onHitByBullet(HitByBulletEvent e) {System.out.println(\"getOnHitByBulletMethod\");turnLeft(90);ahead(11);this.pause=false;turnRadarRight(1.0);}public void onHitWall(HitWallEvent e) {System.out.println(\"getOnWallHitMethod\");turnLeft(90);ahead(11);this.pause=false;turnRadarRight(1.0);}public void onScannedRobot(ScannedRobotEvent e) {System.out.println(\"getOnScannedRobotMethod\");fire(this.firePower);}public void onBulletHit(BulletHitEvent e) {System.out.println(\"getOnBulletHitMethod\");this.firePower = this.firePower++;}public void onBulletMissed(BulletMissedEvent e) {System.out.println(\"getOnBulletMissedMethod\");this.firePower = this.firePower++;}";

        // then
        assertEquals("Phenotype did not translate properly",
                expectedPhenotype,
                phenotype);

    }

    // TMStrategy turnLeft(11);ahead(11);this.pause=false;}
    // SmartNormal if(robotDistance >11 || getEnergy() < 15){fire(this.firePower);}else if (robotDistance > 5) {fire(this.firePower+1);}else{fire(this.firePower+2);}
    // IncrementP this.firePower = this.firePower++;
    // decrementP this.firePower = this.firePower--;

    @Test
    public void shouldTranslateGenoTypeWith1Actions(){
        // given
        int[] genome = {1111, 11111, 21111, 31111, 41111, 51111};

        BotEntity botEntity = new BotEntity(0,0);
        botEntity.setGenome(genome);

        // when
        String phenotype = botEntity.translateGenotype();
        String expectedPhenotype = "public void run() {while(true){turnGunRight(1);}}public void onHitByBullet(HitByBulletEvent e) {System.out.println(\"getOnHitByBulletMethod\");turnLeft(11);ahead(11);this.pause=false;turnRadarRight(11);}public void onHitWall(HitWallEvent e) {System.out.println(\"getOnWallHitMethod\");turnLeft(11);ahead(11);this.pause=false;turnRadarRight(11);}public void onScannedRobot(ScannedRobotEvent e) {System.out.println(\"getOnScannedRobotMethod\");if(e.getDistance() >11 || getEnergy() < 15){fire(this.firePower);}else if (e.getDistance() > 5) {fire(this.firePower+1);}else{fire(this.firePower+2);}}public void onBulletHit(BulletHitEvent e) {System.out.println(\"getOnBulletHitMethod\");this.firePower = this.firePower--;}public void onBulletMissed(BulletMissedEvent e) {System.out.println(\"getOnBulletMissedMethod\");this.firePower = this.firePower--;}";

        // then
        assertEquals("Phenotype did not translate properly",
                expectedPhenotype,
                phenotype);

    }


//    @Test
//    public void shouldReturnPathOnCompile() throws IOException {
//        //given
//        BotEntity botEntity = new BotEntity(-1, 0);
//        String expectedString = BotEntity.PATH.concat("\\"+botEntity.getBotName()+".class");
//
//        //when
//        String testString = botEntity.compile();
//
//        //then
//        assertEquals(testString, expectedString);
//    }
}
