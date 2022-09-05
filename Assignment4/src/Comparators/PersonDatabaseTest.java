package Comparators;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonDatabaseTest {

    @Test
    public void sortedByFirstName(){
        File file = new File("//C:/Users/Cофия/IdeaProjects/assignmnet4/src/Comparators/database.txt");
        Assert.assertTrue(file.exists());
        PersonDatabase db = new PersonDatabase(file);
        Assert.assertNotNull(db);
        Assert.assertEquals(db.sortedByFirstName().size(),4);
        Assert.assertEquals(db.sortedByBirthdate().size(),4);
        Assert.assertEquals(db.sortedBySurnameFirstNameAndBirthdate().size(),4);
        try{
            Assert.assertEquals(db.bornOnDay(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-11")).size(), 1);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}