package assign6;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private final int bookNum;

    public Student(String pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality, int bookNum) {
        super(pesel, firstName, lastName, sex, birthDate, nationality);
        this.bookNum = bookNum;
    }

    public long getBookNum() {
        return bookNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return bookNum == student.bookNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bookNum);
    }
}