package entities;

import runners.ECRunner;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BotCompiler {

//    // These 3 static Strings contain specified information of robocode paths
//	/* FYI : my robocode path is "D:\\robocode\\"
//	 *		 my folder for robots is named in "D:\\robocode\\robots\\robotevolution\\"
//	 *		 my package for robots MUST be named after previous folder (robotevolution)
//	 */
//
//    String _evolutionName;
//    int _generation;
//    String _populationId;
//    Bot _bot;
//    double _botFitness;
//
//    public BotCompiler(String evolutionName, int generation, int populationId, Bot bot) {
//        _evolutionName = evolutionName;
//        _generation = generation;
//        _populationId = new Integer(populationId).toString();
//        _bot = bot;
//    }
//
//
//    // Returns a name of a bot with specified information
//    public static String GetBotName(String evolutionName, int generation, int populationId) {
//        return evolutionName + "_" + generation + "_" + populationId;
//    }
//
//    // Returns a name of file for a bot with specified information
//    public static String GetBotFileName(String evolutionName, int generation, int populationId) {
//        return _path + "\\" + GetBotName(evolutionName, generation, populationId);
//    }
//
//    // Returns a name of a bot with specified information
//    public static String GetBotName(String evolutionName, int generation, String populationId) {
//        return evolutionName + "_" + generation + "_" + populationId;
//    }
//
//    // Returns a name of a bot with specified information
//    public static String GetBotFileName(String evolutionName, int generation, String populationId) {
//        return _path + "\\" + GetBotName(evolutionName, generation, populationId);
//    }
//
////    // Compiles the generation of bots
//    public void CompileBots(int generation, BotEntity[] bots){
//        for (int populationId = 0; populationId < ECRunner.POP_SIZE; ++populationId){
//            BotCompiler botcompiler = new BotCompiler(_evolutionName, generation, populationId,  bots[populationId]);
//            botcompiler.createBot(bots[populationId]);
//        }
//    }
//
//    // Creates and compiles the bot files
//    public void createBot(BotEntity bot) {
//        String botSourceCode;
//
//        // create code
//        botSourceCode = bot.setCode();
//
//        // write code into .java file
//
//
//        // compile .java file into .class file
//       // Compile();
//    }
//
//    // Returns a name of the bot
//    private String GetBotName() {
//        return GetBotName(_evolutionName, _generation, _populationId);
//    }
//
//    // Returns a name of a file that should contain source code of the bot
//    private String GetBotFileName() {
//        return GetBotFileName(_evolutionName, _generation, _populationId);
//    }
//
////    // Writes _botSourceCode into .java File
////    private void WriteCode(String botSourceCode) {
////        try {
////            FileWriter fstream = new FileWriter(GetBotFileName() + ".java");
////            BufferedWriter out = new BufferedWriter(fstream);
////            out.write(botSourceCode);
////            out.close();
////        } catch (Exception e) {
////            System.err.println("Error: " + e.getMessage());
////        }
////    }
//
//    // Compiles bot from .java file into .class file
//


}
