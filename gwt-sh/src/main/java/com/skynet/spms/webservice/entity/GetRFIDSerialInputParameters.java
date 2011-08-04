
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetRFIDSerialInputParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRFIDSerialInputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="euid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opeartor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tag_category" type="{http://www.skynetsoft.org/RFIDwsdl}CategoryEnum"/>
 *         &lt;element name="tag_class" type="{http://www.skynetsoft.org/RFIDwsdl}ClassEnum"/>
 *         &lt;element name="tag_strategy" type="{http://www.skynetsoft.org/RFIDwsdl}StrategyEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRFIDSerialInputParameters", propOrder = {
    "euid",
    "password",
    "opeartor",
    "tagCategory",
    "tagClass",
    "tagStrategy"
})
public class GetRFIDSerialInputParameters {

    @XmlElement(required = true)
    protected String euid;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String opeartor;
    @XmlElement(name = "tag_category", required = true)
    protected CategoryEnum tagCategory;
    @XmlElement(name = "tag_class", required = true)
    protected ClassEnum tagClass;
    @XmlElement(name = "tag_strategy", required = true)
    protected StrategyEnum tagStrategy;

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
     * Gets the value of the tagCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryEnum }
     *     
     */
    public CategoryEnum getTagCategory() {
        return tagCategory;
    }

    /**
     * Sets the value of the tagCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryEnum }
     *     
     */
    public void setTagCategory(CategoryEnum value) {
        this.tagCategory = value;
    }

    /**
     * Gets the value of the tagClass property.
     * 
     * @return
     *     possible object is
     *     {@link ClassEnum }
     *     
     */
    public ClassEnum getTagClass() {
        return tagClass;
    }

    /**
     * Sets the value of the tagClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassEnum }
     *     
     */
    public void setTagClass(ClassEnum value) {
        this.tagClass = value;
    }

    /**
     * Gets the value of the tagStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link StrategyEnum }
     *     
     */
    public StrategyEnum getTagStrategy() {
        return tagStrategy;
    }

    /**
     * Sets the value of the tagStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link StrategyEnum }
     *     
     */
    public void setTagStrategy(StrategyEnum value) {
        this.tagStrategy = value;
    }

}
