package Classes;

import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HumanResourcesStatistics {
    private static final Predicate<Employee> isTrainee = HumanResourcesStatistics::ifTrainee;
    private static final Predicate<Employee> isWorker = employee -> !ifTrainee(employee);

    private static final Function<Employee, Worker> toWorker = employee -> (Worker) employee;
    private static final Function<Employee, Trainee> toTrainee = employee -> (Trainee) employee;

    private static Stream<Worker> streamForWorker(List<Employee> empList){
        return empList
                .stream()
                .filter(isWorker)
                .map(toWorker);
    }

    private static Stream<Trainee> streamForTrainee(List<Employee> empList){
        return  empList
                .stream()
                .filter(isTrainee)
                .map(toTrainee);
    }

    public static boolean ifTrainee(Employee employee){
        return employee instanceof Trainee;
    }

    public static BigDecimal traineeBonus(Employee employee){
        return employee instanceof Trainee ? BigDecimal.ZERO : ((Worker)employee).getBonus();
    }

    private static PayrollEntry payrollEntry(Employee employee) {
        if(employee instanceof Worker) {
            return new PayrollEntry(employee, employee.getSalary(), ((Worker) employee).getBonus());
        }
        return new PayrollEntry(employee, employee.getSalary(), BigDecimal.ZERO);
    }

    public static List<PayrollEntry> payroll(List<Employee> employees) {
        List<PayrollEntry> list = employees
                .stream()
                .map(HumanResourcesStatistics::payrollEntry)
                .collect(Collectors.toList());
        return list;
    }

    // payroll for all subordinates
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        return payroll(manager.getAllSubordinates());
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
//        BigDecimal total = employees.stream().map(employee -> {
//            if(employee.getClass() == Trainee.class)
//                return BigDecimal.valueOf(0);
//            else
//                return ((Worker)employee).getBonus();
//        }).collect(Collectors.toList()).stream().reduce(new BigDecimal(0), BigDecimal::add);
//        return total;
        return employees.stream()
                .map(HumanResourcesStatistics::traineeBonus)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Employee longestSeniority(List<Employee> employees){
//        return employees.stream().filter(employee -> {
//            if(employee.getClass() == Trainee.class)
//                return false;
//            else
//                return true;
//        }).sorted(Comparator.comparing(employee -> ((Worker)employee).getEmpDate())).collect(Collectors.toList()).get(0);
        return employees.stream()
                .filter(isWorker)
                .map(employee -> (Worker)employee).sorted(Comparator.comparing(Worker::getEmpDate))
                .collect(Collectors.toList()).get(0);
    }

    private static BigDecimal highestSalary = new BigDecimal(0);
    public static BigDecimal highestSalary(List<Employee> employees){
        highestSalary = employees.get(0).getSalary();
        employees.stream().forEach(employee -> {
            if(highestSalary.intValue() < employee.getSalary().intValue()){
                highestSalary = employee.getSalary();
            }
        });
        return highestSalary;
    }

    public static BigDecimal highestSalaryWithBonus(List<Employee> employees) {
        return employees.stream()
                .map(employee -> employee.getClass() == Trainee.class ? employee.getSalary() : employee.getSalary()
                        .add(((Worker) employee).getBonus()))
                .sorted().collect(Collectors.toList()).get(employees.size() - 1);
//        return employees.stream().filter(employee -> {
//            if(employee.getClass() == Trainee.class)
//                return false;
//            else
//                return true;
//        }).sorted(Comparator.comparing(employee -> employee.getSalary().add(((Worker)employee).getBonus()))).collect(Collectors.toList()).get(employees.size() - 1 );
    }

    public static List<Employee> surnameWithA(List<Employee> employees){
        return employees.stream().filter(employee -> employee.getSurname().startsWith("A")).collect(Collectors.toList());
    }

    public static List<Employee> moreThanAThousand(List<Employee> employees){
        return employees.stream().filter(employee -> employee.getSalary().intValue() >= 1000).collect(Collectors.toList());
    }

    //assignment3

    public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
        Predicate<Employee> olderThan = emp -> emp.isOlder(employee.getAge());
        Predicate<Employee> earnsMore = emp -> emp.salaryIsGreater(employee.getSalary());
        Predicate<Employee> olderThanAndEarnsMore = olderThan.and(earnsMore);

        return allEmployees
                .stream()
                .filter(olderThanAndEarnsMore)
                .collect(Collectors.toList());
    }

    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
            Predicate<Trainee> practiceLengthLongerThan = employee -> employee.longerPractice(daysCount);
        return streamForTrainee(allEmployees)
                .filter(practiceLengthLongerThan)
                .collect(Collectors.toList());
    }

    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        Predicate<Worker> seniorityLongerThan = employee -> employee.monthlySeniority(monthCount);

        return streamForWorker(allEmployees)
                .filter(seniorityLongerThan)
                .collect(Collectors.toList());
    }

    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        Predicate<Worker> moreThanOneYear = employee -> employee.yearlySeniority(1);
        Predicate<Worker> lessThanThree = employee -> !employee.yearlySeniority(3);
        Predicate<Worker> seniorityBetweenOneAndThreeYears = moreThanOneYear.and(lessThanThree);

        //Predicate<Worker> seniorityBetweenOneAndThreeYears =  employee -> employee.yearlySeniority(1) && !employee.yearlySeniority(3);

        return streamForWorker(allEmployees)
                .filter(seniorityBetweenOneAndThreeYears)
                .collect(Collectors.toList());
    }

    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
        Predicate<Worker> seniorityLongerThan = emp -> emp.monthlySeniority(((Worker)employee).seniorityMonth());

        return streamForWorker(allEmployees)
                .filter(seniorityLongerThan)
                .peek(emp -> emp.setSalary(employee.getSalary()))
                .collect(Collectors.toList());
    }

    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        Predicate<Worker> moreThanTwo = employee -> employee.yearlySeniority(1);
        Predicate<Worker> lessThanFour = employee -> !employee.yearlySeniority(4);
        Predicate<Worker> ageGreaterThan = employee -> employee.isOlder(age);
        Predicate<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan = moreThanTwo.and(lessThanFour).and(ageGreaterThan);

        //Predicate<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan = employee -> employee.yearlySeniority(1) && !employee.yearlySeniority(4) && employee.isOlder(age);

        return streamForWorker(allEmployees)
                .filter(seniorityBetweenTwoAndFourYearsAndAgeGreaterThan)
                .collect(Collectors.toList());
    }


    /**
     * samples for functional processing in Java
     *
     */
    public static List<Short> getAges(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        List<Short> ages = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .collect(Collectors.toList());
        return ages;
    }

    public static void printAges(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .forEach(age -> System.out.print(age + ", "));
    }

    //
    // average age for the Employees whose first name starts with 'A' and they are older than 20
    public static short getAverageAgeInline(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, //
                        (total, age) -> total + age);

        long filteredEmployeesCount = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .count();

        return (short) (employeeTotalAge / filteredEmployeesCount);
    }

    public static short getAverageAgeMethodReference(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, HumanResourcesStatistics::totalAge);
        return (short) (employeeTotalAge / employees.size());
    }

    public static short getMaxAgeInline(List<Employee> employees) {
        short employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce((short) 0, //
                        (maxAge, age) -> {
                            if (maxAge < age) {
                                return age;
                            } else {
                                return maxAge;
                            }
                        });
        return employeeMaxAge;
    }

    public static short getMaxAgeMethodReference(List<Employee> employees) {
        short employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce((short) 0, HumanResourcesStatistics::maxAge);
        return employeeMaxAge;
    }

    private static int totalAge(int totalAge, int age) {
        return totalAge + age;
    }

    private static short maxAge(short maxAge, short age) {
        if (maxAge < age) {
            return age;
        } else {
            return maxAge;
        }
    }
}
