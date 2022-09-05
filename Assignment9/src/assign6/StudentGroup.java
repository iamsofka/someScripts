package assign6;

import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup> {
    private final String name;
    private List<Student> studentList;

    public StudentGroup(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroup)) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentList);
    }

    @Override
    public int compareTo(StudentGroup o) {
        int result;
        result = this.name.compareTo(o.getName());
        if (result == 0)
            return Integer.compare(studentList.size(), o.getStudentList().size());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}