package com.skynet.spms.persistence.entity.spmsdd;

/**
 * 为了便于理解，将所有的术语分为4个基本不同的类型。第一组为&ldquo;E&rdquo;组（EX
 * WORKS），指卖方仅在自己的地点为买方备妥货物；第二组&ldquo;F&rdquo;
 * 组（FCA、FAS和FOB），指卖方需将货物交至买方指定的承运人；第三组&ldquo;C&rdquo;
 * 组（CFR、CIF、CPT和CIP），指卖方须订立运输合同，但对货物灭失或损坏的风险以及装船和启运后发生意外所发生的额外费用，卖方不承担责任；第四组&ldquo
 * ;D&rdquo;组（DAF、DES、DEQ、DDU和DDP），指卖方须承担把货物交至目的地国所需的全部费用和风险。下表反映了这种分类方法：
 * 
 * 2000年国际贸易术语解释通则
 * 组别 术语缩写 术语英文名称 术语中文名称
 * E组发货 EXW EX works 工厂交货（……指定地点）
 * 
 * F组主要运费未付 FCA Free Carrier 交至承运人（……指定地点）
 * FAS Free Along Side 船边交货（……指定装运港）
 * FOB Free On Board 船上交货（……指定装运港）
 * 
 * C组主要运费已付 CFR Cost and Freight 成本加运费（……指定目的港）
 * CIF Cost,Insurance and Freight 成本、保险加运费付至（……指定目的港）
 * CPT Carriage Paid to 运费付至（……指定目的港）
 * CIP Carriage and lnsurance Paid to 运费、保险费付至（……指定目的地）
 * 
 * D组货到 DAF Delivered at Frontier 边境交货（……指定地点）
 * DES Delivered EX Ship 目的港船上交货（……指定目的港）
 * DEQ Delivered EX Quay 目的港码头交货（……指定目的港）
 * DDU Delivered Duty Unpaid 未完税交货（……指定目的地）
 * DDP Delivered Duty Paid 完税后交货（……指定目的地）
 * 
 * 详细信息可参考 http://www.searates.com/cn/reference/incoterms/
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:43
 */
public enum DeliveryType {
	EXW,
	FOB,
	CIF,
	DDU,
	DOD
}