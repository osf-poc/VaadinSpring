package osf.poc.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import osf.poc.vaadin.model.RestContainer;
import osf.poc.vaadin.model.HttpInvokerContainer;

public class ConfiguratorApplication extends Application {
    private static String[] visibleCols = new String[] { "name", "value" };

    private Table contactList = new Table();
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    
    @Override
    public void init() {
        initLayout();
        initPropertiesList();
    }

    private void initLayout() {
        VerticalLayout left = new VerticalLayout();
        setMainWindow(new Window("Vaadin Configurator", left));
        
        left.setSizeFull();
        left.addComponent(contactList);
        contactList.setSizeFull();
        contactList.setColumnReorderingAllowed(true);
        left.setExpandRatio(contactList, 1);
        left.addComponent(bottomLeftCorner);
    }

    private void initPropertiesList() {
        HttpInvokerContainer container = new HttpInvokerContainer();
        //RestContainer container = new RestContainer();
        
        contactList.setContainerDataSource(container);
        contactList.setVisibleColumns(visibleCols);
        contactList.setSelectable(true);
        contactList.setImmediate(true);
    }
}