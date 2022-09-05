package assign6;

import java.util.Comparator;
import java.util.LinkedList;

public class SubjectC extends LinkedList<Subject> {
    @Override
    public boolean add(Subject subject) {
        if (!super.contains(subject)) {
            super.add(subject);
            super.sort(Comparator.naturalOrder());
            return true;
        }
        return false;
    }
}
