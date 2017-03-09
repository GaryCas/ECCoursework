package services;

import entities.ApplicationVariables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by rd019985 on 09/03/2017.
 */
public class CompilationServiceTest {


    String[] testFileNames = {ApplicationVariables.UTPATH+"\\"+"Test.txt",
            ApplicationVariables.UTPATH+"\\"+"Test1.java",
            ApplicationVariables.UTPATH+"\\"+"Test2.java",
            ApplicationVariables.UTPATH+"\\"+"test.java"
    };
    String[] justDeleteFileNames = {ApplicationVariables.UTPATH+"\\"+"test.class"
    };

    @Before
    public void setUp() throws IOException {
        Utils.createFiles(testFileNames);
        Utils.deleteFiles(justDeleteFileNames);
    }

    @After
    public void tearDown(){
        Utils.deleteFiles(testFileNames);

    }

    @Test
    public void shouldListOnlyJavaFiles(){
        // given

        // when
        ArrayList fileNames = CompilationService.getJavaFiles(ApplicationVariables.UTPATH);

        // then
        assertEquals(3, fileNames.size());
    }

    @Test
    public void shouldCompileJavaFiles() throws IOException {
        // given
        assertFalse( new File(ApplicationVariables.UTPATH +"\\"+"test.class").exists());

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(ApplicationVariables.UTPATH + "\\"+"test.java"), "utf-8"))) {
            writer.write("public class test {\n" +
                    "    public static void main (String args[]){\n" +
                    "        System.out.println(\"thing\");\n" +
                    "    }\n" +
                    "} ");
        }

        //when
        CompilationService.Compile(ApplicationVariables.UTPATH, "test.java");


        //then
        assertTrue( new File(ApplicationVariables.UTPATH +"\\"+"test.class").exists());
    }
}
