package com.skynet.spms.client.gui.customerService.salesService.salesContract.view;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.MyUploadI18nConstants;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 附件
 * 
 * @author songb
 * 
 */
public class AttachmentViewForm extends VLayout {

	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);
	public SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();

	private final BaseListGrid attachmentGrid;

	public AttachmentViewForm() {
		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);
		SectionStackSection section = new SectionStackSection("合同附件");
		section.setCanCollapse(false);
		section.setExpanded(true);
		attachmentGrid = new BaseListGrid() {
			@Override
			public void drawGrid() {
				ListGridField titleField = new ListGridField("title");
				ListGridField descriptionField = new ListGridField(
						"description");
				ListGridField fillNameField = new ListGridField("fileName");
				ListGridField modifyDateField = new ListGridField("modifyDate");
				modifyDateField
						.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
				modifyDateField.setCanEdit(false);
				ListGridField operatorField = new ListGridField("operator");
				operatorField.setCanEdit(false);

				attachmentGrid.setFields(titleField, descriptionField,
						fillNameField, modifyDateField, operatorField);
			}
		};
		attachmentGrid.invalidateCache();
		attachmentGrid.setWidth100();
		attachmentGrid.setHeight(224);
		attachmentGrid.setShowAllRecords(true);
		attachmentGrid.setCellHeight(22);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		/** 附件数据源 **/
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESATTACHMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						attachmentGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("uuid", model.contractID);
						attachmentGrid.fetchData(criteria);
						attachmentGrid.drawGrid();
					}
				});
		section.setItems(attachmentGrid);
		sectionStack.setSections(section);
		addMember(sectionStack);
	}
}
