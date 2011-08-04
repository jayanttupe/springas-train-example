package com.skynet.spms.client.gui.portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.events.ErrorEvent;
import com.smartgwt.client.data.events.HandleErrorHandler;
import com.smartgwt.client.rpc.HandleErrorCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FormItemValueFormatter;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class MockMember implements PortalMember {

	private PortalConstants portalConst = GWT.create(PortalConstants.class);

	private Logger log = Logger.getLogger("mock member");

	private ListGrid taskList=new ListGrid();

	private ListGrid itemList=new ItemGrid();

	public MockMember() {
				

		taskList.setCanEdit(true);

		taskList.setWidth100();
		taskList.setHeight("50%");

		DataSource dataSource = DataSource.getDataSource("mockTestPage");

		dataSource.addHandleErrorHandler(new MyErrorHandler("mockTestPage",taskList));

		taskList.setDataSource(dataSource);
		ListGridField[] fields = getListFields();
		
		
		taskList.setFields(fields);
		taskList.addSelectionChangedHandler(new SelectionChangedHandler() {
			public void onSelectionChanged(SelectionEvent event) {
				Record record = event.getRecord();
				Record[] recArray=record.getAttributeAsRecordArray("itemList");
//				for(Record rec:recArray){
//					rec.setAttribute("mockEntity.id", record.getAttributeAsString("id"));
//				}
				itemList.setData(recArray);
			}
		});

	

		DataSource itemDs = DataSource.getDataSource("subitem");
		itemDs.addHandleErrorHandler(new MyErrorHandler("subItem",itemList));

		itemList.setHeight("40%");
//		itemList = new ListGrid();
		itemList.setCanEdit(true);
		itemList.setAutoSaveEdits(false);
		itemList.setDataSource(itemDs);
		itemList.setShowRecordComponents(true);
		itemList.setShowRecordComponentsByCell(true);
		
		itemList.setFields(new ListGridField("name"), new ListGridField("date"),new ListGridField("m_dele"));
		
		itemList.addCellSavedHandler(new CellSavedHandler(){

			@Override
			public void onCellSaved(CellSavedEvent event) {
				
				Record record=event.getRecord();
			
				RecordList list=taskList.getSelectedRecord().getAttributeAsRecordList("itemList");
				
				int rowNum=event.getRowNum();
				
				if(rowNum>=list.getLength()){
					list.add(record);
				}else{
					list.set(rowNum, record);
				}
				
				itemList.setData(list);
				
				
			}
			
		});
		
		
		

		// RPCManager.startQueue();
		//
		// RPCManager.sendQueue();

		// form.setDataSource(dataSource);
		// }catch(Exception e){
		// log.warning(e.getMessage());
		// }
	}

	private ListGridField[] getListFields() {
		
		ListGridField name=new ListGridField("entityName"); 
		ListGridField sku=new ListGridField("SKU");
		ListGridField desc=new ListGridField("description");
		ListGridField cate=new ListGridField("category");
		ListGridField unit=new ListGridField("item.units");
		ListGridField unitCost=new ListGridField("item.unitCost");
		
		CellFormatter format=new CellFormatter(){

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if(value==null){
					value=0;
				}
				NumberFormat nf = NumberFormat.getFormat("#.##");  
				
				return nf.format(((Number)value).floatValue());
			}
			
		};
		unitCost.setCellFormatter(format);
		
		ListGridField[] fields = new ListGridField[] {
			name,sku,desc,cate,unit,unitCost

		};
		return fields;
	}


	@Override
	public Canvas getCanvas() {

		taskList.fetchData();
		VLayout layout = new VLayout();
		layout.setOverflow(Overflow.SCROLL);
		
		layout.addMember(taskList);
		ToolStrip toolbar=new ToolStrip();


		
		ToolStripButton btn = new ToolStripButton("addUnitCost");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final ListGridRecord record=taskList.getSelectedRecord();

				taskList.setUpdateOperation("addUnitCost");
				taskList.updateData(record, new DSCallback(){

					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						
						Record record=response.getData()[0];
						float val=record.getAttributeAsFloat("item.unitCost");
//						SC.say("val:"+val);
						taskList.getSelectedRecord().setAttribute("item.unitCost", val);
//						record.setAttribute("item.unitCost",val);
					}
					
				});
				taskList.setUpdateOperation(null);
			}

		});
		toolbar.addButton(btn);
		ToolStripButton saveAll=new ToolStripButton("saveall");
		saveAll.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {

				
				Record record=taskList.getSelectedRecord();
				
				RecordList items=new RecordList();
				
				for(int rowNum=0;rowNum<itemList.getDataAsRecordList().getLength();rowNum++){					
					Record itemRec=itemList.getEditedRecord(rowNum);
					items.add(itemRec);
				}				
				record.setAttribute("itemList", items);
				
				taskList.setUpdateOperation("saveAllWithSubItem");
				taskList.updateData(record);
				taskList.setUpdateOperation(null);
			}
			
		});		
		toolbar.addButton(saveAll);
		
		ToolStripButton addItem=new ToolStripButton("addItem");
		addItem.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Record record=taskList.getSelectedRecord();
				Record itemRecord=new Record();
				itemRecord.setAttribute("mockEntity.id", record.getAttributeAsString("id"));
				
				itemList.startEditingNew(itemRecord);
				
			}
			
		});
		toolbar.addButton(addItem);
		ToolStripButton add=new ToolStripButton("addMock");
		add.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				final Window win=new Window();
				win.setWidth100();
				win.setHeight100();
				
				DynamicForm form = new DynamicForm();
				
				form.setHeight("10%");
				DataSource dataSource = DataSource.getDataSource("mockTestPage");

				form.setDataSource(dataSource);

				form.setFields(getFormItems());
				
				form.addSubmitValuesHandler(new SubmitValuesHandler(){

					@Override
					public void onSubmitValues(SubmitValuesEvent event) {
						win.destroy();
					}
					
				});
				win.addMember(form);
				
				win.show();
				
			}
			
		});
		toolbar.addButton(add);
		layout.addMember(toolbar);
		layout.addMember(itemList);

		return layout;

	}
	
	private FormItem[] getFormItems() {
		


		TextItem name = new TextItem("entityName");

		SelectItem sele = new SelectItem("SKU");
		
		TextAreaItem desc=new TextAreaItem("description");
		
		SelectItem seleSub = new SelectItem("item.units");
		
		SelectItem category=new SelectItem("category");
		
		TextItem unitCost=new TextItem("item.unitCost");
		
		SubmitItem submit = new SubmitItem("save");

		
		return new FormItem[]{name,sele,desc,seleSub,category,unitCost,submit};
	}

	
	private class MyErrorHandler implements HandleErrorHandler {

		private String name;
		
		private ListGrid list;
		public MyErrorHandler(String name,ListGrid list){
			this.name=name;
			this.list=list;
		}
		@Override
		public void onHandleError(ErrorEvent event) {

			int status=event.getResponse().getStatus();
			if(status>-100 && status<0){

							
				Record rec=new Record();
				rec.setAttribute("id", event.getResponse().getAttribute("id"));
				int rowNum=list.getDataAsRecordList().indexOf(rec);
			
				
				Map errors=event.getResponse().getErrors();
				for(Object key:errors.keySet()){
					String fieldName=(String)key;
					log.info("fieldName:"+fieldName);
					Map err=(Map) errors.get(key);
					String msg=(String) err.get("errorMessage");
					list.setFieldError(rowNum, fieldName, msg);
				}
				return;
			}
			SC.say("fail from "+name+":"
					+ event.getResponse().getStatus()
					+" "
					+ event.getResponse().getAttributeAsString("error"));
			
			
		}

	}

	@Override
	public String getName() {
		return "Mock";
	}

	@Override
	public String getDescription() {
		return "Mock";
	}

	private class ItemGrid extends ListGrid {

		@Override
		protected Canvas createRecordComponent(final ListGridRecord record,
				final Integer colNum) {

			String fieldName = getFieldName(colNum);
			
			if ("m_dele".equals(fieldName)) {
				
				IButton btn=new IButton("Del");
				btn.addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {

						itemList.removeData(record);
						
						RecordList list=taskList.getSelectedRecord().getAttributeAsRecordList("itemList");
						boolean sign=list.remove(record);
						if(!sign){
							log.info("not found match deleted record");
						}
						itemList.setData(list);

					}
					
				});
				return btn;

			}
			else {
				return null;
			}

		}

	}

}
