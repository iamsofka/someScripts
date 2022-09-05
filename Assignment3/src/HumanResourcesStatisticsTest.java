import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class HumanResourcesStatisticsTest {

    // Create a HR structure which resembles the below one:
    //
    // Director (an instance of Manager class (Director does not have a manager)
    //   |- Manager01
    //        |- Worker
    //        |- Worker
    //        |- Trainee
    //        |- ...
    //   |- Manager02
    //        |- ...
    //   |- ...
    //   |- Worker
    //   |- Worker
    //   |- Trainee


    private Manager head;
    private Manager man1, man2, man3;
    private Worker wor1, wor2, wor3, wor4, wor5, wor6;
    private Trainee tr1, tr2, tr3, tr4;
    private LinkedList<Employee> subHead;
    private LinkedList<Employee> subMan1;
    private LinkedList<Employee> subMan2;
    private LinkedList<Employee> subMan3;
    private List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)

    @Before
    public void data(){
        subHead = new LinkedList<>();
        subHead.add(man1);
        subHead.add(man2);
        subHead.add(man3);
        head.setSubordinates(subHead);

        subMan1 = new LinkedList<>();
        subMan1.add(wor1);
        subMan1.add(tr1);
        subMan1.add(wor4);
        subMan1.add(tr4);
        man1.setSubordinates(subMan1);

        subMan2 = new LinkedList<>();
        subMan2.add(wor2);
        subMan2.add(tr2);
        subMan2.add(wor5);
        man2.setSubordinates(subMan2);

        subMan3 = new LinkedList<>();
        subMan3.add(wor3);
        subMan3.add(tr3);
        subMan3.add(wor6);
        man3.setSubordinates(subMan3);

        _allEmployees = new LinkedList<>();
        _allEmployees.add(head);
        _allEmployees.add(man1);
        _allEmployees.add(man2);
        _allEmployees.add(man3);
        _allEmployees.addAll(subMan1);
        _allEmployees.addAll(subMan2);
        _allEmployees.addAll(subMan3);

        head = new Manager("Ivan", "Ivanow", LocalDate.of(1972, 8, 18),
                new BigDecimal(25000), null, LocalDate.of(2012, 10, 10), new BigDecimal(13000), subHead);
        man1 = new Manager("Kate", "Dudko", LocalDate.of(2001, 12, 16),
                new BigDecimal(5000), head, LocalDate.of(2019, 5, 21), new BigDecimal(1000), subMan1);
        man2 = new Manager("Luc", "Briant", LocalDate.of(1998, 2, 23),
                new BigDecimal(7400), head, LocalDate.of(2018, 2, 22), new BigDecimal(5200), subMan2);
        man3 = new Manager("Sandra", "Lolek", LocalDate.of(1995,1,11),
                new BigDecimal(6100), head, LocalDate.of(2018,8,19), new BigDecimal(900), subMan3);

        wor1 = new Worker("Ted", "Mosby", LocalDate.of(1978,8,22),
                new BigDecimal(3954), man1, LocalDate.of(2019,8,21), new BigDecimal(663));
        wor2 = new Worker("Barney", "Stinson", LocalDate.of(1979,7,21),
                new BigDecimal(1133), man2, LocalDate.of(2018,8,20), new BigDecimal(554));
        wor3 = new Worker("Marshall", "Ericson", LocalDate.of(1977,6,20),
                new BigDecimal(1122), man3, LocalDate.of(2017,8,19), new BigDecimal(445));
        wor4 = new Worker("Roby", "Williams", LocalDate.of(1980,5,19),
                new BigDecimal(3772), man1, LocalDate.of(2016,8,18), new BigDecimal(336));
        wor5 = new Worker("Dan", "Brown", LocalDate.of(1982,4,18),
                new BigDecimal(2312), man2, LocalDate.of(2015,8,17), new BigDecimal(227));
        wor6 = new Worker("Lilit", "Amigos", LocalDate.of(1983,3,17),
                new BigDecimal(1559), man3, LocalDate.of(2019,8,16), new BigDecimal(188));

        tr1 = new Trainee("Mike", "Milk", LocalDate.of(1981,9,11),
                new BigDecimal(1711), man1, LocalDate.of(2019,6,22), 80);
        tr2 = new Trainee("Boran", "Przek", LocalDate.of(1983,7,11),
                new BigDecimal(1894), man2, LocalDate.of(2018,5,17), 150);
        tr3 = new Trainee("Ken", "Lucky", LocalDate.of(1986,5,13),
                new BigDecimal(1352), man3, LocalDate.of(2017,4,28), 295);
        tr4 = new Trainee("Hanna", "Hannaken", LocalDate.of(1988,3,19),
                new BigDecimal(1534), man1, LocalDate.of(2016,3,15), 360);
    }

    @Test
    public void payroll() {
        HumanResourcesStatistics.payroll(_allEmployees);
    }

    @Test
    public void subordinatesPayroll() {
        HumanResourcesStatistics.subordinatesPayroll(man1);
    }

    @Test
    public void bonusTotal() {
        BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
        Assert.assertEquals(new BigDecimal(17857), total);
    }

    @Test
    public void longestSeniority(){
        Assert.assertEquals(head, HumanResourcesStatistics.longestSeniority(_allEmployees));
    }

    @Test
    public void highestSalary(){
        Assert.assertEquals(BigDecimal.valueOf(10000), HumanResourcesStatistics.highestSalary(_allEmployees));
    }

    @Test
    public void highestSalaryWithBonus(){
        Assert.assertEquals(BigDecimal.valueOf(25000), HumanResourcesStatistics.highestSalaryWithBonus(_allEmployees));
    }

    @Test
    public void surnameWithA(){
        Assert.assertEquals(List.of(wor4), HumanResourcesStatistics.surnameWithA(_allEmployees));
    }

    @Test
    public void moreThanAThousand(){
        Assert.assertEquals(_allEmployees, HumanResourcesStatistics.moreThanAThousand(_allEmployees));
    }
}
