package assign6;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private final String pesel;
    private final String firstName,
            lastName;
    private final Sex sex;
    private final LocalDate birthDate;
    private final Nationality nationality;

    private final Collator collator;

    public Person(String pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.collator = Collator.getInstance(Nationality.PL.getLocale());
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public int compareTo(Person o) {
        int result = collator.compare(this.firstName, o.getFirstName());
        if (result == 0) {
            result = collator.compare(this.lastName, o.getLastName());
            if (result == 0)
                return collator.compare(this.birthDate, o.getBirthDate());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return pesel.equals(person.pesel) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                sex == person.sex &&
                Objects.equals(birthDate, person.birthDate) &&
                nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, sex, birthDate, nationality);
    }

    @Override
    public String toString() {
        return pesel + " " + firstName + " " + lastName + " " + birthDate + " " + nationality;
    }
}