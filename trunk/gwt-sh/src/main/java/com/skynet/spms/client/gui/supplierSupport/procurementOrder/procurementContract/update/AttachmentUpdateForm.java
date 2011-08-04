package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.update;

import gwtupload.client.IUploader;
import gwtupload.client.IUploadStatus.Status;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.MyUploadI18nConstants;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
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
 * @author gqr
 * 
 */
public class AttachmentUpdateForm extends VLayout {

	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);
	private static final String uploadUrl = "spms/upload.do";
	public ProcurementContractModelLocator model = ProcurementContractModelLocator
			.getInstance();

	private final BaseListGrid attachmentGrid;

	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
				refresh();
			}
		}

	};

	private IUploader.OnCancelUploaderHandler onCancelUploaderHandler = new IUploader.OnCancelUploaderHandler() {
		public void onCancel(IUploader uploader) {
			if (uploader.getStatus() == Status.CANCELED) {
				refresh();
			}
		}
	};

	private IUploader.OnStatusChangedHandler onStatusChangedHandler = new IUploader.OnStatusChangedHandler() {
		public void onStatusChanged(IUploader uploader) {
			if (uploader.getStatus() == Status.DELETED) {
				refresh();
			}
		}
	};

	public AttachmentUpdateForm() {
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
				/* , "标题" */
				ListGridField titleField = new ListGridField("title");
				/* , "描述" */
				ListGridField descriptionField = new ListGridField(
						"description");
				/*
				 * , "文件名"
				 */
				ListGridField fillNameField = new ListGridField("fileName");
				ListGridField modifyDateField = new ListGridField("modifyDate");
				modifyDateField
						.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
				ListGridField operatorField = new ListGridField("operator");

				attachmentGrid.setFields(titleField, descriptionField,
						fillNameField, modifyDateField, operatorField);

			}
		};
		attachmentGrid.invalidateCache();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENT_ATTACHMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						attachmentGrid.setDataSource(dataSource);
						Criteria criteria=new Criteria();
						criteria.setAttribute("uuid", model.contractID);
						attachmentGrid.fetchData(criteria);
						attachmentGrid.drawGrid();
					}
				});
		attachmentGrid.setWidth100();
		attachmentGrid.setHeight(224);
		attachmentGrid.setShowAllRecords(true);
		attachmentGrid.setCellHeight(22);

		section.setItems(attachmentGrid);
		sectionStack.setSections(section);

		VLayout bar = new VLayout();
		model.defaultUploader.setI18Constants(i18n);
		model.defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		model.defaultUploader.addOnCancelUploadHandler(onCancelUploaderHandler);
		model.defaultUploader.addOnStatusChangedHandler(onStatusChangedHandler);
		model.defaultUploader.setFileInputPrefix("default");
		model.defaultUploader.avoidRepeatFiles(true);
		model.defaultUploader.setVisible(true);
		model.defaultUploader.setServletPath(uploadUrl + "?uuid="
				+ model.contractID);
		bar.addMember(model.defaultUploader);
		addMember(sectionStack);
		addMember(bar);

	}

	private void refresh() {
		Criteria conditions = new Criteria();
		conditions.addCriteria("uuid", model.contractID);
		attachmentGrid.fetchData(conditions);
	}

}
