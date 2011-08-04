package com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd;

/**
 * 1位英文字母编码，区分单列和&ldquo;背靠背&rdquo;两种货架。
 * 单列的货架即用&ldquo;A&rdquo;表示，
 * &ldquo;背靠背&rdquo;货架用&ldquo;A&rdquo;&ldquo;B&rdquo;表示。
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:19
 */
public enum StorageRacksTypeCode {
//	1FN,
//	1FM,
//	1FB,
//	2FE,
//	1FH,
//	1FW,
//	2FL
	
	// A型货架
	StorageRacksTypeCode1,
	// B型货架
	StorageRacksTypeCode2
}