import org.junit.AfterClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private static final PrintStream OUT = System.out;
    private static final PrintStream ERR = System.err;
    private static final InputStream IN = System.in;


    @AfterClass
    public static void recoverOriginalOutput() {
        System.err.flush();
        System.out.flush();
        System.setOut(MainTest.OUT);
        System.setErr(MainTest.ERR);
        System.setIn(MainTest.IN);
    }

    private static String executeMain(String input) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));
        System.setIn(inputStream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(baos);
        System.setOut(stream);
        System.setOut(stream);
        Main.main(new String[0]);
        return baos.toString();
    }


    @Test
    public void codeforcesTest() throws Exception {
        List<String> testCases = TestUtils.getCFSampleTestCases(1091,"A");
        for(int i=0;i<testCases.size();i+=2){
            assertEquals(String.valueOf(testCases.get(1)).trim(), String.valueOf(executeMain(testCases.get(0))).trim());
        }
    }
}
