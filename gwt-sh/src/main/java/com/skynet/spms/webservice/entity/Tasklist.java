
package com.skynet.spms.webservice.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Tasklist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tasklist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tasktype" type="{http://www.skynetsoft.org/RFIDwsdl}TaskType"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="taskRefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskStatus" type="{http://www.skynetsoft.org/RFIDwsdl}TaskStatus"/>
 *         &lt;element name="creatBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionDevice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tasklist", propOrder = {
    "taskNumber",
    "tasktype",
    "description",
    "taskDate",
    "taskRefNumber",
    "taskSource",
    "taskStatus",
    "creatBy",
    "actionBy",
    "actionDate",
    "actionDevice"
})
public class Tasklist {

    @XmlElement(required = true)
    protected String taskNumber;
    @XmlElement(required = true)
    protected TaskType tasktype;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar taskDate;
    @XmlElement(required = true)
    protected String taskRefNumber;
    @XmlElement(required = true)
    protected String taskSource;
    @XmlElement(required = true)
    protected TaskStatus taskStatus;
    @XmlElement(required = true)
    protected String creatBy;
    @XmlElement(required = true)
    protected String actionBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar actionDate;
    @XmlElement(required = true)
    protected String actionDevice;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
     * Gets the value of the creatBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatBy() {
        return creatBy;
    }

    /**
     * Sets the value of the creatBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatBy(String value) {
        this.creatBy = value;
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
     * Gets the value of the actionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getActionDate() {
        return actionDate;
    }

    /**
     * Sets the value of the actionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionDate(XMLGregorianCalendar value) {
        this.actionDate = value;
    }

    /**
     * Gets the value of the actionDevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionDevice() {
        return actionDevice;
    }

    /**
     * Sets the value of the actionDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionDevice(String value) {
        this.actionDevice = value;
    }

}
