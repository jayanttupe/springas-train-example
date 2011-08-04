package com.skynet.spms.persistence.entity.spmsdd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author 朱江
 * @version 1.0
 * @created 10-三月-2011 11:10:40
 */
@XmlType(name = "PackagingMaterial")
@XmlEnum
public enum PackagingMaterial {
	/*
	 * 塑料,铁箱,木箱,无包装,纸箱
	 */
	@XmlEnumValue("PLASTIC")
	PLASTIC,
	
	@XmlEnumValue("IRON")
	IRON,

	@XmlEnumValue("WOOD")
	WOOD,

	@XmlEnumValue("NOP")
	NOP,

	@XmlEnumValue("PAPER")
	PAPER
}