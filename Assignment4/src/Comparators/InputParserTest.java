package Comparators;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public final class InputParserTest {
    @Test
    public void InputParse(){
        File file = new File("C:/Users/Cофия/IdeaProjects/assignmnet4/src/Comparators/database.txt");
        Assert.assertTrue(file.exists());
        List<Person> peopleList = InputParser.parse(file);
        Assert.assertNotNull(peopleList);
        Assert.assertEquals(peopleList.size(),4);
        System.out.println(peopleList);
    }
}