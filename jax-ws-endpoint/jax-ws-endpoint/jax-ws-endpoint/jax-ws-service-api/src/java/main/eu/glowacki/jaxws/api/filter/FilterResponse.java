package eu.glowacki.jaxws.api.filter;

import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;

@XmlType( //
        name = "FilterResponseMessage", //different from the name of the class
        namespace = "http://glowacki.eu/filter" //
)
public class FilterResponse {
    public HashSet<Person> personSet;

    public FilterResponse(){}

    public FilterResponse(HashSet<Person> p){
        personSet = p;
    }

    @Override
    public String toString() {
        String result = "";
        for(Person person : personSet){
            result = result + person.toString() + "\n";
        }
        return result;
    }

    public HashSet<Person> getPersonSet() {
        return personSet;
    }
}
