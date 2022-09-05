package assign6;

import java.util.Objects;

public class Subject implements Comparable<Subject> {
    private final String name;
    private final Department supervisor;
    private final Teacher lecturer;

    public Subject(String name, Department supervisor, Teacher lecturer) {
        this.name = name;
        this.supervisor = supervisor;
        this.lecturer = lecturer;
    }

    public String getName() {
        return name;
    }

    public Department getSupervisor() {
        return supervisor;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(supervisor, subject.supervisor) &&
                Objects.equals(lecturer, subject.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supervisor, lecturer);
    }

    @Override
    public int compareTo(Subject o) {
        int result;
        result = name.compareTo(o.getName());
        if (result == 0) {
            result = supervisor.compareTo(o.getSupervisor());
            if (result == 0) {
                return lecturer.compareTo(o.getLecturer());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return name + ", department: " + supervisor + ", lecturer: " + lecturer.getFirstName() + " " + lecturer.getLastName();
    }
}
