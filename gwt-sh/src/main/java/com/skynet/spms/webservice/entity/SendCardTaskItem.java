
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendCardTaskItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendCardTaskItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="manufacturerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partClassify" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendCardTaskItem", propOrder = {
    "manufacturerCode",
    "partClassify",
    "expireDate",
    "partStatusCode",
    "locationNumber"
})
public class SendCardTaskItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String manufacturerCode;
    @XmlElement(required = true)
    protected String partClassify;
    @XmlElement(required = true)
    protected String expireDate;
    @XmlElement(required = true)
    protected String partStatusCode;
    @XmlElement(required = true)
    protected String locationNumber;

    /**
     * Gets the value of the manufacturerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets the value of the manufacturerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturerCode(String value) {
        this.manufacturerCode = value;
    }

    /**
     * Gets the value of the partClassify property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartClassify() {
        return partClassify;
    }

    /**
     * Sets the value of the partClassify property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartClassify(String value) {
        this.partClassify = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the partStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartStatusCode() {
        return partStatusCode;
    }

    /**
     * Sets the value of the partStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartStatusCode(String value) {
        this.partStatusCode = value;
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
