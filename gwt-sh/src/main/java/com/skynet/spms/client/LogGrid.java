package com.skynet.spms.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.constants.PortalConstants;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LogGrid {
	
	private Logger log = Logger.getLogger("log menu");

	private ListGrid list=new DetailGrid();
	
	private PortalConstants portalConst=GWT.create(PortalConstants.class);

	public LogGrid(){
		
		DataSource dataSource=DataSource.get("generalTrackLog");
		list.setDataSource(dataSource);	
		list.setCanEdit(false);
        list.setCanHover(true);  
        list.setShowHover(true);  
        list.setShowHoverComponents(true); 
        list.setCanExpandRecords(true);  
        list.setExpansionMode(ExpansionMode.DETAIL_FIELD);  
        list.setDetailField("actionDescription");
        
        
		ListGridField[] fieldArray=new ListGridField[]{
				new ListGridField("operator"),
				new ListGridField("recordName"),
				getDetail(),
				new ListGridField("actionDate"),
				new ListGridField("m_GeneralOperateLogStatus"),
				
		};
		
		list.setShowFilterEditor(true);
		list.setFields(fieldArray);
		log.info("init log ds");
	}
	
	private ListGridField getDetail(){
		ListGridField field=new ListGridField("actionDescription");		
		return field;
	}
	public void popupWin(){
		final com.smartgwt.client.widgets.Window winModal = new com.smartgwt.client.widgets.Window();
		winModal.setWidth("800");
		winModal.setHeight("600");
		winModal.setTitle(portalConst.logWin());
		winModal.setShowMinimizeButton(false);
		winModal.setShowMaximizeButton(true);
		winModal.setIsModal(false);
        winModal.setShowShadow(true);  
		winModal.setShowModalMask(false);
		winModal.centerInPage();
		
		list.setWidth100();
		list.setHeight100();
		list.fetchData();
		winModal.addItem(list);
		log.info("popup win");
		winModal.show();
	}
	
	private class DetailGrid extends ListGrid{
		 @Override  
         protected Canvas getCellHoverComponent(Record record, Integer rowNum, Integer colNum) {  
             String fieldName= this.getField(colNum).getName();
             if(fieldName.equals("actionDescription")){
				 Label label=new Label();
				 label.setAutoFit(true);
				 label.setContents(record.getAttributeAsString("actionDescription"));
				 return label;
             }else{
            	 return null;
             }
         }  
		 
		 @Override  
         protected Canvas getExpansionComponent(ListGridRecord record) {  
             Canvas canvas = super.getExpansionComponent(record);  
             canvas.setMargin(5);  
             return canvas;  
         }  
		 
	}
}
