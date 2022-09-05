package eu.glowacki.jaxws.implementation.filter;

import eu.glowacki.jaxws.api.filter.Person;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class Parser {
    public static HashSet<Person> getSet(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        HashSet<Person> personHashSet = new HashSet<>();
        try {
            personHashSet.add(new Person("Alisa", "Kotiun", formatter.parse("13-04-2002")));
            personHashSet.add(new Person("Anna", "Kotiun", formatter.parse("14-12-1995")));
            personHashSet.add(new Person("Stas", "Pozhidaev", formatter.parse("08-02-1993")));
            personHashSet.add(new Person("John", "Tan", formatter.parse("23-04-2005")));
            personHashSet.add(new Person("Ali", "Kotiun", formatter.parse("13-04-2002")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return personHashSet;
    }
}
