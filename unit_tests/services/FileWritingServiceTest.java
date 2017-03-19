package services;

import entities.ApplicationVariables;
import entities.BotEntity;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.io.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by rd019985 on 08/03/2017.
 */
public class FileWritingServiceTest {

    String[] testFitnessFilenames = {ApplicationVariables.UTPATH
            +"\\"+"avgFitnessTest.txt", ApplicationVariables.UTPATH
            +"\\"+"bestFitnessTest.txt"};
    String[] testFile = {ApplicationVariables.UTPATH
            +"\\"+"testfile.txt"};

    @Before
    public void setUp(){
        Utils.deleteFiles(testFitnessFilenames);
        Utils.deleteFiles(testFile);
    }

    @Test
    public void FilesShouldBeWritten(){
        //given
        double[] info = {2.0, 5.0};
        String[] filenames = {"avgFitnessTest.txt", "bestFitnessTest.txt"};

        //when
        FileWritingService.outputRunData(0,info,"avgFitnessTest.txt", ApplicationVariables.UTPATH);

        //then
        boolean checkAvg = new File(ApplicationVariables.UTPATH
                +"\\"+filenames[0]).exists();
        boolean checkBest = new File(ApplicationVariables.UTPATH
                +"\\"+filenames[1]).exists();

        assertTrue(checkAvg);
        assertTrue(checkBest);
    }

    @Test
    public void shouldCreateFileAndCheckItExists() throws IOException {
        //given

        //when
        File file =new File(ApplicationVariables.UTPATH
                +"\\"+"testfileCreated.txt");
        file.createNewFile();

        //then
        boolean checkTest = new File(ApplicationVariables.UTPATH
                +"\\"+"testfileCreated.txt").exists();

        assertTrue(checkTest);
    }

    @Test
    public void shouldCompileJavaFile() throws IOException {
        //given

    }
}
