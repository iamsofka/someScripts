package assign6;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class DataGenerator {
    private static final File FNAME_FILE = new File("src/assignment6/Data/firstname.data"),
            LNAME_FILE = new File("src/assignment6/Data/lastname.data");

    public static final int STUDENT_COUNTER = 100,
            TEACHER_COUNTER = 12,
            DEPARTMENT_COUNTER = 4,
            DEPARTMENT_TEACHER_COUNTER = 3,
            SUBJECT_COUNTER = 10,
            STUDENT_GROUP_COUNTER = 10,
            STUDENT_GROUP_STUDENTS_COUNTER = 10;

    private static final int[] checkSumArr = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    public static StudentC generateStudents() {
        StudentC studentList = new StudentC();

        try {
            Map<Integer, String> firstNames = readFile(FNAME_FILE);
            Map<Integer, String> lastNames = readFile(LNAME_FILE);

            for (int i = 0; i < STUDENT_COUNTER; i++) {
                LocalDate localDate = LocalDate.of(
                        getRandom("year"),
                        getRandom("month"),
                        getRandom("day"));
                Sex sex = Sex.random();
                studentList.add(new Student(
                        getPesel(localDate, sex),
                        firstNames.get((int)(Math.random() * firstNames.size())),
                        lastNames.get((int)(Math.random() * lastNames.size())),
                        sex,
                        localDate,
                        Nationality.random(),
                        getRandom("bookno")
                ));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return studentList;
    }
    public static TeacherC generateTeachers() {
        TeacherC teacherList = new TeacherC();
        try {
            Map<Integer, String> firstNames = readFile(FNAME_FILE);
            Map<Integer, String> lastNames = readFile(LNAME_FILE);


            for (int i = 0; i < TEACHER_COUNTER; i++) {
                LocalDate localDate = LocalDate.of(
                        getRandom("year"),
                        getRandom("month"),
                        getRandom("day"));
                Sex sex = Sex.random();
                teacherList.add(new Teacher(
                        getPesel(localDate, sex),
                        firstNames.get((int) (Math.random() * firstNames.size())),
                        lastNames.get((int) (Math.random() * lastNames.size())),
                        sex,
                        localDate,
                        Nationality.random(),
                        AcademicDegree.random(),
                        LocalDate.of(
                                getRandom("hire-year"),
                                getRandom("month"),
                                getRandom("day")
                        )
                ));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return teacherList;
    }
    public static StudentGroupC generateStudentGroups(StudentC students) {
        int counter = 0;
        StudentGroupC groups = new StudentGroupC();
        for (int i = 0; i < STUDENT_GROUP_COUNTER; i++) {
            List<Student> tmp = new LinkedList<>();
            for (int j = 0; j < STUDENT_GROUP_STUDENTS_COUNTER; j++) {
                tmp.add(students.get(counter));
                counter++;
            }
            groups.add(new StudentGroup(
                    getRandom("group") + "-" + getRandomName(2),
                    tmp
            ));
        }
        return groups;
    }
    public static DepartmentC generateDepartments(TeacherC teachers) {
        int counter = 0;
        DepartmentC departments = new DepartmentC();
        for (int i = 0; i < DEPARTMENT_COUNTER; i++) {
            List<Teacher> tmp = new LinkedList<>();
            for (int j = 0; j < DEPARTMENT_TEACHER_COUNTER; j++) {
                tmp.add(teachers.get(counter));
                counter++;
            }
            departments.add(new Department(
                    getRandom("dep") + "-" + getRandomName(2) + "-D",
                    tmp
            ));
        }
        return departments;
    }
    public static SubjectC generateSubjects(TeacherC teachers, DepartmentC departments) {
        SubjectC subjects = new SubjectC();
        for (int i = 0; i < SUBJECT_COUNTER; i++) {
            subjects.add(new Subject(
                    getRandomName(3),
                    departments.get((int) (Math.random() * departments.size())),
                    teachers.get((int) (Math.random() * teachers.size()))
            ));
        }
        return subjects;
    }
    private static Map<Integer, String> readFile(File file) throws FileNotFoundException {
        Map<Integer, String> map = new HashMap<>();
        int counter = 1;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            map.put(counter, sc.nextLine());
            counter++;
        }
        return map;
    }
    private static int getRandom(String type) {
        return switch (type) {
            case "year" -> (int) (Math.random() * 40) + 1970;
            case "month" -> (int) (Math.random() * 12) + 1;
            case "day" -> (int) (Math.random() * 27) + 1;
            case "bookno" -> (int) (Math.random() * 10000) + 10000;
            case "hire-year" -> (int) (Math.random() * 19) + 2000;
            case "group", "dep" -> (int) (Math.random() * 20) + 1;
            default -> 0;
        };
    }
    private static String getRandomName(int bound) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < bound; i++) {
            int index = random.nextInt(alphabet.length());
            char rand = alphabet.charAt(index);
            sb.append(rand);
        }
        return sb.toString();
    }
    private static String getPesel(LocalDate localdate, Sex sex) {
        String str = getFormatedDate(localdate) + getRandomZZZ() + sex.ordinal();
        int cs = 0;
        for (int i = 0; i < 10; i++) {
            cs += Integer.parseInt(String.valueOf(str.toCharArray()[i])) + checkSumArr[i];
        }
        int controlDigit = cs % 10 == 0 ? 0 : 10 - (cs % 10);
        return str + controlDigit;
    }
    private static int getRandomZZZ() {
        return 100 + new Random().nextInt(899);
    }
    private static String getFormatedDate(LocalDate localDate) {
        int month = getMonth(localDate);
        return String.valueOf(localDate.getYear()).substring(2) +
                (month < 10 ? "0" + month : month) + "" +
                (localDate.getDayOfMonth() < 10 ? "0" + localDate.getDayOfMonth() : localDate.getDayOfMonth());
    }
    private static int getMonth(LocalDate localDate) {
        int century = Integer.parseInt(String.valueOf(localDate.getYear()).substring(0, 2));

        return switch (century) {
            case 18 -> localDate.getMonthValue() + 80;
            case 20 -> localDate.getMonthValue() + 20;
            case 21 -> localDate.getMonthValue() + 40;
            default -> localDate.getMonthValue();
        };
    }
}