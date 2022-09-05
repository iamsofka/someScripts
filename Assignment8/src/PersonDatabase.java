import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.*;

public final class PersonDatabase {

    private static final Comparator<Person> CompareBySurnameFirstNameAndDate;
    private static final Comparator<Person> CompareByFirstName;
    private static final Comparator<Person> CompareByBirthdate;

    static {
        CompareBySurnameFirstNameAndDate = Comparator.naturalOrder();
        CompareByFirstName = new FirstNameComparator();
        CompareByBirthdate = new BirthdateComparator();
    }

    // assignment 8 - factory method based on deserialization
    public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
        try{
            int size = input.readInt();
            List<Person> people = new ArrayList<>();
            for (int i = 0; input.available() > 0 && i < size; i++) {
                Person person = Person.deserialize(input);
                people.add(person);
            }
            return new PersonDatabase(people);
        } catch (Exception e){
            throw new Assignment08Exception(e);
        }
    }

    // assignment 8
    public void serialize(DataOutputStream output) throws Assignment08Exception{
        try {
            //System.out.println(size());
            output.writeInt(_bySurnameFirstNameAndDate.size());
            _bySurnameFirstNameAndDate.forEach(p -> {
                try {
                    p.serialize(output);
                } catch (Assignment08Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            throw new Assignment08Exception(e);
        }
    }

    public static Map<Date, List<Person>> CreateByBirthdate(List<Person> people){
        Map<Date, List<Person>> byBirthdate = new TreeMap<>();
        if (people == null){
            return byBirthdate;
        }
        for (Person person : people){
            if (byBirthdate.containsKey(person.getBirthdate())){
                List<Person> value = byBirthdate.get(person.getBirthdate());
                value.add(person);
            } else {
                List<Person> value = Arrays.asList(person);
                byBirthdate.put(person.getBirthdate(), value);
            }
        }
        return byBirthdate;
    }

    private final List<Person> _bySurnameFirstNameAndDate;
    private final List<Person> _byFirstName;
    private final List<Person> _byBirthdate;

    private PersonDatabase(List<Person> people){
        people.sort(CompareBySurnameFirstNameAndDate);
        _bySurnameFirstNameAndDate = Collections.unmodifiableList(people);
        people.sort(CompareByFirstName);
        _byFirstName = Collections.unmodifiableList(people);
        people.sort(CompareByBirthdate);
        _byBirthdate = Collections.unmodifiableList(people);
    }

    public List<Person> sortedByFirstName() {
        return _byFirstName; // external rule for ordering (based on Comparator --- FirstNameComparator)
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        return _bySurnameFirstNameAndDate; // natural order (Comparable)
    }

    public List<Person> sortedByBirthdate() {
        return _byBirthdate; // external rule for ordering (based on Comparator --- BirthdateComparator)
    }

    public int size(){
        return _bySurnameFirstNameAndDate.size();
    }

    public PersonDatabase(File file){
        this(InputParser.parse(file));
    }
}