package services;

import entities.ApplicationVariables;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rd019985 on 09/03/2017.
 */
public class CompilationService {


    public static void Compile(String path, String filename) {
        try {
            Execute("javac -cp " + ApplicationVariables.JARS + ""+ " " + path+"\\" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Executes command
    private static void Execute(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        if (process.exitValue() != 0)
            System.out.println(command + "exited with value " + process.exitValue());
    }

    public static ArrayList<String> getJavaFiles(String path){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if(listOfFiles[i].getName().endsWith(".java")) {
                    fileNames.add("File " + listOfFiles[i].getName());
                }
            }
        }

        return  fileNames;
    }
}
