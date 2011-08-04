package com.skynet.spms.client.gui.portal;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.constants.PortalMessage;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.UserMessageService;
import com.skynet.spms.client.service.UserMessageServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.OperatorId;  
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Hilite;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordCollapseEvent;
import com.smartgwt.client.widgets.grid.events.RecordCollapseHandler;
import com.smartgwt.client.widgets.grid.events.RecordExpandEvent;
import com.smartgwt.client.widgets.grid.events.RecordExpandHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserMsgMember implements PortalMember{
	
	private Logger log = Logger.getLogger("UserMsgMember Panel");


	private PortalConstants portalConst=GWT.create(PortalConstants.class);
	
	private UserMessageServiceAsync msgService=GWT.create(UserMessageService.class);

	private ListGrid msgGrid;
	
	public UserMsgMember(){
		
		msgGrid=new ListGrid();
		msgGrid.setCanEdit(false);
		msgGrid.setShowRecordComponentsByCell(true);
		msgGrid.setShowRecordComponents(true);
		
		DataSource ds=DataSource.get("userMessage");
		
		msgGrid.setDataSource(ds);		
		msgGrid.setCanExpandRecords(true);  
		msgGrid.setExpansionMode(ExpansionMode.DETAIL_FIELD);  
		msgGrid.setDetailField("m_MessageEntity.message");
		
		ListGridField createField=new ListGridField("createBy",portalConst.fromUser());
		ListGridField dateField=new ListGridField("createDate",portalConst.sendDate());
		ListGridField readedField=new ListGridField("readed","      ");
		readedField.setCellFormatter(new CellFormatter(){

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				boolean sign=record.getAttributeAsBoolean("readed");
				if(sign){
					return portalConst.readed();
				}else{
					return portalConst.unread();
				}
			}
			
		});
		msgGrid.setFields(readedField,createField,dateField);
		msgGrid.addRecordCollapseHandler(new RecordCollapseHandler(){

			@Override
			public void onRecordCollapse(RecordCollapseEvent event) {
//				msgGrid.showField("readSign");
			}
			
		});
		msgGrid.addRecordExpandHandler(new RecordExpandHandler(){

			@Override
			public void onRecordExpand(final RecordExpandEvent event) {
				Record rec=event.getRecord();
				if(rec.getAttributeAsBoolean("readed")){
					return;
				}
				String msgID=rec.getAttribute("id");
//				msgGrid.hideField("readSign");
				
				
				msgService.setMsgReaded(msgID, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						Record rec=event.getRecord();
						rec.setAttribute("readed", true);
						msgGrid.invalidateRecordComponents();						
					}
					
				});
			}
			
			
		});
	}
	
	private Canvas getAddWin(){
		IButton btn=new IButton();
		btn.setTitle(portalConst.sendMessage());
		btn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Rectangle rect=new Rectangle(50,50,400,600);
				LeaveMsgWindow win=new LeaveMsgWindow("send msg", false, rect, null);
				ShowWindowTools.showWondow(rect,win,-1);
			}
			
		});
		
		return btn;
	}
		
	@Override
	public Canvas getCanvas() {
		VLayout layout=new VLayout();
		layout.addMember(getAddWin());
		
		msgGrid.fetchData();
		layout.addMember(msgGrid);
		
		return layout;
		
	}

	@Override
	public String getName() {
		
		return "Comment";
	}


	@Override
	public String getDescription() {
		return portalConst.UserMsgDesc();
	}
	
//	private class MsgGrid extends ListGrid{
//		
//		public MsgGrid(){
//			super();
//		}
//		
//		@Override
//		protected Canvas createRecordComponent(final ListGridRecord record,
//				final Integer colNum) {
//			String fieldName = getFieldName(colNum);
//			log.info("msg grid field Name:"+fieldName);
//			if("readSign".equals(fieldName)){
//				 
//				 boolean sign=record.getAttributeAsBoolean("readed");
//				 
//				 Label label=new Label();
//				 label.setAutoHeight();
//				 label.setValign(VerticalAlignment.TOP);
//
//				 if(sign){
//					 label.setContents(portalConst.readed());
//				 }else{
//					 label.setContents(portalConst.unread());
//				 }
//				 				 
//				 return label;
//			}else{
//				return null;
//			}
//			
//		}
//	}

}
