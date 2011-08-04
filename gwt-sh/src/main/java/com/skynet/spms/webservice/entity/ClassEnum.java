
package com.skynet.spms.webservice.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClassEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClassEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="administrator"/>
 *     &lt;enumeration value="chemical"/>
 *     &lt;enumeration value="CO"/>
 *     &lt;enumeration value="dangerous"/>
 *     &lt;enumeration value="equipment"/>
 *     &lt;enumeration value="genTools"/>
 *     &lt;enumeration value="Kit"/>
 *     &lt;enumeration value="manager"/>
 *     &lt;enumeration value="operator"/>
 *     &lt;enumeration value="others"/>
 *     &lt;enumeration value="packingBox"/>
 *     &lt;enumeration value="planTools"/>
 *     &lt;enumeration value="RE"/>
 *     &lt;enumeration value="RO"/>
 *     &lt;enumeration value="standard"/>
 *     &lt;enumeration value="transferBox"/>
 *     &lt;enumeration value="tray"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClassEnum")
@XmlEnum
public enum ClassEnum {

    @XmlEnumValue("administrator")
    ADMINISTRATOR("administrator"),
    @XmlEnumValue("chemical")
    CHEMICAL("chemical"),
    CO("CO"),
    @XmlEnumValue("dangerous")
    DANGEROUS("dangerous"),
    @XmlEnumValue("equipment")
    EQUIPMENT("equipment"),
    @XmlEnumValue("genTools")
    GEN_TOOLS("genTools"),
    @XmlEnumValue("Kit")
    KIT("Kit"),
    @XmlEnumValue("manager")
    MANAGER("manager"),
    @XmlEnumValue("operator")
    OPERATOR("operator"),
    @XmlEnumValue("others")
    OTHERS("others"),
    @XmlEnumValue("packingBox")
    PACKING_BOX("packingBox"),
    @XmlEnumValue("planTools")
    PLAN_TOOLS("planTools"),
    RE("RE"),
    RO("RO"),
    @XmlEnumValue("standard")
    STANDARD("standard"),
    @XmlEnumValue("transferBox")
    TRANSFER_BOX("transferBox"),
    @XmlEnumValue("tray")
    TRAY("tray");
    private final String value;

    ClassEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClassEnum fromValue(String v) {
        for (ClassEnum c: ClassEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
