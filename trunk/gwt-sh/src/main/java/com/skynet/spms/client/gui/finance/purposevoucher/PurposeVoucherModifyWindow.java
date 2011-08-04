package com.skynet.spms.client.gui.finance.purposevoucher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.FileUploadWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.RecordSummaryFunctionType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridSummaryField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class PurposeVoucherModifyWindow extends BaseWindow {

	public PurposeVoucherModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		// TODO Auto-generated constructor stub
	}
	
	Record[] fRecord ;
	ListGridRecord[] record;

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		final PurposeVoucherList pvListGrid = (PurposeVoucherList) listGrid;
		ButtonConstants buttonConstants = GWT.create(ButtonConstants.class);
		final ListGridRecord rdd = listGrid.getSelectedRecord();
		final Window thisWin = this;
		setShowMinimizeButton(false);
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
			}
		});
		Label lblTitle = new Label("<h2>记账凭证</h2>");
		lblTitle.setAlign(Alignment.CENTER);
		lblTitle.setLayoutAlign(Alignment.CENTER);
		lblTitle.setLeft("36%");
		lblTitle.setWidth("20%");
		lblTitle.setHeight("10%");

		List<FormItem> itemList = new ArrayList<FormItem>();
		final DynamicForm form = new DynamicForm();

		form.setDataSource(listGrid.getDataSource());
		form.setTop("6%");
		form.setHeight("80%");
		form.setWidth100();
		form.setPadding(6);
		form.setNumCols(6);

		HiddenItem id = new HiddenItem("id");
		id.setValue(rdd.getAttribute("id"));
		itemList.add(id);
		
		TextItem txtCertificateNumber = new TextItem("certificateNumber");
		txtCertificateNumber.setValue(rdd.getAttribute("certificateNumber"));
		txtCertificateNumber.setWidth(120);
		itemList.add(txtCertificateNumber);

		DateItem dtCertificateDate = new DateItem("certificateDate");
		dtCertificateDate.setAttribute("readOnly", true);
		dtCertificateDate.setDefaultValue(new Date());
		itemList.add(dtCertificateDate);

		final TextItem txtM_Attachment = new TextItem("attachmentNumber");
		txtM_Attachment.setDefaultValue(rdd.getAttribute("attachmentNumber"));
		txtM_Attachment.setTitle("附件");
		txtM_Attachment.setAttribute("readOnly", true);
		txtM_Attachment.setWidth(30);
		txtM_Attachment.setHint("张");
		final FileUploadWindow uploadWindow = new FileUploadWindow("附件上传", false, txtM_Attachment.getPageRect(), null, "",rdd.getAttribute("id"));
		txtM_Attachment.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				// TODO Auto-generated method stub
				ShowWindowTools.showWindow(txtM_Attachment.getPageRect(), uploadWindow, -1);
			}
		});
		uploadWindow.addCloseClickHandler(new CloseClickHandler() {
			
			@Override
			public void onCloseClick(CloseClientEvent event) {
				// TODO Auto-generated method stub
				ShowWindowTools.showCloseWindow(txtM_Attachment.getPageRect(), uploadWindow, -1);
				txtM_Attachment.setValue(""+uploadWindow.getAttachmentListCanvas().attachmentGrid.getTotalRows());
				
			}
		});
		itemList.add(txtM_Attachment);

		SelectItem selWord = (SelectItem)pvListGrid.getPurposeVoucherDataInfo()
						.getFieldInfoByName("m_VoucherWord").generFormField();
		selWord.setWidth(120);
		selWord.setEndRow(true);
		itemList.add(selWord);

		CanvasItem canvasClassificationItems = new CanvasItem(
				"items");
		canvasClassificationItems.setShowTitle(false);
		canvasClassificationItems.setColSpan("*");
		Canvas cvClassificationItems = new Canvas();
		final ListGrid listClassificationItems = new ListGrid();
		listClassificationItems.setWidth100();
		listClassificationItems.setHeight(200);
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		
		dataSourceTool.onCreateDataSource("account.certificateManager.payCertificateManager", "finance_classificationItem_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				// TODO Auto-generated method stub
				Criteria criteria = new Criteria();
				criteria.addCriteria("classificationItemId", rdd.getAttribute("id"));
				
				dataSource.fetchData(criteria,new DSCallback(){
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						// TODO Auto-generated method stub
						fRecord =response.getData();
						record = new ListGridRecord[5];

						for (int i = 0; i < record.length; i++) {
							record[i] = new ListGridRecord();
							record[i].setAttribute("id", "");
							record[i].setAttribute("lockVersion", "");
							record[i].setAttribute("summery", "");
							record[i].setAttribute("subject", "");
							record[i].setAttribute("currency", "");
							record[i].setAttribute("exchangeRate", "");
							record[i].setAttribute("unitPrice","");
							record[i].setAttribute("number", "");
							record[i].setAttribute("creditAmount", "");
							record[i].setAttribute("debitAmount","");
						}
						
						for (int i = 0; i < fRecord.length; i++) {	
							record[i] = new ListGridRecord();
							fRecord[i].setAttribute("isEditFlag", "0");
							record[i].setAttribute("lockVersion", ""+fRecord[i].getAttribute("lockVersion").toString());
							record[i].setAttribute("id",""+fRecord[i].getAttribute("id").toString());
							record[i].setAttribute("summery",new String(""+ fRecord[i].getAttribute("summery").toString()));
							record[i].setAttribute("subject", ""+fRecord[i].getAttribute("subject.subjectName").toString());
							if(fRecord[i].getAttribute("currency")!=null)
								record[i].setAttribute("currency", ""+fRecord[i].getAttribute("currency"));
							if(fRecord[i].getAttribute("exchangeRate")!=null)
							if(fRecord[i].getAttributeAsDouble("exchangeRate")!=0.0)
								record[i].setAttribute("exchangeRate",""+fRecord[i].getAttribute("exchangeRate"));
							record[i].setAttribute("unitPrice",""+fRecord[i].getAttribute("unitPrice"));
							record[i].setAttribute("itemNumber",""+ fRecord[i].getAttribute("itemNumber"));
							if(fRecord[i].getAttributeAsDouble("creditAmount")!=0.0)
								 record[i].setAttribute("creditAmount",""+fRecord[i].getAttribute("creditAmount"));
							if(fRecord[i].getAttributeAsDouble("debitAmount")!=0.0)
							record[i].setAttribute("debitAmount",""+fRecord[i].getAttribute("debitAmount"));
						}
						
						ListGridField id = new ListGridField("id");
						id.setHidden(true);
	

						ListGridField summeryField = new ListGridField("summery","摘要");
						summeryField.setWidth("20%");
						summeryField.setCanEdit(true);

						final ListGridField subjectsField = new ListGridField("subject", "科目名称");
						final Map subjects = new HashMap();
						DataSourceTool dataSourceTool = new DataSourceTool();
						dataSourceTool.onCreateDataSource("account.certificateManager", "finance_subjects_dataSource", new PostDataSourceInit() {
							@Override
							public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
						                  dataSource.fetchData(null, new DSCallback() {
											
											@Override
											public void execute(DSResponse response, Object rawData, DSRequest request) {
												// TODO Auto-generated method stub
											       Record[] recordSubjects = response.getData();
											       for(Record subRecord:recordSubjects){
											    	   subjects.put(subRecord.getAttribute("id"), subRecord.getAttribute("subjectName"));
											       }
											   	subjectsField.setValueMap(subjects);
												subjectsField.setWidth("20%");
												subjectsField.setCanEdit(true);
											}
										});
							}
						});

						ListGridField unitPriceField = new ListGridField("unitPrice","单价");
						unitPriceField.setType(ListGridFieldType.FLOAT);
						unitPriceField.setCanEdit(true);
						unitPriceField.setWidth("7%");
						unitPriceField.setCellFormatter(new CellFormatter() {
							public String format(Object value, ListGridRecord record,
									int rowNum, int colNum) {
								if (value == null)
									return null;
								try {
									NumberFormat nf = NumberFormat.getFormat("#,##0.00");
									return nf.format(((Number) value).doubleValue());
								} catch (Exception e) {
									return value.toString();
								}
							}
						});
						
						ListGridField numberField = new ListGridField("itemNumber","数量");
						numberField.setType(ListGridFieldType.INTEGER);
						numberField.setWidth("7%");
						numberField.setCanEdit(true);

						ListGridField currencyField = new ListGridField("currency","货币");
						currencyField.setWidth("5%");
						currencyField.setCanEdit(true);

						ListGridField exchangeRateField = new ListGridField("exchangeRate","汇率");
						exchangeRateField.setWidth("5%");
						exchangeRateField.setCanEdit(true);

						ListGridSummaryField debitField = new ListGridSummaryField(
								"debitAmount", "借方金额");
						debitField.setCanEdit(false);
						debitField.setWidth("15%");
						debitField
								.setRecordSummaryFunction(RecordSummaryFunctionType.MULTIPLIER);

						debitField.setSummaryFunction(SummaryFunctionType.SUM);
						debitField.setShowGridSummary(true);
						debitField.setShowGroupSummary(true);
						debitField.setCellFormatter(new CellFormatter() {
							public String format(Object value, ListGridRecord record,
									int rowNum, int colNum) {
								if (value == null)
									return null;
								try {
									NumberFormat nf = NumberFormat.getFormat("#,##0.00");
									return nf.format(((Number) value).doubleValue());
								} catch (Exception e) {
									return value.toString();
								}
							}
						});
						
						debitField.addRecordClickHandler(new RecordClickHandler() {
							
							@Override
							public void onRecordClick(RecordClickEvent event) {
								// TODO Auto-generated method stub
								float unitPrice  = 0.0F;
								int number =0;
								if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null)
									unitPrice = Float.parseFloat(listClassificationItems.getEditValue(event.getRecordNum(), 2).toString());
								if(listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null)
									number = Integer.parseInt(listClassificationItems.getEditValue(event.getRecordNum(),  3).toString());
							
								  if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null&&listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null){
				                    	listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", unitPrice*number);
										listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount","");
										
				                    }else if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null&&record[event.getRecordNum()].getAttributeAsInt("itemNumber")!=null){
				                    	listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", unitPrice* record[event.getRecordNum()].getAttributeAsInt("itemNumber"));
										listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount","");
				                    }else if(listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null&&record[event.getRecordNum()].getAttributeAsDouble("unitPrice")!=null){
				                    	listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", number*record[event.getRecordNum()].getAttributeAsDouble("unitPrice"));
										listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount","");
				                    }
								 
								 else{
			                    	if(record[event.getRecordNum()].getAttributeAsDouble("creditAmount")!=0.0)
			                    		listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", record[event.getRecordNum()].getAttributeAsDouble("creditAmount"));
			                    	else
			                    		listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", record[event.getRecordNum()].getAttributeAsDouble("debitAmount"));
			                    	listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount","");
			                    }
								
								
							}
						});

						ListGridSummaryField creditField = new ListGridSummaryField("creditAmount",
								"贷方金额");
						creditField.setCanEdit(false);
						creditField.setWidth("15%");
						creditField
								.setRecordSummaryFunction(RecordSummaryFunctionType.MULTIPLIER);
						creditField.setSummaryFunction(SummaryFunctionType.SUM);
						creditField.setShowGridSummary(true);
						creditField.setShowGroupSummary(true);
						creditField.setCellFormatter(new CellFormatter() {
							public String format(Object value, ListGridRecord record,
									int rowNum, int colNum) {
								if (value == null)
									return null;
								try {
									NumberFormat nf = NumberFormat.getFormat("#,##0.00");
									return nf.format(((Number) value).doubleValue());
								} catch (Exception e) {
									return value.toString();
								}
							}
						});
						creditField.addRecordClickHandler(new RecordClickHandler() {
							
							@Override
							public void onRecordClick(RecordClickEvent event) {
								// TODO Auto-generated method stub
								float unitPrice  = 0.0F;
								int number =0;
								if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null)
									unitPrice = Float.parseFloat(listClassificationItems.getEditValue(event.getRecordNum(), 2).toString());
								if(listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null)
									number = Integer.parseInt(listClassificationItems.getEditValue(event.getRecordNum(),  3).toString());
			                    if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null&&listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null){
			                    	listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", unitPrice*number);
									listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount","");
									
			                    }else if(listClassificationItems.getEditValue(event.getRecordNum(), 2)!=null&&record[event.getRecordNum()].getAttributeAsInt("itemNumber")!=null){
			                    	listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", unitPrice* record[event.getRecordNum()].getAttributeAsInt("itemNumber"));
									listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount","");
			                    }else if(listClassificationItems.getEditValue(event.getRecordNum(),  3)!=null&&record[event.getRecordNum()].getAttributeAsDouble("unitPrice")!=null){
			                    	listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", number*record[event.getRecordNum()].getAttributeAsDouble("unitPrice"));
									listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount","");
			                    }
			                    else{
			                    	if(record[event.getRecordNum()].getAttributeAsDouble("creditAmount")!=0.0)
			                    		listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", record[event.getRecordNum()].getAttributeAsDouble("creditAmount"));
			                    	else
			                    		listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", record[event.getRecordNum()].getAttributeAsDouble("debitAmount"));
									listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount","");
			                    }
							}
						});
						creditField.setWidth("20%");
						listClassificationItems.setFields(id,summeryField, subjectsField,
								unitPriceField, numberField, currencyField, exchangeRateField,debitField,creditField);
						listClassificationItems.setData(record);
						listClassificationItems.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
							
							@Override
							public void onRecordDoubleClick(RecordDoubleClickEvent event) {
								// TODO Auto-generated method stub
								if(listClassificationItems.getEditValue(event.getRecordNum(), "creditAmount")==null&&listClassificationItems.getEditValue(event.getRecordNum(), "debitAmount")==null)
								{
									if(record[event.getRecordNum()].getAttributeAsDouble("creditAmount")!=0.0)
									{
									listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount", record[event.getRecordNum()].getAttributeAsDouble("creditAmount"));
									listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount","");
									}else if(record[event.getRecordNum()].getAttributeAsDouble("debitAmount")!=0.0){
										listClassificationItems.setEditValue(event.getRecordNum(), "debitAmount", record[event.getRecordNum()].getAttributeAsDouble("debitAmount"));
										listClassificationItems.setEditValue(event.getRecordNum(), "creditAmount","");
									}
								}
							}
						});
				
						listClassificationItems.setShowGridSummary(true);
						listClassificationItems.setShowGroupSummary(true);

						listClassificationItems.setAutoSaveEdits(false);
						
					}
					
				});

			}
		});
		
		cvClassificationItems.addChild(listClassificationItems);
		canvasClassificationItems.setCanvas(cvClassificationItems);
		itemList.add(canvasClassificationItems);

		TextItem txtAccountUser = new TextItem("accountUser");
		txtAccountUser.setAttribute("readOnly", true);
		txtAccountUser.setWidth(120);
		itemList.add(txtAccountUser);

		TextItem txtCheckUser = new TextItem("checkUser");
		txtCheckUser.setAttribute("readOnly", true);
		txtCheckUser.setWidth(120);
		itemList.add(txtCheckUser);

		TextItem txtCreateCertificateUser = new TextItem(
				"createCertificateUser");
		txtCreateCertificateUser.setAttribute("readOnly", true);
		txtCreateCertificateUser.setDefaultValue(rdd.getAttribute("createCertificateUser"));
		txtCreateCertificateUser.setWidth(120);
		itemList.add(txtCreateCertificateUser);

		FormItem[] items = new FormItem[itemList.size()];
		itemList.toArray(items);
		form.setFields(items);

		IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				ListGridRecord[] fieldRecords = new ListGridRecord[5];
				for(int i=0;i<fieldRecords.length;i++)
					fieldRecords[i] = new ListGridRecord();
				int[] num = listClassificationItems.getAllEditRows();
				
				for(int i=0;i<num.length;i++)
				{
					if(num[i]<fRecord.length)
						fRecord[num[i]].setAttribute("isEditFlag", "1");
					
					ListGridRecord rd = (ListGridRecord)listClassificationItems.getEditedRecord(num[i]);
					fieldRecords[i].setAttribute("summery", rd.getAttribute("summery"));
					fieldRecords[i].setAttribute("unitPrice", rd.getAttributeAsDouble("unitPrice"));
					fieldRecords[i].setAttribute("itemNumber", rd.getAttributeAsInt("itemNumber"));
					fieldRecords[i].setAttribute("subject", rd.getAttribute("subject"));
					
					if(rd.getAttribute("debitAmount")!=null&&!"".equals(rd.getAttribute("debitAmount"))){
						fieldRecords[i].setAttribute("debitAmount", rd.getAttributeAsDouble("debitAmount"));
						fieldRecords[i].setAttribute("accountDirection",1);
					}
						
					else if(rd.getAttribute("creditAmount")!=null&&!"".equals(rd.getAttribute("creditAmount")))
					{
						fieldRecords[i].setAttribute("creditAmount", rd.getAttributeAsDouble("creditAmount"));
						fieldRecords[i].setAttribute("accountDirection",0);
					}
					if(rd.getAttribute("id")!=null)
						fieldRecords[i].setAttribute("id",rd.getAttribute("id"));
					if(rd.getAttribute("lockVersion")!=null)
						fieldRecords[i].setAttribute("lockVersion",rd.getAttribute("lockVersion"));
					if(rd.getAttribute("id")!=null)
						fieldRecords[i].setAttribute("id",rd.getAttribute("id"));
				}
				
				
				int j=num.length;
				
				for(int i=0;i<fRecord.length;i++){
					if(fRecord[i].getAttribute("isEditFlag").equals("0")){
						fieldRecords[j].setAttribute("summery", fRecord[i].getAttribute("summery"));
						fieldRecords[j].setAttribute("unitPrice",  fRecord[i].getAttributeAsDouble("unitPrice"));
						fieldRecords[j].setAttribute("itemNumber",  fRecord[i].getAttributeAsInt("itemNumber"));
						fieldRecords[j].setAttribute("debitAmount",  fRecord[i].getAttributeAsDouble("debitAmount"));
						fieldRecords[j].setAttribute("creditAmount",  fRecord[i].getAttributeAsDouble("creditAmount"));
						fieldRecords[j].setAttribute("subject",  fRecord[i].getAttribute("subject"));
						fieldRecords[j].setAttribute("id",fRecord[i].getAttribute("id"));
						fieldRecords[j].setAttribute("lockVersion",fRecord[i].getAttribute("lockVersion"));
						j++;
					}
					
				}
				ListGridRecord[] lastList = new ListGridRecord[j];
				for(int i=0;i<j;i++){
					lastList[i] = new ListGridRecord();
					lastList[i] = fieldRecords[i];
				}
				form.setValue("classificationItem", lastList);
				form.saveData();
				clear();
				ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
			}
		});
		// 将form放入TileGrid中，因为TileGrid可以有滚动条

		TileGrid tileGrid = new TileGrid();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		tileGrid.setTileWidth(150);
		tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(lblTitle);
		tileGrid.addChild(form);

		TileGrid buttonGrid = new TileGrid();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");
		buttonGrid.setTileWidth(100);
		buttonGrid.setTileHeight(100);
		buttonGrid.setBorder("0px solid #9C9C9C");
		saveButton.setTop("25%");
		buttonGrid.addChild(saveButton);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid);
		vLayout.addMember(buttonGrid);
		return vLayout;
	}

}
