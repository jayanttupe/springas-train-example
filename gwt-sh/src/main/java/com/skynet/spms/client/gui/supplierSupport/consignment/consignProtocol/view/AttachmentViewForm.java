package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.view;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.base.i18n.MyUploadI18nConstants;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
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
 * @author fl
 * 
 */
public class AttachmentViewForm extends VLayout {
	private static I18n ui = new I18n();
	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);
	public ConsignProtocolModel model = ConsignProtocolModel.getInstance();

	private final BaseListGrid attachmentGrid;


	public AttachmentViewForm() {
		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);
		//附件信息
		SectionStackSection section = new SectionStackSection(ui.getM().attachment_title());
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
				ListGridField operatorField = new ListGridField("operator");
				attachmentGrid.setFields(titleField, descriptionField,
						fillNameField, modifyDateField, operatorField);
			}
		};
		attachmentGrid.setWidth100();
		attachmentGrid.setHeight(224);
		attachmentGrid.setShowAllRecords(true);
		attachmentGrid.setCellHeight(22);
		attachmentGrid.invalidateCache();
		DataSourceTool dataSourceTool = new DataSourceTool();
		// 附件数据源
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNATTACH_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						attachmentGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("uuid", model.protocolId);
						attachmentGrid.fetchData(criteria);
						attachmentGrid.drawGrid();
					}
				});
		

		section.setItems(attachmentGrid);
		sectionStack.setSections(section);
		addMember(sectionStack);
	}
}
