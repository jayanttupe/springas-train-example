
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StockCountExeItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StockCountExeItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="stockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "StockCountExeItem", propOrder = {
    "stockRoomNumber",
    "locationNumber",
    "partStatusCode",
    "realityQuantity"
})
public class StockCountExeItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String stockRoomNumber;
    @XmlElement(required = true)
    protected String locationNumber;
    @XmlElement(required = true)
    protected String partStatusCode;
    
    
    @XmlElement(required=true)
    protected double realityQuantity;


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
    

	public double getRealityQuantity() {
		return realityQuantity;
	}

	public void setRealityQuantity(double realityQuantity) {
		this.realityQuantity = realityQuantity;
	}
    

}
