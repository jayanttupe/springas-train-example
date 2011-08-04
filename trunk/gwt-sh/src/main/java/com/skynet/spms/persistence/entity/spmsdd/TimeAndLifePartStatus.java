package com.skynet.spms.persistence.entity.spmsdd;

/**
 * 本系统中合同类型的枚举字典
 * @author 曹宏炜
 * @version 1.0
 * @created 21-四月-2011 12:23:42
 */
public enum TimeAndLifePartStatus {
	// 监控中
	Monitoring,
	// 已超期
	Exceed,
	// 已预警
	Warn,
	// 已申请
	Apply,
	// 已报废
	Discard
}