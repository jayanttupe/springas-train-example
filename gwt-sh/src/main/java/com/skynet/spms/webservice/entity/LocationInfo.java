
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barcodeTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RFIDTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fullLocationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationInfo", propOrder = {
    "barcodeTagUUID",
    "rfidTagUUID",
    "fullLocationNumber"
})
public class LocationInfo {

    @XmlElement(required = true)
    protected String barcodeTagUUID;
    @XmlElement(name = "RFIDTagUUID", required = true)
    protected String rfidTagUUID;
    @XmlElement(required = true)
    protected String fullLocationNumber;

    /**
     * Gets the value of the barcodeTagUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcodeTagUUID() {
        return barcodeTagUUID;
    }

    /**
     * Sets the value of the barcodeTagUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcodeTagUUID(String value) {
        this.barcodeTagUUID = value;
    }

    /**
     * Gets the value of the rfidTagUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFIDTagUUID() {
        return rfidTagUUID;
    }

    /**
     * Sets the value of the rfidTagUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFIDTagUUID(String value) {
        this.rfidTagUUID = value;
    }

    /**
     * Gets the value of the fullLocationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullLocationNumber() {
        return fullLocationNumber;
    }

    /**
     * Sets the value of the fullLocationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullLocationNumber(String value) {
        this.fullLocationNumber = value;
    }

}
