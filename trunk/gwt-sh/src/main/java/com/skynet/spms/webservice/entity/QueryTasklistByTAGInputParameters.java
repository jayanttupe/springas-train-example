
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryTasklistByTAGInputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryTasklistByTAGInputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="euid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opeartor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tagIdentifierCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryTasklistByTAGInputParameters", propOrder = {
    "euid",
    "password",
    "opeartor",
    "tagIdentifierCode"
})
public class QueryTasklistByTAGInputParameters {

    @XmlElement(required = true)
    protected String euid;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String opeartor;
    @XmlElement(required = true)
    protected String tagIdentifierCode;

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
     * Gets the value of the opeartor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpeartor() {
        return opeartor;
    }

    /**
     * Sets the value of the opeartor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpeartor(String value) {
        this.opeartor = value;
    }

    /**
     * Gets the value of the tagIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagIdentifierCode() {
        return tagIdentifierCode;
    }

    /**
     * Sets the value of the tagIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagIdentifierCode(String value) {
        this.tagIdentifierCode = value;
    }

}
