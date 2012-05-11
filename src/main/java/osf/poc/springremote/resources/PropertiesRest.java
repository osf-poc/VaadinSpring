package osf.poc.springremote.resources;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import osf.poc.model.Property;

@XmlRootElement(name="properties")
public class PropertiesRest {
	private int count;
	private List<Property> properties;

	public PropertiesRest() {}
	
	public PropertiesRest(List<Property> properties) {
		this.properties = properties;
		this.count = properties.size();
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElement(name="property")
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
