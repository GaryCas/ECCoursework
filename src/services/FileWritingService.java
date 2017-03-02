package services;

import entities.BotEntity;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class FileWritingService {


    public static void outputRunData(int round, double avgFit, double bestFit, double avgNode, String bestBotName){
        FileWriter dataStream;
        try {
            // store all info in single file
//            dataStream = new FileWriter(BotEntity.PATH+"\\run_data.txt", true);
//            dataStream.write(round+"\t"+avgFit+"\t"+bestFit+"\t"+avgNode+"\t"+bestNode+"\n");
//            dataStream.close();

            // store each variable in its own file (for graphs)
            dataStream = new FileWriter(BotEntity.PATH+"\\run_data_avgFitness.txt", true);
            dataStream.write(avgFit+"\n");
            dataStream.close();

            dataStream = new FileWriter(BotEntity.PATH+"\\run_data_bestFitness.txt", true);
            dataStream.write(bestFit+"\n");
            dataStream.close();

            dataStream = new FileWriter(BotEntity.PATH+"\\run_data_avgNodes.txt", true);
            dataStream.write(avgNode+"\n");
            dataStream.close();

//            dataStream = new FileWriter(BotEntity.PATH+"\\run_data_bestNodes.txt", true);
//            dataStream.write(bestNode+"\n");
//            dataStream.close();

            dataStream = new FileWriter(BotEntity.PATH+"\\run_data_candidates.txt", true);
            dataStream.write(bestBotName+"\n");
            dataStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
