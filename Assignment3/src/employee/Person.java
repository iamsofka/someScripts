package employee;
import java.time.LocalDate;

public abstract class Person {
    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // the type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    private final String _firstName;// backing field
    private final String _surname;

    private final LocalDate _birthDate;

    protected Person(String firstName, String surname, LocalDate birthDate) {
        _firstName = firstName;
        _surname = surname;
        _birthDate = birthDate;
    }

    public String getFirstName() { // getter
        return _firstName;
    }

    public String getSurname(){
        return _surname;
    }

    public short getAge() {
        return (short)(LocalDate.now().getYear() - _birthDate.getYear());
    }
}

