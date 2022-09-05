package assign6;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentC extends LinkedList<Student> {

    public LinkedList<Student> filterByNationality(Nationality n) {
        Predicate<Student> filterByNationality = e -> e.getNationality().equals(n);

        return (LinkedList<Student>) super
                .stream()
                .filter(filterByNationality)
                .collect(Collectors.toList());
    }
}