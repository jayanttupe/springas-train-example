
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaskType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TaskType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Checking"/>
 *     &lt;enumeration value="Shelving"/>
 *     &lt;enumeration value="Transferring"/>
 *     &lt;enumeration value="ComplementCode"/>
 *     &lt;enumeration value="StockCount"/>
 *     &lt;enumeration value="Packing"/>
 *     &lt;enumeration value="Picking"/>
 *     &lt;enumeration value="Delivering"/>
 *     &lt;enumeration value="SpareBoxing"/>
 *     &lt;enumeration value="SendCardTask"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TaskType")
@XmlEnum
public enum TaskType {

    @XmlEnumValue("Checking")
    CHECKING("Checking"),
    @XmlEnumValue("Shelving")
    SHELVING("Shelving"),
    @XmlEnumValue("Transferring")
    TRANSFERRING("Transferring"),
    @XmlEnumValue("ComplementCode")
    COMPLEMENT_CODE("ComplementCode"),
    @XmlEnumValue("StockCount")
    STOCK_COUNT("StockCount"),
    @XmlEnumValue("Packing")
    PACKING("Packing"),
    @XmlEnumValue("Picking")
    PICKING("Picking"),
    @XmlEnumValue("Delivering")
    DELIVERING("Delivering"),
    @XmlEnumValue("SpareBoxing")
    SPARE_BOXING("SpareBoxing"),
    @XmlEnumValue("SendCardTask")
    SEND_CARD_TASK("SendCardTask"),
    @XmlEnumValue("LocationStockCount")
    LOCATION_STOCK_COUNT("LocationStockCount");
    private final String value;

    TaskType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TaskType fromValue(String v) {
        for (TaskType c: TaskType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
