package osf.poc.springremote.model;

import java.io.Serializable;

/**
 *
 * @author Jeremy
 */
public class Property implements Serializable {
    
    private String name;
    private String value;
    
    public Property() {
        // Nothing
    }
    
    public Property(String name, String value) {
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
