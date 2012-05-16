package osf.poc.vaadin;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import osf.poc.vaadin.model.HttpInvokerContainer;
import osf.poc.vaadin.model.JAXContainer;
import osf.poc.vaadin.model.RestContainer;

/**
 * Handle main menu items
 */
public class MainMenuCommand implements MenuBar.Command {

    private final ConfiguratorApplication application;
    
    public MainMenuCommand(ConfiguratorApplication application) {
        this.application = application;
    }

    @Override
    public void menuSelected(MenuItem selectedItem) {
        if(selectedItem.getText().equals(ConfiguratorApplication.MENU_CONFIG_INVOKER))
            application.setPanel(new ConfigPanel(new HttpInvokerContainer()));
        else if(selectedItem.getText().equals(ConfiguratorApplication.MENU_CONFIG_REST))
            application.setPanel(new ConfigPanel(new RestContainer()));
        else if(selectedItem.getText().equals(ConfiguratorApplication.MENU_CONFIG_JAXWS))
            application.setPanel(new ConfigPanel(new JAXContainer()));
        else if(selectedItem.getText().equals(ConfiguratorApplication.MENU_ABOUT))
            application.setPanel(new AboutPanel());
    }
}
