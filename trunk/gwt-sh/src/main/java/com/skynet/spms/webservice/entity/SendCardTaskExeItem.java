
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendCardTaskExeItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendCardTaskExeItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.skynetsoft.org/RFIDwsdl}BasePartTaskItem">
 *       &lt;sequence>
 *         &lt;element name="partStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partRFIDTagUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendCardTaskExeItem", propOrder = {
    "partStatusCode",
    "partRFIDTagUUID"
})
public class SendCardTaskExeItem
    extends BasePartTaskItem
{

    @XmlElement(required = true)
    protected String partStatusCode;
    @XmlElement(required = true)
    protected String partRFIDTagUUID;

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

    /**
     * Gets the value of the partRFIDTagUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartRFIDTagUUID() {
        return partRFIDTagUUID;
    }

    /**
     * Sets the value of the partRFIDTagUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartRFIDTagUUID(String value) {
        this.partRFIDTagUUID = value;
    }

}
