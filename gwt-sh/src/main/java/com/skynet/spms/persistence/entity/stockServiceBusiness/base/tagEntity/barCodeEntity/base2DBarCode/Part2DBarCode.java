package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base2DBarCode;

/**
 * 二维条码采用Data Matrix 码  每个属性之间以&ldquo;|&rdquo;分割
 * 
 * 
 * 条码编码内容由以下业务属性组成（参见收料单，验收单域模型）
 * 
 * <ol>
 * 	<li>业务流水号</li>
 * 	<li>客户订单号</li>
 * 	<li>件号</li>
 * 	<li>数量</li>
 * 	<li>单位</li>
 * 	<li>供应商代码</li>
 * 	<li>箱号</li>
 * 	<li>修理订单号</li>
 * 	<li>客户代码</li>
 * 	<li>制造商代码</li>
 * 	<li>部件序列编号</li>
 * 	<li>部件唯一标识</li>
 * 	<li>备件证件电子扫描档案号</li>
 * 	<li>库存编号</li>
 * 	<li>仓库货位号</li>
 * 	<li>入库检验单编号</li>
 * 	<li>有效期</li>
 * 	<li>货架寿命代码</li>
 * 	<li>制造日期</li>
 * 	<li>装箱单号</li>
 * </ol>
 * @author 朱江
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
public class Part2DBarCode extends Base2DBarCode {

	/*
	 * PNR 件号
	 */
	private String partsInventoryID;
	/*
	 * MFR 制造商代码
	 */
	private String a;
	/*
	 * SER 部件序列编号
	 */
	private String b;
	/*
	 * CER 备件证件电子扫描档案号
	 */
	private String c;
	/*
	 * STK 库存编号
	 */
	private String d;
	/*
	 * SIN 入库检验单编号
	 */
	private String e;
	/*
	 * EXP 有效期
	 */
	private String f;
	/*
	 * DMF 制造日期
	 */
	private String g;
	/*
	 * SHQ 数量
	 */
	private String h;
	/*
	 * UNT 单位
	 */
	private String i;
	/*
	 * SLC 货架寿命代码
	 */
	private String j;
	
}