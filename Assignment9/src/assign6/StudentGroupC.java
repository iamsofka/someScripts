package assign6;

import java.util.Comparator;
import java.util.LinkedList;

public class StudentGroupC extends LinkedList<StudentGroup> {
    @Override
    public boolean add(StudentGroup studentGroup) {
        if (!super.contains(studentGroup)) {
            super.add(studentGroup);
            super.sort(Comparator.naturalOrder());
            return true;
        }
        return false;
    }
}