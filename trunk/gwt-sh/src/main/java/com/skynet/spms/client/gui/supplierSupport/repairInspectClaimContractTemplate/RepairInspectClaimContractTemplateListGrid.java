package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 供应商修理检验合同列表
 * 
 * @author tqc
 * 
 */
public class RepairInspectClaimContractTemplateListGrid extends ListGrid {

	public RepairInspectClaimContractTemplateListGrid() {
		this.setWidth100();
		this.setMargin(5);
		this.setCellHeight(22);
		this.setShowFilterEditor(true);
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setAutoFetchData(true);
		this.setShowAllRecords(false);
		this.setDataPageSize(10);
		this.setCanEdit(false);
	}

	/**
	 * 绘制表格列
	 */
	public void drawGrid() {

		ListGridField contractNumberField = new ListGridField("contractNumber"/*,
				"合同编号"*/);
		contractNumberField.setCanEdit(false);
		contractNumberField.setCanFilter(true);

		ListGridField priorityField = new ListGridField("m_Priority"/*, "优先级"*/);
		priorityField.setCanEdit(false);
		priorityField.setCanFilter(true);

		ListGridField extendedValueTotalAmountField = new ListGridField(
				"extendedValueTotalAmount"/*, "总金额"*/);
		extendedValueTotalAmountField.setCanEdit(false);
		extendedValueTotalAmountField.setCanFilter(true);

		ListGridField makeByField = new ListGridField("makeBy"/*, "制定人"*/);
		makeByField.setCanEdit(false);
		makeByField.setCanFilter(true);

		ListGridField publishStatusField = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus"/*, "发布状态"*/);
		publishStatusField.setCanEdit(false);
		publishStatusField.setCanFilter(true);

		ListGridField bussinessStatusField = new ListGridField(
				"m_BussinessStatusEntity.status"/*, "业务状态"*/);
		bussinessStatusField.setCanEdit(false);
		bussinessStatusField.setCanFilter(true);

		ListGridField auditStatusField = new ListGridField("auditStatus"/*,
				"审批状态"*/);
		auditStatusField.setCanEdit(false);
		auditStatusField.setCanFilter(true);

		ListGridField approvalStageField = new ListGridField("approvalStage"/*,
				"审批阶段"*/);
		approvalStageField.setCanEdit(false);
		approvalStageField.setCanFilter(true);

		setFields(contractNumberField, priorityField,
				extendedValueTotalAmountField, makeByField, publishStatusField,
				bussinessStatusField, auditStatusField, approvalStageField);

	}

}
