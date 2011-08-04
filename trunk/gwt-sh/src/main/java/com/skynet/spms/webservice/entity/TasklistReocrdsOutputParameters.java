
package com.skynet.spms.webservice.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TasklistReocrdsOutputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TasklistReocrdsOutputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tasklist" type="{http://www.skynetsoft.org/RFIDwsdl}Tasklist" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TasklistReocrdsOutputParameters", propOrder = {
    "tasklist"
})
public class TasklistReocrdsOutputParameters {

    @XmlElement(name = "Tasklist")
    protected List<Tasklist> tasklist;

    /**
     * Gets the value of the tasklist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tasklist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTasklist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tasklist }
     * 
     * 
     */
    public List<Tasklist> getTasklist() {
        if (tasklist == null) {
            tasklist = new ArrayList<Tasklist>();
        }
        return this.tasklist;
    }

	public void setTasklist(List<Tasklist> list) {
		this.tasklist=list;
		
	}

}
