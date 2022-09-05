package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Manager extends Worker{
    private List<Employee> _allSubordinates;
    private List<Employee> _subordinates;

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)

    public Manager(String firstName, String surname, LocalDate birthdate, BigDecimal salary, Manager manager, LocalDate empDate, BigDecimal bonus, LinkedList<Employee> subordinates) {

        super(firstName, surname, birthdate, salary, manager, empDate, bonus);

        _subordinates = subordinates;
        _allSubordinates = new ArrayList<>();
    }

    public void setSubordinates(List<Employee> _subordinates){
        this._subordinates = _subordinates;
    }

    public List<Employee> getAllSubordinates() {
        return Stream
                .concat(_subordinates.stream(), subordinatesOfSubordinates())
                .collect(Collectors.toList());
    }

    private Stream<Employee> subordinatesOfSubordinates() {
        return _subordinates
                .stream()
                .filter(employee -> employee instanceof Manager)
                .map(employee -> (Manager)employee)
                .flatMap(manager -> manager.getAllSubordinates().stream());
    }
}
