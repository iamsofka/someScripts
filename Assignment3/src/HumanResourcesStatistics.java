import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HumanResourcesStatistics {
    public static List<PayrollEntry> payroll(List<Employee> employees) {
        List<PayrollEntry> list = employees.stream().map(employee -> {
            if(employee.getClass() == Trainee.class)
                return new PayrollEntry(employee, employee.getSalary(), BigDecimal.valueOf(0));
            else
                return new PayrollEntry(employee, employee.getSalary(), ((Worker)employee).getBonus());
        }).collect(Collectors.toList());
        return list;
    }

    // payroll for all subordinates
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        List<PayrollEntry> list = manager.getAllSubordinates().stream().map(employee -> {
            if(employee.getClass() == Trainee.class)
                return new PayrollEntry(employee, employee.getSalary(), BigDecimal.valueOf(0));
            else
                return new PayrollEntry(employee, employee.getSalary(), ((Worker)employee).getBonus());
        }).collect(Collectors.toList());
        return list;
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
        BigDecimal total = employees.stream().map(employee -> {
            if(employee.getClass() == Trainee.class)
                return BigDecimal.valueOf(0);
            else
                return ((Worker)employee).getBonus();
        }).collect(Collectors.toList()).stream().reduce(new BigDecimal(0), BigDecimal::add);
        return total;
    }

    public static Employee longestSeniority(List<Employee> employees){
        return employees.stream().filter(employee -> {
            if(employee.getClass() == Trainee.class)
                return false;
            else
                return true;
        }).sorted(Comparator.comparing(employee -> ((Worker)employee).getEmpDate())).collect(Collectors.toList()).get(0);
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
    //        return employees.stream().map(Employee::getSalary).sorted().collect(Collectors.toList()).get(employees.size() - 1);

    public static BigDecimal highestSalaryWithBonus(List<Employee> employees) {
        return employees.stream()
                .map(employee -> employee.getClass() == Trainee.class ? employee.getSalary() : employee.getSalary().add(((Worker) employee).getBonus()))
                .sorted().collect(Collectors.toList()).get(employees.size() - 1);
    }

    public static List<Employee> surnameWithA(List<Employee> employees){
        return employees.stream().filter(employee -> employee.getSurname().startsWith("A")).collect(Collectors.toList());
    }

    public static List<Employee> moreThanAThousand(List<Employee> employees){
        return employees.stream().filter(employee -> employee.getSalary().intValue() >= 1000).collect(Collectors.toList());
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
