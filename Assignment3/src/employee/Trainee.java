package employee;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee{
    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)
    private LocalDate apprenticeshipSt;
    private int apprenticeshipL;

    public Trainee(String firstName, String surname, LocalDate birthdate, BigDecimal salary, Manager manager, LocalDate apprenticeshipSt, int apprenticeshipL) {

        super(firstName, surname, birthdate, salary, manager);
        this.apprenticeshipSt = apprenticeshipSt;
        this.apprenticeshipL = apprenticeshipL;
    }
}