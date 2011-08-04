
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransferTaskItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransferTaskItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="manufacturerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromStockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromLocaitonNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toStockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toLocaitonNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferTaskItem", propOrder = {
    "manufacturerCode",
    "fromStockRoomNumber",
    "fromLocaitonNumber",
    "toStockRoomNumber",
    "toLocaitonNumber",
    "partStatusCode"
})
public class TransferTaskItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String manufacturerCode;
    @XmlElement(required = true)
    protected String fromStockRoomNumber;
    @XmlElement(required = true)
    protected String fromLocaitonNumber;
    @XmlElement(required = true)
    protected String toStockRoomNumber;
    @XmlElement(required = true)
    protected String toLocaitonNumber;
    @XmlElement(required = true)
    protected String partStatusCode;

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
     * Gets the value of the fromStockRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStockRoomNumber() {
        return fromStockRoomNumber;
    }

    /**
     * Sets the value of the fromStockRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStockRoomNumber(String value) {
        this.fromStockRoomNumber = value;
    }

    /**
     * Gets the value of the fromLocaitonNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLocaitonNumber() {
        return fromLocaitonNumber;
    }

    /**
     * Sets the value of the fromLocaitonNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLocaitonNumber(String value) {
        this.fromLocaitonNumber = value;
    }

    /**
     * Gets the value of the toStockRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToStockRoomNumber() {
        return toStockRoomNumber;
    }

    /**
     * Sets the value of the toStockRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToStockRoomNumber(String value) {
        this.toStockRoomNumber = value;
    }

    /**
     * Gets the value of the toLocaitonNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToLocaitonNumber() {
        return toLocaitonNumber;
    }

    /**
     * Sets the value of the toLocaitonNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToLocaitonNumber(String value) {
        this.toLocaitonNumber = value;
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

}
