/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osf.poc.vaadin.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import osf.poc.springremote.model.Property;

@XmlRootElement(name="properties")
public class Properties {
	private int count;
	private List<Property> properties;

	public Properties() {}
	
	public Properties(List<Property> properties) {
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
