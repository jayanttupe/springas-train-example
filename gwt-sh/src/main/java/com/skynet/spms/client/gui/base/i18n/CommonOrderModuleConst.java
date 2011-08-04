package com.skynet.spms.client.gui.base.i18n;

/**
 * 提发货模块国际化常量
 * 
 * @author tqc
 * 
 */
public interface CommonOrderModuleConst extends RepairModuleConst {

	@DefaultMessage("基本信息")
	public String base_info();
	
	@DefaultMessage("确定要取消发布吗?")
	public String msg_make_sure_publish();
	
	@DefaultMessage("新建发货指令")
	public String create_order();
	
	@DefaultMessage("依据合同明细")
	public String by_contract_item();
	
	@DefaultMessage("加入")
	public String put_in();
	
	@DefaultMessage("发货指令")
	public String delivery_order();
	
	@DefaultMessage("发货指令明细")
	public String delivery_order_item();
	
	@DefaultMessage("合同编号")
	public String contractNumber();
	
	@DefaultMessage("指令编号")
	public String orderID();
	
	@DefaultMessage("件号")
	public String partNumber();
	
	@DefaultMessage("数量")
	public String quantity();
	
	@DefaultMessage("单价")
	public String unitPriceAmount();
	
	@DefaultMessage("总价")
	public String totalAmount();
	
	@DefaultMessage("请从左边合同明细中拖拽记录")
	public String drag_into_grid();
	
	@DefaultMessage("已发布")
	public String published();
	
	@DefaultMessage("未发布")
	public String unpublished();
	
	@DefaultMessage("未处理")
	public String status1();
	
	@DefaultMessage("处理中")
	public String status2();
	
	@DefaultMessage("已处理")
	public String status3();
	
	@DefaultMessage("新建提货指令")
	public String create_pickup_order();
	
	@DefaultMessage("装箱信息")
	public String box_info();
	
	@DefaultMessage("添加箱")
	public String create_box();
	
	@DefaultMessage("保存箱信息")
	public String save_box_info();
	
	@DefaultMessage("提货指令明细信息")
	public String picup_order_item();
	
	@DefaultMessage("保存指令明细")
	public String save_order_item();
	
	@DefaultMessage("指令明细信息保存成功")
	public String save_order_item_success();
	
	@DefaultMessage("项号")
	public String itemNo();
	
	@DefaultMessage("装箱单号")
	public String packingListNumber();
	
	@DefaultMessage("箱号")
	public String pacakgeNumber();
	
	@DefaultMessage("尺寸")
	public String containerSizeandWeight();
	
	@DefaultMessage("净重")
	public String billOfLadingWeight();
	
	@DefaultMessage("箱号")
	public String vanningID();
	
	@DefaultMessage("提货数量")
	public String picupnumer();
	
	@DefaultMessage("提货指令修改")
	public String modify_picup_order();
	
	@DefaultMessage("删除箱")
	public String drop_box();
	
	@DefaultMessage("该箱子里有指令明细，请先移除箱子里的指令明细!")
	public String msg_box_have_items();
	
	@DefaultMessage("提货指令查看")
	public String view_picup_order();
	
	@DefaultMessage("提货指令")
	public String picup_order();
	
	@DefaultMessage("如果是AOG类型，提交审批后直接可以建立提发货指令，其他类型必须审批通过后,才可以建立!")
	public String msg_aog_warn();
	
	@DefaultMessage("物流指令")
	public String order();
	
	
	
}
