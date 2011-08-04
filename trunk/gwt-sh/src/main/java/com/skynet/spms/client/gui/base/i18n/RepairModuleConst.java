package com.skynet.spms.client.gui.base.i18n;




/**
 * 送修模块国际化常量
 * 
 * @author tqc
 * 
 */
public interface RepairModuleConst extends BaseConst {

	@DefaultMessage("修改客户送修送检指令")
	public String repair_update_title();
	
	@DefaultMessage("查看客户送修送检指令")
	public String repair_view_title();
	
	@DefaultMessage("新建客户送修送检指令")
	public String repair_add_titile();
	
	@DefaultMessage("请先取消发布，方可修改")
	public String msg_publish_before_update();
	
	@DefaultMessage("运代商")
	public String title_for_shipmanager();
	
	@DefaultMessage("合同总金额")
	public String contract_amount();
	
	@DefaultMessage("送修备件明细")
	public String repair_item_title();
	
	
	
	@DefaultMessage("供应商合同未建立")
	public String supplier_contract_cantnot_create();
	
	@DefaultMessage("确认上传了《客户送修确认函附件》后，合同方可提交终审。")
	public String makesure_customer_has_accept();
	
	@DefaultMessage("您确认上传了客户送修确认函吗")
	public String are_you_uploaded_the_file();
	
	@DefaultMessage("客户送修确认函面板")
	public String customer_makesure_title();
	
	@DefaultMessage("检验费用")
	public String check_cast();
	
	@DefaultMessage("修理记录")
	public String fix_record();
	
	@DefaultMessage("同带数据")
	public String query_data_from_supplier_contract();
	
	@DefaultMessage("添加明细")
	public String btn_item_add();

	@DefaultMessage("明细保存")
	public String btn_item_save();
	
	@DefaultMessage("费用处理明细项")
	public String do_with_cast_item();
	
	@DefaultMessage("供应商费用处理明细项")
	public String supplier_do_with_cast_item();
	
	@DefaultMessage("修理报价明细项")
	public String supplier_rq_quote_item();
	
	@DefaultMessage("请先保存基本信")
	public String warn_save_base_info_first();
	
	@DefaultMessage("修改客户送修合同")
	public String modify_repari_contract();
	
	@DefaultMessage("查看客户送修合同")
	public String view_rrpair_contract();
	
	@DefaultMessage("操作成功")
	public String opp_success();
	
	@DefaultMessage("操作失败")
	public String opp_failed();
	
	@DefaultMessage("请先进行合同变更操作")
	public String publish_contract_changing();
	
	@DefaultMessage("申请单基本信息")
	public String sheet_base_info();
	
	@DefaultMessage("部件拆换信息")
	public String part_ass_data_info();
	
	@DefaultMessage("修改送修申请单")
	public String modify_repair_sheet_title();
	
	@DefaultMessage("查看送修申请单")
	public String view_repair_sheet_title();
	
	@DefaultMessage("添加送修申请单")
	public String create_repair_sheet_title();
	
	@DefaultMessage("合同变更")
	public String toolStrip_contract_changing();
	
	@DefaultMessage("发布变更")
	public String toolStrip_publish_contract_changing();
	
	@DefaultMessage("提交审批")
	public String toolStrip_submit_check();
	
	@DefaultMessage("初审")
	public String toolStrip_submit_first();
	
	@DefaultMessage("终审")
	public String toolStrip_submit_end();
	
	@DefaultMessage("业务指令")
	public String toolStrip_biz_order();
	
	@DefaultMessage("新建客户送修送检指令")
	public String toolStrip_create_repair_order();
	
	@DefaultMessage("飞机机尾号")
	public String aircraftIdentificationNumber();
	
	
	
}
