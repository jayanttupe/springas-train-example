
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaskStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TaskStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPN"/>
 *     &lt;enumeration value="WIP"/>
 *     &lt;enumeration value="CAN"/>
 *     &lt;enumeration value="OVR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TaskStatus")
@XmlEnum
public enum TaskStatus {

    OPN,
    WIP,
    CAN,
    OVR;

    public String value() {
        return name();
    }

    public static TaskStatus fromValue(String v) {
        return valueOf(v);
    }

}
