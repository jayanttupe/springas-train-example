
package com.skynet.spms.webservice.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TaskExecutive complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskExecutive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tasktype" type="{http://www.skynetsoft.org/RFIDwsdl}TaskType"/>
 *         &lt;element name="taskRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="actionBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionDeviceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskStatus" type="{http://www.skynetsoft.org/RFIDwsdl}TaskStatus"/>
 *         &lt;element name="ShelvingTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}ShelvingTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DeliveryTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}DeliveryTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TransferTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}TransferTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ComplementCodeTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}ComplementCodeTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackingTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}PackingTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PickingTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}PickingTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StockCountExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}StockCountExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SendCardTaskExeItem" type="{http://www.skynetsoft.org/RFIDwsdl}SendCardTaskExeItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskExecutive", propOrder = {
    "taskNumber",
    "taskSource",
    "tasktype",
    "taskRefNumber",
    "taskDate",
    "actionBy",
    "actionDeviceNumber",
    "taskStatus",
    "shelvingTaskExeItem",
    "deliveryTaskExeItem",
    "transferTaskExeItem",
    "complementCodeTaskExeItem",
    "packingTaskExeItem",
    "pickingTaskExeItem",
    "stockCountExeItem",
    "sendCardTaskExeItem"
})
@XmlSeeAlso({
    SetTaskDetailsExeInputParameters.class
})
public class TaskExecutive {

    @XmlElement(required = true)
    protected String taskNumber;
    @XmlElement(required = true)
    protected String taskSource;
    @XmlElement(required = true)
    protected TaskType tasktype;
    @XmlElement(required = true)
    protected String taskRefNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar taskDate;
    @XmlElement(required = true)
    protected String actionBy;
    @XmlElement(required = true)
    protected String actionDeviceNumber;
    @XmlElement(required = true)
    protected TaskStatus taskStatus;
    @XmlElement(name = "ShelvingTaskExeItem")
    protected List<ShelvingTaskExeItem> shelvingTaskExeItem;
    @XmlElement(name = "DeliveryTaskExeItem")
    protected List<DeliveryTaskExeItem> deliveryTaskExeItem;
    @XmlElement(name = "TransferTaskExeItem")
    protected List<TransferTaskExeItem> transferTaskExeItem;
    @XmlElement(name = "ComplementCodeTaskExeItem")
    protected List<ComplementCodeTaskExeItem> complementCodeTaskExeItem;
    @XmlElement(name = "PackingTaskExeItem")
    protected List<PackingTaskExeItem> packingTaskExeItem;
    @XmlElement(name = "PickingTaskExeItem")
    protected List<PickingTaskExeItem> pickingTaskExeItem;
    @XmlElement(name = "StockCountExeItem")
    protected List<StockCountExeItem> stockCountExeItem;
    @XmlElement(name = "SendCardTaskExeItem")
    protected List<SendCardTaskExeItem> sendCardTaskExeItem;

    /**
     * Gets the value of the taskNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    /**
     * Sets the value of the taskNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskNumber(String value) {
        this.taskNumber = value;
    }

    /**
     * Gets the value of the taskSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskSource() {
        return taskSource;
    }

    /**
     * Sets the value of the taskSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskSource(String value) {
        this.taskSource = value;
    }

    /**
     * Gets the value of the tasktype property.
     * 
     * @return
     *     possible object is
     *     {@link TaskType }
     *     
     */
    public TaskType getTasktype() {
        return tasktype;
    }

    /**
     * Sets the value of the tasktype property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskType }
     *     
     */
    public void setTasktype(TaskType value) {
        this.tasktype = value;
    }

    /**
     * Gets the value of the taskRefNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskRefNumber() {
        return taskRefNumber;
    }

    /**
     * Sets the value of the taskRefNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskRefNumber(String value) {
        this.taskRefNumber = value;
    }

    /**
     * Gets the value of the taskDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTaskDate() {
        return taskDate;
    }

    /**
     * Sets the value of the taskDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTaskDate(XMLGregorianCalendar value) {
        this.taskDate = value;
    }

    /**
     * Gets the value of the actionBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionBy() {
        return actionBy;
    }

    /**
     * Sets the value of the actionBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionBy(String value) {
        this.actionBy = value;
    }

    /**
     * Gets the value of the actionDeviceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionDeviceNumber() {
        return actionDeviceNumber;
    }

    /**
     * Sets the value of the actionDeviceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionDeviceNumber(String value) {
        this.actionDeviceNumber = value;
    }

    /**
     * Gets the value of the taskStatus property.
     * 
     * @return
     *     possible object is
     *     {@link TaskStatus }
     *     
     */
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * Sets the value of the taskStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskStatus }
     *     
     */
    public void setTaskStatus(TaskStatus value) {
        this.taskStatus = value;
    }

    /**
     * Gets the value of the shelvingTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shelvingTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShelvingTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShelvingTaskExeItem }
     * 
     * 
     */
    public List<ShelvingTaskExeItem> getShelvingTaskExeItem() {
        if (shelvingTaskExeItem == null) {
            shelvingTaskExeItem = new ArrayList<ShelvingTaskExeItem>();
        }
        return this.shelvingTaskExeItem;
    }

    /**
     * Gets the value of the deliveryTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryTaskExeItem }
     * 
     * 
     */
    public List<DeliveryTaskExeItem> getDeliveryTaskExeItem() {
        if (deliveryTaskExeItem == null) {
            deliveryTaskExeItem = new ArrayList<DeliveryTaskExeItem>();
        }
        return this.deliveryTaskExeItem;
    }

    /**
     * Gets the value of the transferTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransferTaskExeItem }
     * 
     * 
     */
    public List<TransferTaskExeItem> getTransferTaskExeItem() {
        if (transferTaskExeItem == null) {
            transferTaskExeItem = new ArrayList<TransferTaskExeItem>();
        }
        return this.transferTaskExeItem;
    }

    /**
     * Gets the value of the complementCodeTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the complementCodeTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComplementCodeTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplementCodeTaskExeItem }
     * 
     * 
     */
    public List<ComplementCodeTaskExeItem> getComplementCodeTaskExeItem() {
        if (complementCodeTaskExeItem == null) {
            complementCodeTaskExeItem = new ArrayList<ComplementCodeTaskExeItem>();
        }
        return this.complementCodeTaskExeItem;
    }

    /**
     * Gets the value of the packingTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packingTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackingTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackingTaskExeItem }
     * 
     * 
     */
    public List<PackingTaskExeItem> getPackingTaskExeItem() {
        if (packingTaskExeItem == null) {
            packingTaskExeItem = new ArrayList<PackingTaskExeItem>();
        }
        return this.packingTaskExeItem;
    }

    /**
     * Gets the value of the pickingTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pickingTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPickingTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PickingTaskExeItem }
     * 
     * 
     */
    public List<PickingTaskExeItem> getPickingTaskExeItem() {
        if (pickingTaskExeItem == null) {
            pickingTaskExeItem = new ArrayList<PickingTaskExeItem>();
        }
        return this.pickingTaskExeItem;
    }

    /**
     * Gets the value of the stockCountExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stockCountExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStockCountExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StockCountExeItem }
     * 
     * 
     */
    public List<StockCountExeItem> getStockCountExeItem() {
        if (stockCountExeItem == null) {
            stockCountExeItem = new ArrayList<StockCountExeItem>();
        }
        return this.stockCountExeItem;
    }

    /**
     * Gets the value of the sendCardTaskExeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sendCardTaskExeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSendCardTaskExeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SendCardTaskExeItem }
     * 
     * 
     */
    public List<SendCardTaskExeItem> getSendCardTaskExeItem() {
        if (sendCardTaskExeItem == null) {
            sendCardTaskExeItem = new ArrayList<SendCardTaskExeItem>();
        }
        return this.sendCardTaskExeItem;
    }

}
