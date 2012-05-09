package osf.poc.springremote.resources;

import java.util.List;
//import osf.poc.vaadin.model.Property;
import osf.poc.springremote.model.Property;

/**
 *
 * @author Jeremy
 */
public interface IPropertiesResource {

    public List<Property> getProperties();
    
}