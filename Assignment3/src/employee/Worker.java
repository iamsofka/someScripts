package employee;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    private LocalDate empDate;
    private BigDecimal bonus;

    public Worker(String firstName, String surname, LocalDate birthdate, BigDecimal salary, Manager manager, LocalDate empDate, BigDecimal bonus) {

        super(firstName, surname, birthdate, salary, manager);

        this.empDate = empDate;
        this.bonus = bonus;
    }

    public LocalDate getEmpDate() {
        return empDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }
}
