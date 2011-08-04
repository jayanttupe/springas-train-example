package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 附件
 * 
 * @author tqc
 * 
 */
public class PartPlanNeedAttachmentView extends ListGrid {

	public PartPlanNeedAttachmentView() {

		setWidth100();
		setShowAllRecords(true);
		setCanEdit(false);
		setAutoFetchData(true);
		drawGrid();

	}

	public void drawGrid() {
		// 项号
		ListGridField itemNumberField = new ListGridField("itemId");
		itemNumberField.setTitle("项号");

		ListGridField titleField = new ListGridField("title");
		// 描述
		ListGridField descriptionField = new ListGridField("description", "描述");
		// 文件名
		ListGridField fillNameField = new ListGridField("fileName", "文件名");
		// 最后修改时间
		ListGridField modifyDateField = new ListGridField("modifyDate",
				"最后修改时间");
		modifyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 修改人
		ListGridField operatorField = new ListGridField("operator", "修改人");

		setFields(itemNumberField, titleField, descriptionField, fillNameField,
				modifyDateField, operatorField);
	}

}
