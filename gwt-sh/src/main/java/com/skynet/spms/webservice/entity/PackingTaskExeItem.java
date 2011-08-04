
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial;


/**
 * <p>Java class for PackingTaskExeItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackingTaskExeItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="packingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packingBarcodeTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packingRFIDTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "PackingTaskExeItem", propOrder = {
    "packingNumber",
    "packingBarcodeTagUUID",
    "packingRFIDTagUUID",
    "partStatusCode",
    "packagingMaterial"
})
public class PackingTaskExeItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String packingNumber;
    @XmlElement(required = true)
    protected String packingBarcodeTagUUID;
    @XmlElement(required = true)
    protected String packingRFIDTagUUID;
    @XmlElement(required = true)
    protected String partStatusCode;
    
    @XmlElement(required = true)
    protected PackagingMaterial packagingMaterial;

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

	public PackagingMaterial getPackagingMaterial() {
		return packagingMaterial;
	}

	public void setPackagingMaterial(PackagingMaterial packagingMaterial) {
		this.packagingMaterial = packagingMaterial;
	}

}
