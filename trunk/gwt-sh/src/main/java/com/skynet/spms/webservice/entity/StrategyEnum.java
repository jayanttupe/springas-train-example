
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StrategyEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StrategyEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="batch"/>
 *     &lt;enumeration value="jobNumber"/>
 *     &lt;enumeration value="package"/>
 *     &lt;enumeration value="partContainer"/>
 *     &lt;enumeration value="serial"/>
 *     &lt;enumeration value="tool"/>
 *     &lt;enumeration value="toolContainer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StrategyEnum")
@XmlEnum
public enum StrategyEnum {

    @XmlEnumValue("batch")
    BATCH("batch"),
    @XmlEnumValue("jobNumber")
    JOB_NUMBER("jobNumber"),
    @XmlEnumValue("package")
    PACKAGE("package"),
    @XmlEnumValue("partContainer")
    PART_CONTAINER("partContainer"),
    @XmlEnumValue("serial")
    SERIAL("serial"),
    @XmlEnumValue("tool")
    TOOL("tool"),
    @XmlEnumValue("toolContainer")
    TOOL_CONTAINER("toolContainer");
    private final String value;

    StrategyEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StrategyEnum fromValue(String v) {
        for (StrategyEnum c: StrategyEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
