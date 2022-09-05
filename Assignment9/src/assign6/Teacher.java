package assign6;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher extends Person {
    private final AcademicDegree academicDegree;
    private final LocalDate hireDate;

    public Teacher(String pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality, AcademicDegree academicDegree, LocalDate hireDate) {
        super(pesel, firstName, lastName, sex, birthDate, nationality);
        this.academicDegree = academicDegree;
        this.hireDate = hireDate;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return academicDegree == teacher.academicDegree &&
                Objects.equals(hireDate, teacher.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academicDegree, hireDate);
    }
}
