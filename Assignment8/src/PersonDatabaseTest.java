import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class PersonDatabaseTest {

    private final File txtFile = new File("C:\\Users\\Cофия\\IdeaProjects\\Assign8\\src\\inputFile");
    private final File binaryFile = new File ("C:\\Users\\Cофия\\IdeaProjects\\Assign8\\src\\output");

    @Test
    public void serializeAndDeserialize() throws Throwable{
        InputParser parser = new InputParser();
        List<Person> persons = parser.parse(txtFile);
        PersonDatabase database = new PersonDatabase(txtFile);

        OutputStream output = new FileOutputStream(binaryFile);
        DataOutputStream dataOutput = new DataOutputStream(output);
        database.serialize(dataOutput);
        dataOutput.close();

        InputStream input = new FileInputStream(binaryFile);
        DataInputStream dataInput = new DataInputStream(input);
        PersonDatabase deserialized = PersonDatabase.deserialize(dataInput);
        dataInput.close();
        Assert.assertNotNull(deserialized);
        Assert.assertEquals(database.size(), deserialized.size());
    }
}