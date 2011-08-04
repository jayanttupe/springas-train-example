package com.skynet.spms.client.gui.customerService.repairService.repairContract;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 送修合同列表
 * 
 * @author tqc
 * 
 */
public class RepairContractListGrid extends ListGrid {
	private I18n ui = new I18n();

	public RepairContractListGrid() {
		this.setWidth100();
		this.setMargin(5);
		this.setCellHeight(22);
		this.setShowFilterEditor(true);
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setAutoFetchData(true);
	}

	/**
	 * 绘制表格列
	 */
	public void drawGrid() {
		ListGridField contractNumberField = new ListGridField("contractNumber"/*,
				"合同编号"*/);

		ListGridField priorityField = new ListGridField("m_Priority"/*, "优先级"*/);

		ListGridField extendedValueTotalAmountField = new ListGridField(
				"extendedValueTotalAmount"/*, "总金额"*/);

		ListGridField makeByField = new ListGridField("makeBy"/*, "制定人"*/);

		ListGridField suppContractNumberField = new ListGridField(
				"suppContractNumber"/*, "供应商检修合同单号"*/);

		suppContractNumberField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if ("".equals(value) || null == value) {
					return "暂无";
				}
				return (String) value;
			}
		});

		ListGridField bussinessPublishStatusField = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus", ui.getB()
						.listTitlePublishStatus());
		bussinessPublishStatusField.setCanEdit(false);
		bussinessPublishStatusField.setCanFilter(true);

		ListGridField bussinessStatusField = new ListGridField(
				"m_BussinessStatusEntity.status", ui.getB()
						.listTitleBusStatus());
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
				extendedValueTotalAmountField, makeByField,
				suppContractNumberField, bussinessPublishStatusField,
				bussinessStatusField, auditStatusField, approvalStageField);
	}

}
