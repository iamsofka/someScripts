package employee;
import java.math.BigDecimal;
import java.time.LocalDate;


public abstract class Employee extends Person{
    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    private BigDecimal salary;
    private Manager manager;

    protected Employee(String firstName, String surname, LocalDate birthdate, BigDecimal salary, Manager manager) {
        super(firstName, surname, birthdate);
        this.salary = salary;
        this.manager = manager;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Manager getManager(){
        return manager;
    }
}