package classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    private Mark mark1;
    private Mark mark2;
    private List<Mark> marks;
    private Cont<Mark, Integer> marksCont;

    @Before
    public void inputData(){
        mark1 = new Mark(10);
        mark2 = new Mark(99);
        marks = new ArrayList<>();
        marks.add(mark1);
        marks.add(mark2);
        marksCont = new Cont<>();
        marksCont.elements().add(mark1);
        marksCont.elements().add(mark2);
    }

    @Test
    public void test_1(){ // testing elements() method
        Assert.assertEquals(10, marksCont.elements().get(0).points());
        Assert.assertEquals(99, marksCont.elements().get(1).points());
    }

    @Test
    public void test_2(){ //testing clonning (checking if objects are not the same (by hashcode of elements))
        Assert.assertNotSame(marksCont.elements().get(0), marksCont.cloneElementAtIndex(0));
        Assert.assertNotSame(marksCont.elements().get(1), marksCont.cloneElementAtIndex(1));
        //Assert.assertSame(marksCont.elements().get(0), marksCont.cloneElementAtIndex(0));
    }

    @Test
    public void test_3(){ //aggregating all elements
        Integer int1 = marksCont.aggregateAllElements();
        Integer int2 = marks.get(0).aggregate(marks.get(1).aggregate(null));
        Assert.assertEquals(int1, int2);
    }
}
