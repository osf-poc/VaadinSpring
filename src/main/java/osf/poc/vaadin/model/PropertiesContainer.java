package osf.poc.vaadin.model;

import osf.poc.springremote.model.PropertyItem;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractInMemoryContainer;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import osf.poc.springremote.resources.IPropertiesResource;

public class PropertiesContainer extends AbstractInMemoryContainer<Integer, String, PropertyItem> implements Filterable {
    private List<PropertyItem> items = new ArrayList<PropertyItem>();
    
    public PropertiesContainer(){
        //ClientConfig config = new DefaultClientConfig(); 
        //Client client = Client.create(config);
        //WebResource service = client.resource("http://localhost:8080/JerseyRest-1.0-SNAPSHOT/");
        //File test = new File("spring-configuration.xml");
        //System.out.println("Test: " + test.getAbsolutePath());
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-configuration.xml");
        IPropertiesResource propertiesResource = (IPropertiesResource)context.getBean("HttpUserService");
        
        //osf.poc.vaadin.model.Property p = new osf.poc.vaadin.model.Property("wicht", "beardy");
        
        //List<osf.poc.vaadin.model.Property> properties = Arrays.asList(p);
        //List<osf.poc.springremote.model.Property> properties = propertiesResource.getProperties();
        List<osf.poc.springremote.model.Property> properties = propertiesResource.getProperties();
        
        List<Integer> ids = new ArrayList<Integer>();
        
        for(int i = 0; i < properties.size(); ++i){
            items.add(new PropertyItem(properties.get(i)));
            ids.add(i);
        }
        
        setAllItemIds(ids);
    }

    @Override
    protected PropertyItem getUnfilteredItem(Object itemId) {
        return items.get((Integer) itemId);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return Arrays.asList("name", "value");
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return getUnfilteredItem(itemId).getItemProperty(propertyId);
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return String.class;
    }

    @Override
    public void addContainerFilter(Filter filter) throws UnsupportedFilterException {
        addFilter(filter);
    }

    @Override
    public void removeContainerFilter(Filter filter) {
        removeFilter(filter);
    }

    @Override
    public void removeAllContainerFilters() {
        removeAllFilters();
    }
}
