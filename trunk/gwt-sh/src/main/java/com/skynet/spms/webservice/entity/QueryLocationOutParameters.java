
package com.skynet.spms.webservice.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryLocationOutParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryLocationOutParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocationInfo" type="{http://www.skynetsoft.org/RFIDwsdl}LocationInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryLocationOutParameters", propOrder = {
    "locationInfo"
})
public class QueryLocationOutParameters {

    @XmlElement(name = "LocationInfo")
    protected List<LocationInfo> locationInfo;

    /**
     * Gets the value of the locationInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locationInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationInfo }
     * 
     * 
     */
    public List<LocationInfo> getLocationInfo() {
        if (locationInfo == null) {
            locationInfo = new ArrayList<LocationInfo>();
        }
        return this.locationInfo;
    }
    
    public void setLocationList(List<LocationInfo>  locList){
    	this.locationInfo=locList;
    }
}
