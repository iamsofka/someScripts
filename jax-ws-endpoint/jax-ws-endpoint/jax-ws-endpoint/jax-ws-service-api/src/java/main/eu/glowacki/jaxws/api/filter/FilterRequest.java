package eu.glowacki.jaxws.api.filter;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType( //
        name = "FilterRequestMessage", //different from the name of the class
        namespace = "http://glowacki.eu/filter" //
)
public class FilterRequest {
    public String surname;
    public Date date;

    public FilterRequest(){}

    public FilterRequest(String s, Date d){
        surname = s;
        date = d;
    }

    @Override
    public String toString() {
        return "request{" + surname + ", " + date + '}';
    }
}
