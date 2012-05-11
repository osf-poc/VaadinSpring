package osf.poc.vaadin.model;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractInMemoryContainer;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import osf.poc.springremote.resources.IPropertiesHttpInvoker;

/**
 * Container data model for mapping tables with configuration properties
 * using Sprint Remoting and the Http Invoker method
 */
public class HttpInvokerContainer extends AbstractInMemoryContainer<Integer, String, PropertyItem> implements Filterable {
    private List<PropertyItem> items = new ArrayList<PropertyItem>();
    
    public HttpInvokerContainer(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-configuration.xml");
        IPropertiesHttpInvoker propertiesResource = (IPropertiesHttpInvoker)context.getBean("ConfigurationService");
        
        List<osf.poc.model.Property> properties = propertiesResource.getProperties();
        
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
