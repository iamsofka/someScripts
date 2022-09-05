package Comparators;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private final List<Person> _bySurnameFirstNameAndDate;
    private final List<Person> _byFirstName;
    private final List<Person> _byBirthdate;
    private Map<Date, List<Person>> _birthdateSearch;

    private PersonDatabase(List<Person> database){
        _bySurnameFirstNameAndDate = database;
        _byFirstName = new ArrayList<>(database);
        _byBirthdate = new ArrayList<>(database);

        _bySurnameFirstNameAndDate.sort(Comparator.naturalOrder());
        _byFirstName.sort(new FirstNameComparator());
        _byBirthdate.sort(new BirthdateComparator());

        _birthdateSearch = database.stream().
                collect(Collectors.groupingBy(Person::getBirthdate, TreeMap::new, Collectors.mapping(p -> p, Collectors.toList())));
    }

    public PersonDatabase(File file){
        this(InputParser.parse(file));
    }

    public List<Person> sortedByFirstName() {
        return _byFirstName; // external rule for ordering (based on Comparator --- FirstNameComparator)
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        return _bySurnameFirstNameAndDate; // natural order (Comparable)
    }

    public List<Person> sortedByBirthdate() {
        return _byBirthdate; // external rule for ordering (based on Comparator --- BirthdateComparator)
    }

    public List<Person> bornOnDay(Date date) {
        return _birthdateSearch.get(date);
    }
}