package classes;

import org.junit.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    private Subject sub1;
    private Subject sub2;
    private List<Subject> subs;
    private Cont<Subject, String> subCont;

    @Before
    public void inputData(){
        sub1 = new Subject("maths");
        sub2 = new Subject("chemistry");
        subs = new ArrayList<>();
        subs.add(sub1);
        subs.add(sub2);
        subCont = new Cont<>();
        subCont.elements().add(sub1);
        subCont.elements().add(sub2);
    }

    @Test
    public void STest_1(){
        Assert.assertEquals("maths", subCont.elements().get(0).name());
        Assert.assertEquals("chemistry", subCont.elements().get(1).name());
    }

    @Test
    public void STst_2(){
        Assert.assertNotSame(subCont.elements().get(0), subCont.cloneElementAtIndex(0));
        Assert.assertNotSame(subCont.elements().get(1), subCont.cloneElementAtIndex(1));
    }

    @Test
    public void STest_3(){
        String str1 = subCont.aggregateAllElements();
        String str2 = subs.get(1).aggregate(subs.get(0).aggregate(null));
        Assert.assertEquals(str1, str2);
    }
}
