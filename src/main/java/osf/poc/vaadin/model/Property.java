package osf.poc.vaadin.model;

import javax.xml.bind.annotation.XmlRootElement;

public class Property {
    private String name;
    private String value;
    
    public Property(){
        super();
        
        //Nothing
    }
    
    public Property(String name, String value){
        super();
        
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
