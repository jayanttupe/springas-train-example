package com.skynet.spms.client.gui.base.i18n;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.Messages.DefaultMessage;

/**
 * 基本国际化常量
 * 
 * @author tqc
 * 
 */
public interface BaseConst extends Messages {

	@DefaultMessage("新增{0}")
	String addFormTitle(String domain);

	@DefaultMessage("修改{0}")
	String modifyFormTitle(String domain);

	@DefaultMessage("{0}信息")
	String viewFormTitle(String domain);

	@DefaultMessage("关闭")
	String btnClose();

	@DefaultMessage("清空")
	String btnClean();

	@DefaultMessage("取消")
	String btnCancel();

	@DefaultMessage("保存")
	String btnSave();

	@DefaultMessage("保存明细")
	String btnSaveItem();

	@DefaultMessage("保存")
	String btnSaveAdd();

	@DefaultMessage("删除")
	String btnRemove();

	@DefaultMessage("保存添加并发布")
	String btnSaveAddAndPublish();

	@DefaultMessage("修改")
	String btnModify();

	@DefaultMessage("保存")
	String btnSaveModify();

	@DefaultMessage("保存修改并发布")
	String btnSaveModifyAndPublish();

	@DefaultMessage("重置")
	String btnClearForm();

	@DefaultMessage("保存成功")
	String msgAddOpSuccess();

	@DefaultMessage("修改成功")
	String msgModifyOpSuccess();

	@DefaultMessage("删除成功")
	String msgRemoveOpSuccess();

	@DefaultMessage("请选择一条您要操作的数据")
	String msgOpSeletedData();
	
	@DefaultMessage("请选择要建立合同的申请单")
	String msgOpSelectSheetToContant();
	
	@DefaultMessage("请先选择申请单")
	String msgOpSelectSheet();
	
	@DefaultMessage("合同已经存在")
	String msgContantExist();

	@DefaultMessage("业务编号系统自动生成")
	String msgAutoIdInfo();

	@DefaultMessage("您确定要删除该记录吗?")
	String msgDelWarn();

	@DefaultMessage("发布成功")
	String msgPulishSuccess();

	@DefaultMessage("信息已经发布过了")
	String msgHasPublished();

	@DefaultMessage("附件")
	String attachMent();

	@DefaultMessage("{0}列表")
	String msgListTitle(String name);

	@DefaultMessage("{0}明细")
	String msgItemTitle(String name);

	@DefaultMessage("{0}失败")
	String msgFailure(String domain);

	@DefaultMessage("请先保存明细")
	String msgFirstSaveItem();
	
	@DefaultMessage("请先保存申请单")
	String msgFirstSaveSheet();

	@DefaultMessage("提示")
	public String msgTitle();

	@DefaultMessage("确认")
	public String makeSure();

	@DefaultMessage("取消")
	public String cancel();
	
	@DefaultMessage("数据加载中，请稍后......")
	public String msgLoading();
	
	@DefaultMessage("不能添加明细信息，请核对后再试！")
	public String msgCanNotSaveItem();
	
	

	String listTitlePublishStatus();

	String listTitleBusStatus();

	String validateMsgNotNull(String item);

	String validateMsgInt(String item);

	String details();

	String description();

	String remarkText();

	String m_UnitOfMeasureCode();

	String createDate();

	String m_CustomerIdentificationCode();

	String m_ManufacturerCode();

	// 采购计划明细
	String procurementPlanItems();

	// 采购指令明细
	String procurementOrderItems();

	// 执行采购
	String doProcurementOrder();
	
	// 租赁申请单明细
	String leaseRequisitionSheetItems();

	// 租赁合同明细
	String leaseContractItems();

	// 供应商租赁合同明细
	String ssLeaseContractItems();
	
	
	@DefaultMessage("合同基本信息")
	public String contract_base_info_title();
	
	@DefaultMessage("地址信息")
	public String address_info_title();
	
	@DefaultMessage("合同条款")
	public String contract_provision_title();
	
	@DefaultMessage("附件信息")
	public String attachment_title();

}
