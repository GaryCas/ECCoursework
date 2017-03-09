package utils;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by rd019985 on 09/03/2017.
 */
public class Utils {

    public static void deleteFiles(String[] testFitnessFilenames) {
        for (String testFitnessFilename : testFitnessFilenames) {
            File file = new File(testFitnessFilename);
            if(file.exists()) {
                file.delete();
            }
            assertFalse(file.exists());
        }
    }

    public static void createFiles(String[] testFitnessFilenames) throws IOException {
        for (String testFitnessFilename : testFitnessFilenames) {
            File file = new File(testFitnessFilename);
            if(!file.exists()) {
                file.createNewFile();
            }
            assertTrue(file.exists());
        }
    }

}
