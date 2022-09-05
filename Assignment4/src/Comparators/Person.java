package Comparators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Comparable<Person> {

    private final String _firstName;
    private final String _surname;
    private final Date _birthdate;
    private static final DateFormat FORMAT = new
            SimpleDateFormat("yyyy-MM-dd");

    public Person(String firstName, String surname, Date birthdate) {
        _firstName = firstName;
        _surname = surname;
        _birthdate = birthdate;
    }

    @Override
    public int compareTo(Person otherPerson) {
        int result = getSurname().compareTo(otherPerson.getSurname());
        if (result != 0) {
            return result;
        }
        result = getFirstName().compareTo(otherPerson.getFirstName());
        if (result != 0) {
            return result;
        }
        return getBirthdate().compareTo(otherPerson.getBirthdate());
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getSurname() {
        return _surname;
    }

    public Date getBirthdate() {
        return _birthdate;
    }

    @Override
    public String toString() {
        return _firstName + " " + _surname + " (" + FORMAT.format(_birthdate) + ")";
    }
}