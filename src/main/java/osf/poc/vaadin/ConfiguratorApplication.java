package osf.poc.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.*;
import osf.poc.springremote.resources.IPropertiesHttpInvoker;
import osf.poc.vaadin.model.HttpInvokerContainer;

/**
 * Main class for the Vaadin configuration application
 */
public class ConfiguratorApplication extends Application {
    
    // Constants
    public static final String MENU_CONFIG_INVOKER = "Configuration HttpInvoker";
    public static final String MENU_CONFIG_REST = "Configuration REST";
    public static final String MENU_ABOUT = "About";
    
    // Helper to access our Spring beans
    private SpringContextHelper contextHelper;
    
    // Page components
    private final Label title = new Label("Configuration application using Vaadin/Spring");
    private final MenuBar menuBar = new MenuBar();
    Panel currentPanel;
    
    // Page layouts
    private VerticalLayout mainLayout = new VerticalLayout();
    
    @Override
    public void init() {
        // Must be created here (spring bean not available at construction)
        contextHelper = new SpringContextHelper(this);
        currentPanel = new ConfigPanel(new HttpInvokerContainer((IPropertiesHttpInvoker)getSpringBean("ConfigurationService")));
        
        initLayout();
    }

    private void initLayout() {
        final Window main = new Window("Vaadin Spring Configurator", mainLayout);
        //main.setName("vaadin-spring-configurator");
        main.setDebugId("WindowId");            // For performance tests
        mainLayout.setDebugId("AppLayoutId");   // For performance tests
        setMainWindow(main);
        
        // Title
        title.setStyleName("h1");
        title.setWidth(null);
        mainLayout.addComponent(title);
        
        // Menu
        MainMenuCommand command = new MainMenuCommand(this);
        menuBar.addItem(MENU_CONFIG_INVOKER, command);
        menuBar.addItem(MENU_CONFIG_REST, command);
        menuBar.addItem(MENU_ABOUT, command);
        mainLayout.addComponent(menuBar);
        
        // Panel
        mainLayout.addComponent(currentPanel);
        
        // Layout properties
        mainLayout.setSpacing(true);
        mainLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
    }
    
    Object getSpringBean(String name) {
        return contextHelper.getBean(name);
    }
    
    void setPanel(Panel newPanel) {
        mainLayout.replaceComponent(currentPanel, newPanel);
        currentPanel = newPanel;
    }
}