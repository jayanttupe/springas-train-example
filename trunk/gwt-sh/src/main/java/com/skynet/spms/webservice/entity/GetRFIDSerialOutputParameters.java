
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetRFIDSerialOutputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRFIDSerialOutputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tagIdentifierCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRFIDSerialOutputParameters", propOrder = {
    "tagIdentifierCode"
})
public class GetRFIDSerialOutputParameters {

    @XmlElement(required = true)
    protected String tagIdentifierCode;

    /**
     * Gets the value of the tagIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagIdentifierCode() {
        return tagIdentifierCode;
    }

    /**
     * Sets the value of the tagIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagIdentifierCode(String value) {
        this.tagIdentifierCode = value;
    }

}
