
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaskItemStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TaskItemStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="waiting "/>
 *     &lt;enumeration value="succee"/>
 *     &lt;enumeration value="failure"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TaskItemStatus")
@XmlEnum
public enum TaskItemStatus {

    @XmlEnumValue("OPN")
    OPN,
    @XmlEnumValue("WIP")
    WIP,
    @XmlEnumValue("CAN")
    CAN,
    @XmlEnumValue("OVR")
    OVR;
    
//    private final String value;

//    TaskItemStatus(String v) {
//        value = v;
//    }

//    public String value() {
//        return value;
//    }

//    public static TaskItemStatus fromValue(String v) {
//        for (TaskItemStatus c: TaskItemStatus.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }

}
