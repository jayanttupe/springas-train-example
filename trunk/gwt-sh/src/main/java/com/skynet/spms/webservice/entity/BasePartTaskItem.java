
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasePartTaskItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasePartTaskItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lotNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="unitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="barcodeTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RFIDTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operationStatus" type="{http://www.skynetsoft.org/RFIDwsdl}TaskItemStatus"/>
 *         &lt;element name="remarkText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postaction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasePartTaskItem", propOrder = {
    "partNumber",
    "partName",
    "partSerialNumber",
    "lotNumber",
    "quantity",
    "unitOfMeasureCode",
    "barcodeTagUUID",
    "rfidTagUUID",
    "operationStatus",
    "remarkText",
    "postaction"
})
@XmlSeeAlso({
    ComplementCodeTaskItem.class,
    PickingTaskItem.class,
    TransferTaskItem.class,
    StockCountTaskItem.class,
    LocationPartItem.class,
    ComplementCodeTaskExeItem.class,
    SendCardTaskExeItem.class,
    ShelvingTaskExeItem.class,
    SendCardTaskItem.class,
    DeliveryTaskExeItem.class,
    PackingTaskExeItem.class,
    PickingTaskExeItem.class,
    ShelvingTaskItem.class,
    StockCountExeItem.class,
    TransferTaskExeItem.class
})
public abstract class BasePartTaskItem {

    @XmlElement(required = true)
    protected String partNumber;
    @XmlElement(required = true)
    protected String partName;
    @XmlElement(required = true)
    protected String partSerialNumber;
    @XmlElement(required = true)
    protected String lotNumber;
    protected double quantity;
    @XmlElement(required = true)
    protected String unitOfMeasureCode;
    @XmlElement(required = true)
    protected String barcodeTagUUID;
    @XmlElement(name = "RFIDTagUUID", required = true)
    protected String rfidTagUUID;
    @XmlElement(required = true)
    protected TaskItemStatus operationStatus;
    @XmlElement(required = true)
    protected String remarkText;
    @XmlElement(required = true)
    protected String postaction;

    /**
     * Gets the value of the partNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the value of the partNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNumber(String value) {
        this.partNumber = value;
    }

    /**
     * Gets the value of the partName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the value of the partName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartName(String value) {
        this.partName = value;
    }

    /**
     * Gets the value of the partSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartSerialNumber() {
        return partSerialNumber;
    }

    /**
     * Sets the value of the partSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartSerialNumber(String value) {
        this.partSerialNumber = value;
    }

    /**
     * Gets the value of the lotNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the value of the lotNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLotNumber(String value) {
        this.lotNumber = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the unitOfMeasureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    /**
     * Sets the value of the unitOfMeasureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasureCode(String value) {
        this.unitOfMeasureCode = value;
    }

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
     * Gets the value of the operationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link TaskItemStatus }
     *     
     */
    public TaskItemStatus getOperationStatus() {
        return operationStatus;
    }

    /**
     * Sets the value of the operationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskItemStatus }
     *     
     */
    public void setOperationStatus(TaskItemStatus value) {
        this.operationStatus = value;
    }

    /**
     * Gets the value of the remarkText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarkText() {
        return remarkText;
    }

    /**
     * Sets the value of the remarkText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarkText(String value) {
        this.remarkText = value;
    }

    /**
     * Gets the value of the postaction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostaction() {
        return postaction;
    }

    /**
     * Sets the value of the postaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostaction(String value) {
        this.postaction = value;
    }

}
