package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    private LocalDate empDate;
    private BigDecimal bonus;
    private Boolean hasBonus;

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

    //assignment3
    public Boolean hasBonus(){
        return hasBonus;
    }

    public int seniorityYear(){
        return LocalDate.now().getYear() - empDate.getYear();
    }

    public int seniorityMonth(){
        return empDate.getMonthValue() + (seniorityYear() * 12);
    }

    public boolean yearlySeniority(int years){
        return seniorityYear() > years;
    }

    public boolean monthlySeniority(int months){
        return seniorityMonth() > months;
    }

    public boolean greaterBonus(BigDecimal bonus){
        return bonus.compareTo(bonus) > 0;
    }
}
