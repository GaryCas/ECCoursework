package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    public static void outputRunData(int round, double[] info, String[] filenames, String path){
            // store each variable in its own file (for graphs)
            for (int i = 0; i < info.length; i++) {
                writeStatisticsToFile(round, info[i], filenames[i], path);
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
}
