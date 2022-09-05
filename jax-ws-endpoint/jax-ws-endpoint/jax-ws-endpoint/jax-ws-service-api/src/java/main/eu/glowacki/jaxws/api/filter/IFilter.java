package eu.glowacki.jaxws.api.filter;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService( //
        name = "IFilter", //
        targetNamespace = "http://glowacki.eu/filter" //
)
public interface IFilter {
    public static final String URI = "http://localhost:8080/filter";

    @WebMethod(action = "http://glowacki.eu/filter/search")
    FilterResponse search(FilterRequest request);

}
