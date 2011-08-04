
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
 * <p>Java class for TaskDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tasktype" type="{http://www.skynetsoft.org/RFIDwsdl}TaskType"/>
 *         &lt;element name="taskRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="createBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stockRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskStatus" type="{http://www.skynetsoft.org/RFIDwsdl}TaskStatus"/>
 *         &lt;element name="TransferTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}TransferTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShelvingTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}ShelvingTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ComplementCodeTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}ComplementCodeTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StockCountTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}StockCountTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SendCardTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}SendCardTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PickingTaskItem" type="{http://www.skynetsoft.org/RFIDwsdl}PickingTaskItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskDetails", propOrder = {
    "taskNumber",
    "taskSource",
    "tasktype",
    "taskRefNumber",
    "targetStockRoomNumber",
    "taskDate",
    "createBy",
    "stockRoomNumber",
    "taskStatus",
    "transferTaskItem",
    "shelvingTaskItem",
    "complementCodeTaskItem",
    "stockCountTaskItem",
    "sendCardTaskItem",
    "pickingTaskItem"
})
@XmlSeeAlso({
    GetTaskDetailsOutputParameters.class
})
public class TaskDetails {

    @XmlElement(required = true)
    protected String taskNumber;
    @XmlElement(required = true)
    protected String taskSource;
    @XmlElement(required = true)
    protected TaskType tasktype;
    @XmlElement(required = true)
    protected String taskRefNumber;
    
    @XmlElement(required=true)
    protected String targetStockRoomNumber;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar taskDate;
    @XmlElement(required = true)
    protected String createBy;
    @XmlElement(required = true)
    protected String stockRoomNumber;
    @XmlElement(required = true)
    protected TaskStatus taskStatus;
    @XmlElement(name = "TransferTaskItem")
    protected List<TransferTaskItem> transferTaskItem;
    @XmlElement(name = "ShelvingTaskItem")
    protected List<ShelvingTaskItem> shelvingTaskItem;
    @XmlElement(name = "ComplementCodeTaskItem")
    protected List<ComplementCodeTaskItem> complementCodeTaskItem;
    @XmlElement(name = "StockCountTaskItem")
    protected List<StockCountTaskItem> stockCountTaskItem;
    @XmlElement(name = "SendCardTaskItem")
    protected List<SendCardTaskItem> sendCardTaskItem;
    @XmlElement(name = "PickingTaskItem")
    protected List<PickingTaskItem> pickingTaskItem;

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
     * Gets the value of the createBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Sets the value of the createBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateBy(String value) {
        this.createBy = value;
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
    
    

    public String getTargetStockRoomNumber() {
		return targetStockRoomNumber;
	}

	public void setTargetStockRoomNumber(String targetStockRoomNumber) {
		this.targetStockRoomNumber = targetStockRoomNumber;
	}

	/**
     * Gets the value of the transferTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransferTaskItem }
     * 
     * 
     */
    public List<TransferTaskItem> getTransferTaskItem() {
        if (transferTaskItem == null) {
            transferTaskItem = new ArrayList<TransferTaskItem>();
        }
        return this.transferTaskItem;
    }

    /**
     * Gets the value of the shelvingTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shelvingTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShelvingTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShelvingTaskItem }
     * 
     * 
     */
    public List<ShelvingTaskItem> getShelvingTaskItem() {
        if (shelvingTaskItem == null) {
            shelvingTaskItem = new ArrayList<ShelvingTaskItem>();
        }
        return this.shelvingTaskItem;
    }

    /**
     * Gets the value of the complementCodeTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the complementCodeTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComplementCodeTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplementCodeTaskItem }
     * 
     * 
     */
    public List<ComplementCodeTaskItem> getComplementCodeTaskItem() {
        if (complementCodeTaskItem == null) {
            complementCodeTaskItem = new ArrayList<ComplementCodeTaskItem>();
        }
        return this.complementCodeTaskItem;
    }

    /**
     * Gets the value of the stockCountTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stockCountTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStockCountTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StockCountTaskItem }
     * 
     * 
     */
    public List<StockCountTaskItem> getStockCountTaskItem() {
        if (stockCountTaskItem == null) {
            stockCountTaskItem = new ArrayList<StockCountTaskItem>();
        }
        return this.stockCountTaskItem;
    }

    /**
     * Gets the value of the sendCardTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sendCardTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSendCardTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SendCardTaskItem }
     * 
     * 
     */
    public List<SendCardTaskItem> getSendCardTaskItem() {
        if (sendCardTaskItem == null) {
            sendCardTaskItem = new ArrayList<SendCardTaskItem>();
        }
        return this.sendCardTaskItem;
    }

    /**
     * Gets the value of the pickingTaskItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pickingTaskItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPickingTaskItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PickingTaskItem }
     * 
     * 
     */
    public List<PickingTaskItem> getPickingTaskItem() {
        if (pickingTaskItem == null) {
            pickingTaskItem = new ArrayList<PickingTaskItem>();
        }
        return this.pickingTaskItem;
    }

}
