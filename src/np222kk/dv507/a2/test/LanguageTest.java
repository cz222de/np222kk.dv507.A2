package np222kk.dv507.a2.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Run tests cases for the semantical analysis
 */
public class LanguageTest {

    public static void main(String[] args) {
        
        /*
         * The srgs array should contain all directories with test files and
         * if they are exprected to fail or not:
         * 
         * The array should look something like this. At first an absolute path
         * to the test files should be specified. Afterwards it should be specified
         * if the test cases are supposed to fail:
         *      true --> supposed to fail
         *      false --> cases should worke fine
         * {"Absolute Path A", "true", "Absolute Path B", "false"}
         * 
         */

        Map<File, Boolean> testDirectories = new HashMap<File, Boolean>();

        if (args.length % 2 != 0) {
            System.out.println("Insufficient number of args given");
        } else {
            for (int i = 0; i < args.length; i+=2) {
                testDirectories.put(new File(args[i]), Boolean.parseBoolean(args[i+1]));
            }
        }

        List<OFPTest> tests = new ArrayList<OFPTest>();

        int cntSuccess = 0;
        int cntFail = 0;

        // Loop over all folders
        for (File folder : testDirectories.keySet()) {
            boolean failureExpected = testDirectories.get(folder);

            // Loop over each test file
            for (File file : folder.listFiles()) {
            String filePath = file.getAbsolutePath();
            System.out.print("Test: " + filePath);
            OFPTest test = new OFPTest(filePath, failureExpected);
                if (test.runTest()) {
                    System.out.println("SUCCESS");
                    cntSuccess++;
                } else {
                    System.out.println("FAIL");
                    cntFail++;
                }
            
            tests.add(test);
        }
    }

        System.out.println("--------------------------------------------");
        System.out.println("Testresults:");
        
        tests.forEach(v -> {
            System.out.println("(" + (v.result?"\u001B[32mSUCCESS\u001B[0m":"\u001B[31mFAIL\u001B[0m\t") + ") Test: " + v.fileName);
        });

        System.out.println("Test results: " + (cntSuccess + cntFail) + " test cases checked (" + cntSuccess + " successed, " + cntFail + " failed)");
    
        System.out.println("--------------------------------------------");

        
    }



}
