package entities;

import runners.ECRunner;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotCompiler {

    // These 3 static Strings contain specified information of robocode paths
	/* FYI : my robocode path is "D:\\robocode\\"
	 *		 my folder for robots is named in "D:\\robocode\\robots\\robotevolution\\"
	 *		 my package for robots MUST be named after previous folder (robotevolution)
	 */
    public static String _path = new String("D:\\robocode\\robots\\robotevolution");
    public static String _package = new String("robotevolution");
    public static String _jar = new String("D:\\robocode\\libs\\robocode.jar;");

    String _evolutionName;
    int _generation;
    String _populationId;
    Bot _bot;
    double _botFitness;

    public BotCompiler(String evolutionName, int generation, int populationId, Bot bot) {
        _evolutionName = evolutionName;
        _generation = generation;
        _populationId = new Integer(populationId).toString();
        _bot = bot;
    }

    public static String GetBotPackage() {
        return _package;
    }

    // Returns a name of a bot with specified information
    public static String GetBotName(String evolutionName, int generation, int populationId) {
        return evolutionName + "_" + generation + "_" + populationId;
    }

    // Returns a name of file for a bot with specified information
    public static String GetBotFileName(String evolutionName, int generation, int populationId) {
        return _path + "\\" + GetBotName(evolutionName, generation, populationId);
    }

    // Returns a name of a bot with specified information
    public static String GetBotName(String evolutionName, int generation, String populationId) {
        return evolutionName + "_" + generation + "_" + populationId;
    }

    // Returns a name of a bot with specified information
    public static String GetBotFileName(String evolutionName, int generation, String populationId) {
        return _path + "\\" + GetBotName(evolutionName, generation, populationId);
    }

//    // Compiles the generation of bots
//    public void CompileBots(int generation, Bot[] bots){
//        for (int populationId = 0; populationId < ECRunner.POP_SIZE; ++populationId){
//            BotCompiler botcompiler = new BotCompiler(_evolutionName, generation, populationId,  bots[populationId]);
//            botcompiler.createBot();
//        }
//    }

    // Creates and compiles the bot files
    public void createBot() {
        String botSourceCode;

        // create code
        botSourceCode = CreateCode();

        // write code into .java file
        //WriteCode(botSourceCode);

        // compile .java file into .class file
        Compile();
    }

    // Returns a name of the bot
    private String GetBotName() {
        return GetBotName(_evolutionName, _generation, _populationId);
    }

    // Returns a name of a file that should contain source code of the bot
    private String GetBotFileName() {
        return GetBotFileName(_evolutionName, _generation, _populationId);
    }

    // Returns source code for the bot as String
    private String CreateCode() {
        StringBuilder sb = new StringBuilder();
        {
            // imports
            sb.append("package " + (_package) + ";");
            sb.append("\nimport robocode.*;");
            sb.append("\nimport robocode.util.Utils;");
            sb.append("\nimport java.awt.Color;\n");
            sb.append("\n");
            sb.append("\npublic class " + (GetBotName()) + " extends AdvancedRobot { \n ");
            sb.append("\n");

            // variables declaration

            // run ~ main method of robot
            {
                sb.append("public void run() { \n");
                sb.append("\n");
                sb.append(_bot.GetRunMethod());
                sb.append("} \n");
            }
            sb.append("\n");

            // Event listeners

            // onScannedRobot
            {
                sb.append("\n	public void onScannedRobot(ScannedRobotEvent e) { \n ");
                sb.append("\n");
                sb.append(_bot.GetOnScannedRobot());
                sb.append("} \n");
            }
            sb.append("\n");

            // onHitByBullet
            {
                sb.append("public void onHitByBullet(HitByBulletEvent e) { \n");
                sb.append("\n");
                sb.append(_bot.GetOnHitByBullet());
                sb.append("} \n");
            }
            sb.append("\n");

            // onHitWall
            {
                sb.append("public void onHitWall(HitWallEvent e) { \n");
                sb.append("\n");
                sb.append(_bot.GetOnHitWall());
                sb.append("} \n");
            }
            sb.append("\n");

            // onHitRobot
            {
                sb.append("public void onHitRobot(HitWallEvent e) { \n");
                sb.append("\n");
                sb.append(_bot.GetOnHitRobot());
                sb.append("} \n");
            }
            sb.append("\n");

            sb.append("}");
        }
        return sb.toString();
    }

//    // Writes _botSourceCode into .java File
//    private void WriteCode(String botSourceCode) {
//        try {
//            FileWriter fstream = new FileWriter(GetBotFileName() + ".java");
//            BufferedWriter out = new BufferedWriter(fstream);
//            out.write(botSourceCode);
//            out.close();
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }
//    }

    // Compiles bot from .java file into .class file
    public void Compile() {
        try {
            Execute("javac -cp " + _jar + " " + (GetBotFileName() + ".java"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Executes command
    private void Execute(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        if (process.exitValue() != 0)
            System.out.println(command + "exited with value " + process.exitValue());
    }
}
