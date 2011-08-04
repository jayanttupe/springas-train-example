package com.skynet.spms.client.gui.customerService.salesService.salesContract.add;

import gwtupload.client.IUploader;
import gwtupload.client.IUploadStatus.Status;

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
public class AttachmentAddForm extends VLayout {

	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);

	public SaleContractModelLocator salemodel = SaleContractModelLocator.getInstance();

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

	public AttachmentAddForm() {
		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);
		SectionStackSection section = new SectionStackSection("合同附件");
		section.setCanCollapse(false);
		section.setExpanded(true);
		attachmentGrid=new BaseListGrid() {
			
			@Override
			public void drawGrid() {
				ListGridField titleField = new ListGridField("title"/*, "标题"*/);
				ListGridField descriptionField = new ListGridField("description"/*, "描述"*/);
				ListGridField fillNameField = new ListGridField("fileName"/*, "文件名"*/);
				ListGridField modifyDateField = new ListGridField("modifyDate"/*,
						"最后修改时间"*/);
				modifyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
				ListGridField operatorField = new ListGridField("operator"/*, "修改人"*/);
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
						attachmentGrid.drawGrid();
					}
				});
		
		section.setItems(attachmentGrid);
		sectionStack.setSections(section);

		VLayout bar = new VLayout();
		salemodel.defaultUploader.setVisible(false);
		salemodel.defaultUploader.setI18Constants(i18n);
		salemodel.defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		salemodel.defaultUploader.addOnCancelUploadHandler(onCancelUploaderHandler);
		salemodel.defaultUploader.addOnStatusChangedHandler(onStatusChangedHandler);
		salemodel.defaultUploader.setFileInputPrefix("default");
		salemodel.defaultUploader.avoidRepeatFiles(true);

		bar.addMember(salemodel.defaultUploader);
		addMember(sectionStack);
		addMember(bar);

	}

	private void refresh() {
		Criteria conditions = new Criteria();
		conditions.addCriteria("uuid", salemodel.contractID);
		attachmentGrid.fetchData(conditions);
	}

}
