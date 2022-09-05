import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;

public final class UtilityTest{
    private final File data = new File("C:\\Users\\Cофия\\IdeaProjects\\Assigm7\\src");
    private final File testFile = new File ("C:\\Users\\Cофия\\IdeaProjects\\Assigm7\\src\\testFile");

    private final File dataZip = new File("C:\\Users\\Cофия\\IdeaProjects\\Assigm7\\src");
    private final File testFileZip = new File("C:\\Users\\Cофия\\IdeaProjects\\Assigm7\\src\\testFileZip.zip");

    @Before
    public void before(){
        Assert.assertTrue(data.exists());
        Assert.assertTrue(testFile.exists());

        Assert.assertTrue(dataZip.exists());
        Assert.assertTrue(testFileZip.exists());
    }

    @Test
    public void searchByName(){
        List<File> files = FileUtility.searchByName(data, "testFile");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("testFile", file.getName());
        Assert.assertEquals(testFile, file);
    }

    @Test
    public void SearchByContent(){
        List<File> files = FileUtility.searchByContent(data, "The penguin Filipp is really sleepy");
        Assert.assertEquals(2, files.size());
        File file = files.get(0);
        Assert.assertEquals("testFile", file.getName());
        Assert.assertEquals(testFile, file);
    }

    @Test
    public void SearchByName(){
        List<File> files = FileUtility.searchByName(dataZip, "testFileZip");
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("testFileZip.zip", file.getName());
        Assert.assertEquals(testFileZip, file);
    }
}