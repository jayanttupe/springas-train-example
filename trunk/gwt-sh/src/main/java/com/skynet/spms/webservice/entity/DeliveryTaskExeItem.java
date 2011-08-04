
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeliveryTaskExeItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeliveryTaskExeItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="packingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packingBarcodeTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packingRFIDTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliveryTaskExeItem", propOrder = {
    "packingNumber",
    "packingBarcodeTagUUID",
    "packingRFIDTagUUID"
})
public class DeliveryTaskExeItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String packingNumber;
    @XmlElement(required = true)
    protected String packingBarcodeTagUUID;
    @XmlElement(required = true)
    protected String packingRFIDTagUUID;

    /**
     * Gets the value of the packingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingNumber() {
        return packingNumber;
    }

    /**
     * Sets the value of the packingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingNumber(String value) {
        this.packingNumber = value;
    }

    /**
     * Gets the value of the packingBarcodeTagUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingBarcodeTagUUID() {
        return packingBarcodeTagUUID;
    }

    /**
     * Sets the value of the packingBarcodeTagUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingBarcodeTagUUID(String value) {
        this.packingBarcodeTagUUID = value;
    }

    /**
     * Gets the value of the packingRFIDTagUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingRFIDTagUUID() {
        return packingRFIDTagUUID;
    }

    /**
     * Sets the value of the packingRFIDTagUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingRFIDTagUUID(String value) {
        this.packingRFIDTagUUID = value;
    }

}
