package com.skynet.spms.persistence.entity.csdd.c;

/**
 * Specifies the condition of part for sale.
 * 
 * AR As Removed 拆卸件 CR Condition provided on request 定制件 EX Exchange/Lease
 * 交换/租赁件 NU New 新件 OH Overhauled 翻修件 ST Stolen/Missing 遗失件 US Used/Serviceable
 * 可用件 XX Scrap 报废件
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:40
 */
public enum ConditionCode {
	AR, CR, EX, NU, OH, ST, US, XX
}