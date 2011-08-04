
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="container"/>
 *     &lt;enumeration value="location"/>
 *     &lt;enumeration value="part"/>
 *     &lt;enumeration value="people"/>
 *     &lt;enumeration value="tools"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryEnum")
@XmlEnum
public enum CategoryEnum {

    @XmlEnumValue("container")
    CONTAINER("container"),
    @XmlEnumValue("location")
    LOCATION("location"),
    @XmlEnumValue("part")
    PART("part"),
    @XmlEnumValue("people")
    PEOPLE("people"),
    @XmlEnumValue("tools")
    TOOLS("tools");
    private final String value;

    CategoryEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryEnum fromValue(String v) {
        for (CategoryEnum c: CategoryEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
