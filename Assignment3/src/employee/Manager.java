package employee;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
//
//    public List<Employee> getAllSubordinates(){
//        return _allSubordinates.stream().filter(employee -> employee.getManager() == this).collect(Collectors.toList());
//    }

    public List<Employee> getAllSubordinates() {
        LinkedList<Employee> list = new LinkedList<>();
        for (Employee e : _subordinates) {
            if (e.getClass() == Manager.class)
                ((Manager) e).getAllSubordinates();
            list.add(e);
        }
        return list;
    }

}