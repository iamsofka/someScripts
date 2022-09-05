import assign6.DataGenerator;
import assign6.Student;
import assign6.StudentC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class PeselTest {
    private StudentC students;

    @Before
    public void init() {
        students = DataGenerator.generateStudents();
    }
    @Test
    public void isValid() throws Exception {
        Class<? extends Pesel> c = Pesel.class;
        Method m = c.getDeclaredMethod("isValid", String.class);
        m.setAccessible(true);

        for (Student s : students)
            Assert.assertTrue((Boolean) m.invoke(null, s.getPesel()));
    }
    @Test
    public void getDate() throws Exception {
        Class<? extends Pesel> c = Pesel.class;
        Method m = c.getDeclaredMethod("getDate", String.class);
        m.setAccessible(true);

        for (Student s : students)
            Assert.assertEquals(s.getBirthDate(), m.invoke(null, s.getPesel()));
    }
    @Test
    public void getSex() throws Exception {
        Class<? extends Pesel> c = Pesel.class;
        Method m = c.getDeclaredMethod("getSex", String.class);
        m.setAccessible(true);

        for (Student s : students)
            Assert.assertEquals(s.getSex().toString(), m.invoke(null, s.getPesel()));
    }
}