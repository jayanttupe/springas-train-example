package com.skynet.spms.client.gui.customerService.commonui;

import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.OnStartUploaderHandler;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.MyUploadI18nConstants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class AttachmentCanvas extends VLayout {

	MyUploadI18nConstants i18n = GWT.create(MyUploadI18nConstants.class);

	private static final String uploadUrl = "spms/upload.do";

	private static final String moduleName = "account.applyManager.payApplyManager";

	private static final String dsName = "attachments_dataSource";

	private MultiUploader uploader = null;
	
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}




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

	public AttachmentCanvas() {
		setWidth("100%");
		setHeight("30%");
		final SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(150);
		final SectionStackSection section = new SectionStackSection("附件");
		section.setCanCollapse(false);
		section.setExpanded(true);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						attachmentGrid.setDataSource(dataSource);
						attachmentGrid.setWidth100();
						attachmentGrid.setHeight(224);
						attachmentGrid.setShowAllRecords(true);
						attachmentGrid.setCellHeight(22);
						attachmentGrid.fetchData();
						attachmentGrid.setShowRowNumbers(true);

						ListGridField titleField = new ListGridField("title",
								"标题");
						titleField.setCanEdit(false);
						ListGridField descriptionField = new ListGridField(
								"description", "描述");
						descriptionField.setCanEdit(false);
						ListGridField fillNameField = new ListGridField(
								"fileName", "文件名");
						fillNameField.setCanEdit(false);
						ListGridField modifyDateField = new ListGridField(
								"modifyDate", "最后修改时间");
						modifyDateField.setCanEdit(false);
						ListGridField operatorField = new ListGridField(
								"operator", "修改人");
						operatorField.setCanEdit(false);

						attachmentGrid.setFields(titleField, descriptionField,
								fillNameField, modifyDateField, operatorField);
						section.setItems(attachmentGrid);
						sectionStack.setSections(section);

						VLayout bar = new VLayout();
						uploader = new MultiUploader(FileInputType.BUTTON);
						uploader.setVisible(true);
						uploader.setI18Constants(i18n);
						uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
						uploader.addOnCancelUploadHandler(onCancelUploaderHandler);
						uploader.addOnStatusChangedHandler(onStatusChangedHandler);
						uploader.setFileInputPrefix("default");

						uploader.addOnStartUploadHandler(new OnStartUploaderHandler() {
							@Override
							public void onStart(IUploader uploader) {
								if (getUuid() == null) {
									SC.warn("请先保存基本信息");
									uploader.cancel();
								} else {
									uploader.avoidRepeatFiles(true);
									uploader.setServletPath(uploadUrl
											+ "?uuid="
											+ getUuid());
								}
							}
						});
						bar.addMember(uploader);
						addMember(sectionStack);
						addMember(bar);
					}
				});
	}
	
	public AttachmentCanvas(final String uuid) {
		this.setUuid(uuid);
		setWidth("100%");
		setHeight("30%");
		final SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(150);
		final SectionStackSection section = new SectionStackSection("附件");
		section.setCanCollapse(false);
		section.setExpanded(true);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				attachmentGrid.setDataSource(dataSource);
				attachmentGrid.setWidth100();
				attachmentGrid.setHeight(224);
				attachmentGrid.setShowAllRecords(true);
				attachmentGrid.setCellHeight(22);
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
				
				uploader.setServletPath(uploadUrl
						+ "?uuid=" + uuid);
				bar.addMember(uploader);
				addMember(sectionStack);
				addMember(bar);
				refresh();
			}
		});
	}

	
	

	private void refresh() {
		attachmentGrid.clearCriteria();
		Criteria conditions = new Criteria();
		StringBuilder sb=new StringBuilder();
		sb.append(getUuid());
		conditions.addCriteria("bussuuid", sb.toString());
		attachmentGrid.fetchData(conditions);
	}

}
