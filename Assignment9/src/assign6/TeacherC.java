package assign6;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TeacherC extends LinkedList<Teacher> {

    public LinkedList<Teacher> filterByNationality(Nationality n) {
        Predicate<Teacher> filterByNationality = e -> e.getNationality().equals(n);

        return (LinkedList<Teacher>) super
                .stream()
                .filter(filterByNationality)
                .collect(Collectors.toList());
    }
}