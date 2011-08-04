package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DocumentRecordsDetailWindow extends Window {
	
	public DocumentRecordsDetailWindow(String windowTitle, boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(860);
		setHeight(550);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setWidth(660);
		mainForm.setNumCols(6);
		mainForm.setColWidths(100,120,100,120,100,120);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// 单证记录ID
		final HiddenItem txtDocumentRecordsID = new HiddenItem("id");
		// 物流任务编号
		final TextItem logisticsTasksNumber = new TextItem("logisticsTaskNumber", "物流任务编号");
		logisticsTasksNumber.setWidth(120);
		logisticsTasksNumber.setDisabled(true);
		// 合同编号
		final TextItem contractNumber = new TextItem("contractNumber", "合同编号");
		contractNumber.setWidth(120);
		contractNumber.setDisabled(true);
		// 指令编号
		final TextItem orderNumber = new TextItem("orderNumber", "指令编号");
		orderNumber.setWidth(120);
		orderNumber.setDisabled(true);
		// 运代商
		final TextItem forwarder = new TextItem("forwarder", "运代商");
		forwarder.setWidth(120);
		// 运代商联系人
		final TextItem linkmanOfForwarder = new TextItem("linkmanOfForwarder", "运代商联系人");
		linkmanOfForwarder.setWidth(120);
		// 运代商联系方式
		final TextItem telephoneOfForwarder = new TextItem("telephoneOfForwarder", "运代商联系方式");
		telephoneOfForwarder.setWidth(120);
		// 报关代理商
		final TextItem customsAgent = new TextItem("customsAgent", "报关代理商");
		customsAgent.setWidth(120);
		// 报关代理商联系人
		final TextItem linkmanOfCustomsAgent = new TextItem("linkmanOfCustomsAgent", "报关代理商联系人");
		linkmanOfCustomsAgent.setWidth(120);
		// 报关代理商联系方式
		final TextItem telephoneOfCustomsAgent = new TextItem("telephoneOfCustomsAgent", "报关代理商联系方式");
		telephoneOfCustomsAgent.setWidth(120);

		// 运输单证
		final TransprotationDocumentListgrid transprotationDocumentListgrid = new TransprotationDocumentListgrid();
		transprotationDocumentListgrid.setWidth(660);
		transprotationDocumentListgrid.setHeight(160);
		transprotationDocumentListgrid.setMargin(5);
		transprotationDocumentListgrid.setAutoSaveEdits(false);
		transprotationDocumentListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取运输单证区域数据
		String transprotationModeName = "logisticsCustomsDeclaration.documentRecords";
		String transprotationDSName = "transprotationDocument_dataSource";
		DataSourceTool transprotationDST = new DataSourceTool();
		transprotationDST.onCreateDataSource(transprotationModeName, transprotationDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					transprotationDocumentListgrid.setDataSource(dataSource);
			
					transprotationDocumentListgrid.drawTransprotationDocumentListgrid();
					if (isView == true) {
						transprotationDocumentListgrid.setCanEdit(false);
					} else {
						transprotationDocumentListgrid.setCanEdit(true);
					}
					transprotationDocumentListgrid.setEditEvent(ListGridEditEvent.CLICK);
					if (txtDocumentRecordsID.getValue() != null)
					{
						transprotationDocumentListgrid.fetchData(new Criteria("documentID", "" + txtDocumentRecordsID.getValue().toString()));
					}
				}
			});
		
		// 报关报检单证
		final CIQDocumentListgrid cIQDocumentListgrid = new CIQDocumentListgrid();
		cIQDocumentListgrid.setWidth(660);
		cIQDocumentListgrid.setHeight(160);
		cIQDocumentListgrid.setMargin(5);
		cIQDocumentListgrid.setAutoSaveEdits(false);
		cIQDocumentListgrid.setCanEdit(true);
		cIQDocumentListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取报关报检单证数据
		String cIQDocumentModeName = "logisticsCustomsDeclaration.documentRecords";
		String cIQDocumentDsName = "cIQDocument_dataSource";
		DataSourceTool cIQDocumentDST = new DataSourceTool();
		cIQDocumentDST.onCreateDataSource(cIQDocumentModeName, cIQDocumentDsName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					cIQDocumentListgrid.setDataSource(dataSource);
					
					cIQDocumentListgrid.drawCIQDocumentListgrid();
					if (isView == true) {
						cIQDocumentListgrid.setCanEdit(false);
					} else {
						cIQDocumentListgrid.setCanEdit(true);
					}
					cIQDocumentListgrid.setEditEvent(ListGridEditEvent.CLICK);
					if (txtDocumentRecordsID.getValue() != null)
					{
						cIQDocumentListgrid.fetchData(new Criteria("documentID", "" + txtDocumentRecordsID.getValue().toString()));
					}
				}
			});

		// 新增运输单证按钮
		final IButton btnNewTransprotation = new IButton();
		btnNewTransprotation.setTitle("新增");
		btnNewTransprotation.setWidth(65);
		btnNewTransprotation.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				transprotationDocumentListgrid.startEditingNew();
			}
		});

		// 删除运输单证按钮
		final IButton btnDelTransprotation = new IButton();
		btnDelTransprotation.setTitle("删除");
		btnDelTransprotation.setWidth(65);
		btnDelTransprotation.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				transprotationDocumentListgrid.removeSelectedData();
				if (transprotationDocumentListgrid.getSelection().length == 1) {
					transprotationDocumentListgrid.removeSelectedData();
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}
		});

		// 新增报关报检单证按钮
		final IButton btnNewCIQ = new IButton();
		btnNewCIQ.setTitle("新增");
		btnNewCIQ.setWidth(65);
		btnNewCIQ.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cIQDocumentListgrid.startEditingNew();
			}
		});

		// 删除报关报检单证库按钮
		final IButton btnDelCIQ = new IButton();
		btnDelCIQ.setTitle("删除");
		btnDelCIQ.setWidth(65);
		btnDelCIQ.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cIQDocumentListgrid.removeSelectedData();
				if (cIQDocumentListgrid.getSelection().length == 1) {
					cIQDocumentListgrid.removeSelectedData();
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (logisticsTasksNumber.getAttribute("Value") == null
						|| logisticsTasksNumber.getAttribute("Value").equals("")) {
					SC.say("物流任务编号不能为空");
					return;
				}

				// 设置运输单证数据
				ListGridRecord[] transprotationRecord = new ListGridRecord[transprotationDocumentListgrid.getTotalRows()];
				for (int i = 0; i < transprotationDocumentListgrid.getTotalRows(); i++)
				{
					transprotationRecord[i] = (ListGridRecord)transprotationDocumentListgrid.getEditedRecord(i);
				}
				mainForm.setValue("transprotationDocumentList", transprotationRecord);
				
				// 设置报关报检单证数据
				ListGridRecord[] cIQDocumentRecord = new ListGridRecord[cIQDocumentListgrid.getTotalRows()];
				for (int i = 0; i < cIQDocumentListgrid.getTotalRows(); i++)
				{
					cIQDocumentRecord[i] = (ListGridRecord)cIQDocumentListgrid.getEditedRecord(i);
				}
				mainForm.setValue("cIQDocumentList", cIQDocumentRecord);
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");
						String documentRecordsID = response.getData()[0].getAttribute("id");

						// 刷新运输单证数据
						Criteria transprotationCriteria = new Criteria();
						transprotationCriteria.addCriteria("temp", String.valueOf(Math.random()));
						transprotationCriteria.addCriteria("documentID", "" + documentRecordsID);
						DataSource transprotationDS = transprotationDocumentListgrid.getDataSource();
						transprotationDocumentListgrid.setDataSource(transprotationDS);
						transprotationDocumentListgrid.drawTransprotationDocumentListgrid();
						transprotationDocumentListgrid.fetchData(transprotationCriteria);
						
						// 刷新报关报检单证数据
						Criteria cIQDocumentCriteria = new Criteria();
						cIQDocumentCriteria.addCriteria("temp", String.valueOf(Math.random()));
						cIQDocumentCriteria.addCriteria("documentID", "" + documentRecordsID);
						DataSource cIQDocumentDS = cIQDocumentListgrid.getDataSource();
						cIQDocumentListgrid.setDataSource(cIQDocumentDS);
						cIQDocumentListgrid.drawCIQDocumentListgrid();
						cIQDocumentListgrid.fetchData(cIQDocumentCriteria);
					}
				});
			}
		});

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		mainForm.setFields(txtDocumentRecordsID
							,logisticsTasksNumber
							,contractNumber
							,orderNumber
							,forwarder
							,linkmanOfForwarder
							,telephoneOfForwarder
							,customsAgent
							,linkmanOfCustomsAgent
							,telephoneOfCustomsAgent
						);
		 	
		
		final HLayout CIQBtnLayout = new HLayout();
		CIQBtnLayout.setMargin(5);
		CIQBtnLayout.setMembersMargin(5);
		CIQBtnLayout.addMember(btnNewCIQ);
		CIQBtnLayout.addMember(btnDelCIQ);

		final BtnsHLayout transprotationBtnLayout = new BtnsHLayout();
		transprotationBtnLayout.addMember(btnNewTransprotation);
		transprotationBtnLayout.addMember(btnDelTransprotation);

		final BtnsHLayout bototmBtnLayout = new BtnsHLayout(); 
		bototmBtnLayout.addMember(btnSave);
		bototmBtnLayout.addMember(btnReturn);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(transprotationBtnLayout);
		layout.addMember(transprotationDocumentListgrid);
		layout.addMember(CIQBtnLayout);
		layout.addMember(cIQDocumentListgrid);
		layout.addMember(bototmBtnLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			transprotationBtnLayout.setVisible(false);
			CIQBtnLayout.setVisible(false);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
		}

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}