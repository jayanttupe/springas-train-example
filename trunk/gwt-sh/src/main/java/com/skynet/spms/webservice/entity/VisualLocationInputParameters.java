
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VisualLocationInputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VisualLocationInputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="euid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opeartor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VisualLocationInputParameters", propOrder = {
    "euid",
    "password",
    "opeartor",
    "stockRoomNumber",
    "locationNumber"
})
public class VisualLocationInputParameters {

    @XmlElement(required = true)
    protected String euid;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String opeartor;
    @XmlElement(required = true)
    protected String stockRoomNumber;
    @XmlElement(required = true)
    protected String locationNumber;

    /**
     * Gets the value of the euid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuid() {
        return euid;
    }

    /**
     * Sets the value of the euid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuid(String value) {
        this.euid = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the opeartor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpeartor() {
        return opeartor;
    }

    /**
     * Sets the value of the opeartor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpeartor(String value) {
        this.opeartor = value;
    }

    /**
     * Gets the value of the stockRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStockRoomNumber() {
        return stockRoomNumber;
    }

    /**
     * Sets the value of the stockRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStockRoomNumber(String value) {
        this.stockRoomNumber = value;
    }

    /**
     * Gets the value of the locationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationNumber() {
        return locationNumber;
    }

    /**
     * Sets the value of the locationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationNumber(String value) {
        this.locationNumber = value;
    }

}
