
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ShelvingTaskItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShelvingTaskItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="internationalCommodityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supplierCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="manufacturDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="manufacturerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recommendLocationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cerificateBarcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ShelvingTaskItem", propOrder = {
    "internationalCommodityCode",
    "supplierCode",
    "manufacturDate",
    "manufacturerCode",
    "partDescription",
    "recommendLocationNumber",
    "stockRoomNumber",
    "expireDate",
    "cerificateBarcode",
    "partStatusCode"
})
public class ShelvingTaskItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String internationalCommodityCode;
    @XmlElement(required = true)
    protected String supplierCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar manufacturDate;
    @XmlElement(required = true)
    protected String manufacturerCode;
    @XmlElement(required = true)
    protected String partDescription;
    @XmlElement(required = true)
    protected String recommendLocationNumber;
    @XmlElement(required = true)
    protected String stockRoomNumber;
    @XmlElement(required = true)
    protected String expireDate;
    @XmlElement(required = true)
    protected String cerificateBarcode;
    @XmlElement(required = true)
    protected String partStatusCode;

    /**
     * Gets the value of the internationalCommodityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalCommodityCode() {
        return internationalCommodityCode;
    }

    /**
     * Sets the value of the internationalCommodityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalCommodityCode(String value) {
        this.internationalCommodityCode = value;
    }

    /**
     * Gets the value of the supplierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Sets the value of the supplierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierCode(String value) {
        this.supplierCode = value;
    }

    /**
     * Gets the value of the manufacturDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getManufacturDate() {
        return manufacturDate;
    }

    /**
     * Sets the value of the manufacturDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setManufacturDate(XMLGregorianCalendar value) {
        this.manufacturDate = value;
    }

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
     * Gets the value of the partDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartDescription() {
        return partDescription;
    }

    /**
     * Sets the value of the partDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartDescription(String value) {
        this.partDescription = value;
    }

    /**
     * Gets the value of the recommendLocationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendLocationNumber() {
        return recommendLocationNumber;
    }

    /**
     * Sets the value of the recommendLocationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendLocationNumber(String value) {
        this.recommendLocationNumber = value;
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
     * Gets the value of the cerificateBarcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCerificateBarcode() {
        return cerificateBarcode;
    }

    /**
     * Sets the value of the cerificateBarcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCerificateBarcode(String value) {
        this.cerificateBarcode = value;
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
