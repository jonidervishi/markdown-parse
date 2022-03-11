import static org.junit.Assert.*; //imports the Assert type tests
import org.junit.*; //imports the all of the junit methods and classes and so on

import static org.junit.Assert.*; //imports the Assert type tests
import org.junit.*; //imports the all of the junit methods and classes and so on

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.*;
public class MarkdownParseLabTest {
    @Test//indicates where a test is for the tester
    public void addition() {//test method header
        assertEquals(2, 1 + 1);//an actual test that chects if the two parameters are equals
    }
    @Test
    public void testTestFile() throws IOException, NoSuchFileException{
        Path fileName = Path.of("lab-test-1.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        List<String> returnArray = new ArrayList<>();
        returnArray.add("`google.com");
        returnArray.add("google.com");
        returnArray.add("ucsd.edu");

        assertEquals(returnArray, links);
    }

    @Test
    public void testTestFile2() throws IOException, NoSuchFileException{
        Path fileName = Path.of("lab-test-2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        List<String> returnArray = new ArrayList<>();
        returnArray.add("a.com");
        returnArray.add("a.com(())");
        returnArray.add("example.com");

        assertEquals(returnArray, links);
    }

    @Test
    public void testTestFile3() throws IOException, NoSuchFileException{
        Path fileName = Path.of("lab-test-3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        List<String> returnArray = new ArrayList<>();
        returnArray.add("https://ucsd-cse15l-w22.github.io/");

        assertEquals(returnArray, links);
    }
}

