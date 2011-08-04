package com.skynet.spms.client.gui.base.i18n;



public interface ContractModuleConst extends BaseConst {

	@DefaultMessage("合同编号")
	String contractNumber();

	@DefaultMessage("优先级")
	String priority();

	@DefaultMessage("飞机机尾号")
	String airIdentificationNumber();

	@DefaultMessage("是否指定运代")
	String buyerFreightAgent();

	@DefaultMessage("运代商")
	String carrierName();

	@DefaultMessage("运代商联系人")
	String carrierLinkMan();

	@DefaultMessage("运代商联系方式")
	String carrierLinkWay();

	@DefaultMessage("交货方式")
	String deliveryType();

	@DefaultMessage("运输方式")
	String transportationCode();

	@DefaultMessage("贸易方式")
	String tradeMethods();

	@DefaultMessage("GTA协议")
	String enterpriseIds();
	
	@DefaultMessage("标签")
	String tag();
	
	@DefaultMessage("标签名")
	String tagName();
	
	@DefaultMessage("插入")
	String insert();
	
	@DefaultMessage("模板类型")
	String tempType();
	
	@DefaultMessage("新建条款")
	String createprovition();
	
	@DefaultMessage("请选择合同模板")
	String msg_select_data();
	
	@DefaultMessage("英文标题")
	String title_en();
	
	@DefaultMessage("中文标题")
	String title_zh();
	
	@DefaultMessage("英文内容")
	String content_en();
	
	@DefaultMessage("中文内容")
	String content_zh();
	
	@DefaultMessage("插入标签")
	String insertTag();
	
}
