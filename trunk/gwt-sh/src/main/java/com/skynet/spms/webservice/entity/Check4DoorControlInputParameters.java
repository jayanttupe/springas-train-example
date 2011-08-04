
package com.skynet.spms.webservice.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Check4DoorControlInputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Check4DoorControlInputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="euid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deviceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="antennaNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="captureTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RFIDTagUUID" type="{http://www.skynetsoft.org/RFIDwsdl}RFIDTagUUID" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Check4DoorControlInputParameters", propOrder = {
    "euid",
    "password",
    "deviceNumber",
    "antennaNumber",
    "captureTime",
    "rfidTagUUID"
})
public class Check4DoorControlInputParameters {

    @XmlElement(required = true)
    protected String euid;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String deviceNumber;
    @XmlElement(required = true)
    protected String antennaNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar captureTime;
    @XmlElement(name = "RFIDTagUUID", required = true)
    protected List<RFIDTagUUID> rfidTagUUID;

    /**
     * Gets the value of the euid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuid() {
        return euid;
    }

    /**
     * Sets the value of the euid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuid(String value) {
        this.euid = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the deviceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * Sets the value of the deviceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceNumber(String value) {
        this.deviceNumber = value;
    }

    /**
     * Gets the value of the antennaNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAntennaNumber() {
        return antennaNumber;
    }

    /**
     * Sets the value of the antennaNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAntennaNumber(String value) {
        this.antennaNumber = value;
    }

    /**
     * Gets the value of the captureTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaptureTime() {
        return captureTime;
    }

    /**
     * Sets the value of the captureTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaptureTime(XMLGregorianCalendar value) {
        this.captureTime = value;
    }

    /**
     * Gets the value of the rfidTagUUID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rfidTagUUID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRFIDTagUUID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RFIDTagUUID }
     * 
     * 
     */
    public List<RFIDTagUUID> getRFIDTagUUID() {
        if (rfidTagUUID == null) {
            rfidTagUUID = new ArrayList<RFIDTagUUID>();
        }
        return this.rfidTagUUID;
    }

}
