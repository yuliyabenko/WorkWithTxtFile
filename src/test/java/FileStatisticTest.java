import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileStatisticTest extends TestCase {
    private FileStatistic fileStat = new FileStatistic();
    private String fileName = null;

    @Test
    public void testLineLength() throws Exception {
        assertEquals(13, fileStat.lineLength("One two three"));
        assertEquals(18, fileStat.lineLength("Apple peach orange"));
        assertEquals(15, fileStat.lineLength("Red blue yellow"));

    }

    @Test
    public void testAverageWordLength() throws Exception {
        assertEquals(3, fileStat.averageWordLength("One two three"));
        assertEquals(5, fileStat.averageWordLength("Apple peach orange"));
        assertEquals(4, fileStat.averageWordLength("Red blue yellow"));
    }

    @Test
    public void testShortestWord() throws Exception {
        assertEquals("One",fileStat.shortestWord("One two three"));
        assertEquals("Apple",fileStat.shortestWord("Apple peach orange"));
        assertEquals("Red",fileStat.shortestWord("Red blue yellow"));
    }

    @Test
    public void testLongestWord() throws Exception {
        assertEquals("three",fileStat.longestWord("One two three"));
        assertEquals("orange",fileStat.longestWord("Apple peach orange"));
        assertEquals("yellow",fileStat.longestWord("Red blue yellow"));
    }

    private List<String> prepareList(){
        List<String> list = new ArrayList<> ();
        list.add("One two three ");
        list.add("Apple peach orange ");
        list.add("Red blue yellow ");
        return list;
    }

    @Test
    public void testLineStatistic() throws Exception {
        LineFileStat lfs1 = new LineFileStat();
        lfs1.setName(fileName + ", line 1");
        lfs1.setLength(14);
        lfs1.setAverageWordLength(3);
        lfs1.setShortestWord("One");
        lfs1.setLongestWord("three");
        LineFileStat lfs2 = new LineFileStat();
        lfs2.setName(fileName + ", line 2");
        lfs2.setLength(19);
        lfs2.setAverageWordLength(5);
        lfs2.setShortestWord("Apple");
        lfs2.setLongestWord("orange");
        LineFileStat lfs3 = new LineFileStat();
        lfs3.setName(fileName + ", line 3");
        lfs3.setLength(16);
        lfs3.setAverageWordLength(4);
        lfs3.setShortestWord("Red");
        lfs3.setLongestWord("yellow");

        assertEquals(lfs1.toString(), (fileStat.lineStatistic(prepareList(), 0).toString()));
        assertEquals(lfs2.toString(), (fileStat.lineStatistic(prepareList(), 1).toString()));
        assertEquals(lfs3.toString(), (fileStat.lineStatistic(prepareList(), 2).toString()));
    }

    @Test
    public void testFileStatistic() throws Exception {
        LineFileStat lfs = new LineFileStat();
        lfs.setName(fileName);
        lfs.setLength(49);
        lfs.setAverageWordLength(4);
        lfs.setShortestWord("One");
        lfs.setLongestWord("orange");
        assertEquals(lfs.toString(), (fileStat.fileStatistic(prepareList()).toString()));
    }
}