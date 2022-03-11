import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    
    @Test
    public void testFile2() throws IOException {
        String contents= Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testMissingCloseParen() {
        String contents= "[link title](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSpaceAroundLink() {
        String contents= "[link title](   a.com   )";
        List<String> expect = List.of("a.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testNestedParens() throws IOException {
        String contents = Files.readString(Path.of("test-parens-inside-link.md"));
        List<String> expect = List.of("something.com()", "something.com((()))", "something.com", "boring.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testTestFile() throws IOException, NoSuchFileException{
        Path fileName = Path.of("lab-test-1.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        List<String> returnArray = new ArrayList<>();
        returnArray.add("url.com");
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
        returnArray.add("https://www.twitter.com");
        returnArray.add("https://ucsd-cse15l-w22.github.io/");
        returnArray.add("https://cse.ucsd.edu/");
        assertEquals(returnArray, links);
    }

}
