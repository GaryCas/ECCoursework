package services;

import entities.BotEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class FileWritingService {

    /**
     * The method that publishes the result of a round, called at the end of each round.
     *
     * @param round
     * @param avgFit
     * @param bestFit
     * @param avgNode
     * @param bestBotName
     */
    public static void outputRunData(int round, double[] info, String filename, String path){
            // store each variable in its own file (for graphs)
            for (int i = 0; i < info.length; i++) {
                writeStatisticsToFile(round, info[i], filename, path);
            }
    }

    private static void writeStatisticsToFile(int round, double value, String filename, String path) {
        try {
            validateFile(filename, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter dataStream = new FileWriter(path+filename, true)) {
            dataStream.write("round " + round + " " +value+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateFile(String filename, String path) throws IOException {
        File file ;
        if(!new File(path+"\\"+filename).exists()){
           file = new File(path+"\\"+filename);
           file.createNewFile();
        }

    }

    public static void writeJavaFile(String code, String fileName, String PATH) {
        try(FileWriter dataStream = new FileWriter(PATH+"\\"+fileName, true)) {
            validateFile(fileName, PATH);
            dataStream.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeBot(BotEntity bestSoFar, String title, String PATH, String fileName) {
        try(FileWriter dataStream = new FileWriter(PATH+"\\"+fileName, true)) {
            validateFile(fileName, PATH);
            dataStream.write(title + " \n");
            dataStream.write("Name " + bestSoFar.getBotName()+ "\n");
            dataStream.write("Fitness " + bestSoFar.getFitness()+ "\n");
            dataStream.write("Genome " + bestSoFar.getGenome()+ "\n");
            dataStream.write("Phenome " + bestSoFar.getPhenotype()+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
