package com.skynet.spms.client.gui.base;

import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.MyUploadI18nConstants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;


public class AttachmentListCanvas extends VLayout{

	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);
	private static final String uploadUrl = "spms/upload.do";
	private  MultiUploader uploader = null;
	private String uuid;
	public ListGrid attachmentGrid = new ListGrid();

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

	public AttachmentListCanvas(String modName, String dsName) {
		// 构建表格
		
		setWidth("80%");
		setHeight("30%");
		setLeft("10%");
		setTop("50%");
		final SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(150);
		final SectionStackSection section = new SectionStackSection("附件");
		section.setCanCollapse(false);
		section.setExpanded(true);

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modName, dsName, new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				// TODO Auto-generated method stub
				attachmentGrid.setDataSource(dataSource);
				attachmentGrid.setWidth100();
				
				attachmentGrid.setHeight(224);
				attachmentGrid.setShowAllRecords(true);
				attachmentGrid.setCellHeight(22);
				attachmentGrid.fetchData();
				attachmentGrid.setShowRowNumbers(true);

				ListGridField titleField = new ListGridField("title", "标题");
				titleField.setCanEdit(false);
				ListGridField descriptionField = new ListGridField("description", "描述");
				descriptionField.setCanEdit(false);
				ListGridField fillNameField = new ListGridField("fileName", "文件名");
				fillNameField.setCanEdit(false);
				ListGridField modifyDateField = new ListGridField("modifyDate",
						"最后修改时间");
				modifyDateField.setCanEdit(false);
				ListGridField operatorField = new ListGridField("operator", "修改人");
				operatorField.setCanEdit(false);

				attachmentGrid.setFields( titleField, descriptionField,
						fillNameField,modifyDateField,operatorField);
				section.setItems(attachmentGrid);
				sectionStack.setSections(section);
				
				VLayout bar = new VLayout();
				uploader =  new MultiUploader(FileInputType.BUTTON);
				uploader.setVisible(true);
				uploader.setI18Constants(i18n);
				uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
				uploader.addOnCancelUploadHandler(onCancelUploaderHandler);
				uploader.addOnStatusChangedHandler(onStatusChangedHandler);
				uploader.setFileInputPrefix("default");
				uploader.avoidRepeatFiles(true);	
				uuid = "temp"+Math.random();
				uploader.setServletPath(uploadUrl
						+ "?uuid=" + uuid);
				bar.addMember(uploader);
				addMember(sectionStack);
				addMember(bar);
			}
		});
	}

	public AttachmentListCanvas(String modName, String dsName, final String uUId) {
		// TODO Auto-generated constructor stub
		
		setWidth("80%");
		setHeight("30%");
		setLeft("10%");
		setTop("50%");
		uuid = uUId;
		final SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(150);
		final SectionStackSection section = new SectionStackSection("附件");
		section.setCanCollapse(false);
		section.setExpanded(true);

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modName, dsName, new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				// TODO Auto-generated method stub
				//dataSource.setCacheAllData(false);
				attachmentGrid.setDataSource(dataSource);
				attachmentGrid.setWidth100();
				
				attachmentGrid.setHeight(224);
				attachmentGrid.setShowAllRecords(true);
				attachmentGrid.setCellHeight(22);
				attachmentGrid.setShowRowNumbers(true);
				refresh();
				ListGridField titleField = new ListGridField("title", "标题");
				titleField.setCanEdit(false);
				ListGridField descriptionField = new ListGridField("description", "描述");
				descriptionField.setCanEdit(false);
				ListGridField fillNameField = new ListGridField("fileName", "文件名");
				fillNameField.setCanEdit(false);
				ListGridField modifyDateField = new ListGridField("modifyDate",
						"最后修改时间");
				modifyDateField.setCanEdit(false);
				ListGridField operatorField = new ListGridField("operator", "修改人");
				operatorField.setCanEdit(false);

				attachmentGrid.setFields( titleField, descriptionField,
						fillNameField,modifyDateField,operatorField);
				section.setItems(attachmentGrid);
				sectionStack.setSections(section);
				
				VLayout bar = new VLayout();
				uploader =  new MultiUploader(FileInputType.BUTTON);
				uploader.setVisible(true);
				uploader.setI18Constants(i18n);
				uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
				uploader.addOnCancelUploadHandler(onCancelUploaderHandler);
				uploader.addOnStatusChangedHandler(onStatusChangedHandler);
				uploader.setFileInputPrefix("default");
				uploader.avoidRepeatFiles(true);	
				
				uploader.setServletPath(uploadUrl
						+ "?uuid=" + uuid);

				bar.addMember(uploader);
				addMember(sectionStack);
				addMember(bar);
			}
		});
	}

	private void refresh() {
		attachmentGrid.clearCriteria();
		Criteria conditions = new Criteria();
		conditions.addCriteria("bussuuid", uuid);
		attachmentGrid.fetchData(conditions);
	}
	
}
