import employee.Employee;

import java.math.BigDecimal;

public final class PayrollEntry {
    private final Employee _employee;
    private final BigDecimal _salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        _employee = employee;
        _salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
    }

    @Override
    public String toString() {
        return _employee.getSurname() + " " + _salaryPlusBonus;
    }
}