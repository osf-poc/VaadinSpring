package osf.poc.springremote.resources;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import osf.poc.model.Property;

@WebService(serviceName="PropertiesJAXWS")
public interface IPropertiesWebService {
    @WebMethod
    public List<Property> getProperties();
}