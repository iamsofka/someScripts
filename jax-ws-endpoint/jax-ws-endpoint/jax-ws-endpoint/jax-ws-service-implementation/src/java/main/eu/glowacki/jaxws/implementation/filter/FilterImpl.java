package eu.glowacki.jaxws.implementation.filter;

import eu.glowacki.jaxws.api.filter.FilterRequest;
import eu.glowacki.jaxws.api.filter.FilterResponse;
import eu.glowacki.jaxws.api.filter.IFilter;
import eu.glowacki.jaxws.api.filter.Person;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebService( //
        name = "IFilter", //
        targetNamespace = "http://glowacki.eu/filter" //
)
public class FilterImpl implements IFilter {

    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private static final HashSet<Person> personSet;

    static {
        personSet = Parser.getSet();
    }

    public static void main(String... args) {
        Endpoint.publish(IFilter.URI, new FilterImpl());
        LOGGER.info("SERVICE STARTED");
    }

    @Override
    public FilterResponse search(FilterRequest request) {
        LOGGER.info(request.toString());
        return new FilterResponse(calculateResponse(request));
    }

    public HashSet<Person> calculateResponse(FilterRequest request){
        return  personSet.stream()
                .filter(p -> p.surname.equals(request.surname))
                .filter(p -> p.birthdate.equals(request.date))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
