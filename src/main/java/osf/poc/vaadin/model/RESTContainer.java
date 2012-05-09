package osf.poc.vaadin.model;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractInMemoryContainer;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import osf.poc.springremote.model.PropertyItem;

public class RESTContainer extends AbstractInMemoryContainer<Integer, String, PropertyItem> implements Filterable {
    private List<PropertyItem> items = new ArrayList<PropertyItem>();
    
    public RESTContainer(){
        Properties props = new RestTemplate().getForObject("http://localhost:8080/SpringRest-1.0-SNAPSHOT/rest/properties", Properties.class);
        
        List<Integer> ids = new ArrayList<Integer>();
        
        for(int i = 0; i < props.getProperties().size(); ++i){
            items.add(new PropertyItem(props.getProperties().get(i)));
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
