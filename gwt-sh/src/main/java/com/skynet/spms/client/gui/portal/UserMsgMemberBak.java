package com.skynet.spms.client.gui.portal;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.constants.PortalMessage;
import com.skynet.spms.client.entity.UserMessage;
import com.skynet.spms.client.event.PortalLeaveEventHandler;
import com.skynet.spms.client.event.PortalLeaveSeleEvent;
import com.skynet.spms.client.event.PortalRefreshEvent;
import com.skynet.spms.client.event.PortalRefreshEventHandler;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.UserMessageService;
import com.skynet.spms.client.service.UserMessageServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.StringUtil;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserMsgMemberBak implements PortalMember{
	private static final String LAB_ID = "nonlabel";

	private Logger log = Logger.getLogger("UserMsgMember Panel");
	
	private VLayout layout=new VLayout();
	
	private PortalConstants portalConst=GWT.create(PortalConstants.class);
	
	private PortalMessage portalMsg=GWT.create(PortalMessage.class);
	
	private UserMessageServiceAsync msgService=GWT.create(UserMessageService.class);
		
	
	public UserMsgMemberBak(HandlerManager eventBus){
				
		layout.setHeight100();
		layout.setWidth100();
		layout.setOverflow(Overflow.AUTO);
		
		log.info("init user msg layout");	
		
		eventBus.addHandler(PortalRefreshEvent.HANDLER, 
				new PortalRefreshEventHandler(){

					@Override
					public void onRefreshPortal(PortalRefreshEvent event) {
						getNewMsg();
					}			
			
		});
		
		eventBus.addHandler(PortalLeaveSeleEvent.HANDLER, new PortalLeaveEventHandler(){
			@Override
			public void onLeavePortal(
					PortalLeaveSeleEvent portalLeaveSeleEvent) {
				log.info("on leave portal");
			}
		});
		
		
	}

	private void getNewMsg() {
		msgService.getNewMessageList(new AsyncCallback<List<UserMessage>>(){

			@Override
			public void onFailure(Throwable caught) {
				log.warning(caught.getMessage());	
				
			}

			@Override
			public void onSuccess(List<UserMessage> result) {
				for(UserMessage msg:result){
					
					log.info("iterator msg:"+msg.getMsgID()+" layout:"+layout.getID());

					
					Canvas canvas=getWidget(msg);					
					
					canvas.setPadding(3);
					canvas.setMargin(1);
					
					layout.addMember(canvas,0);
					
				}		
				
			}
			
		});
	}

	@Override
	public Canvas getCanvas() {

		layout.clear();
		
		log.info("create layout canvas ");	
		msgService.getMessageList(new AsyncCallback<List<UserMessage>>(){

			@Override
			public void onFailure(Throwable caught) {
				log.warning(caught.getMessage());	
			}

			@Override
			public void onSuccess(List<UserMessage> result) {
				
				if(result.isEmpty()){
					Label label = getNonLabel();
					layout.addMember(label);
					
					return;
				}
				
				layout.addMember(getAddWin());
				
				for(UserMessage msg:result){
										
					Canvas canvas=getWidget(msg);
					
					log.info("iterator msg:"+canvas.getID()+" layout:"+layout.getID());
					
					canvas.setPadding(3);
					canvas.setMargin(1);
					
					layout.addMember(canvas);
					
				}		
			}

			
			
		});
		
		return layout;
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
	
	private  Canvas getWidget(final UserMessage msg) {
		final Window canvas=new Window();
		
		canvas.setShowCloseButton(true);
		canvas.addCloseClickHandler(new CloseClickHandler(){

				@Override
				public void onCloseClick(CloseClientEvent event) {
					msgService.setMsgReaded(msg.getMsgID(), new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {
							log.warning(caught.getMessage());									
						}

						@Override
						public void onSuccess(Void result) {
							
							log.info("sign read msg");
							Canvas member=layout.getMember(msg.getMsgID());
							layout.removeMember(member);
							member.destroy();
							
							if(layout.getMembers().length==0){
								Label label = getNonLabel();
								layout.addMember(label);
							}
							
						}
						
					});
				}
		    	
		    });
		
		DateTimeFormat format=DateTimeFormat.getFormat("MM-dd:HH:mm:ss");
		String timeStr=format.format(msg.getTime());
		
		String fullTitle=portalMsg.msgTime(timeStr,msg.getFrom());
		canvas.setTitle(fullTitle);
		canvas.setID(msg.getMsgID());
		canvas.setShowEdges(true);
		canvas.setMembersMargin(5);
		canvas.setWidth100();
	    canvas.setAutoSize(true);	    	    
		
//		HTMLFlow  ctx=new HTMLFlow();
//		ctx.setEvalScriptBlocks(false);
//		ctx.setID("msg"+msg.getMsgID());
//		ctx.setWidth100();
//		ctx.setHeight100();

//		String context=msg.getContext().trim();
//		context=StringUtil.asHTML(context);
//		if(context.length()<40){
//			ctx.setContents(context+".<br/>");
//		}else{
//			ctx.setContents(context.substring(0,40)+portalConst.moreCtx());
//		}
	    Label ctx=new Label();
	    ctx.setWidth("99%");
	    ctx.setHeight("99%");
	    ctx.setContents(msg.getContext().trim());
		canvas.addItem(ctx);
		
		canvas.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
								
				HTMLFlow label=new HTMLFlow();
				label.setEvalScriptBlocks(false);
				label.setContents(StringUtil.asHTML(msg.getContext()));
				
				canvas.removeItem(canvas.getItems()[0]);
				canvas.addItem(label);
				
			}
			
		});
		canvas.setHeight("50px");
		return canvas;
	}
	
	private Label getNonLabel() {
		Label label=new Label();
		label.setID(LAB_ID);
		label.setContents(portalConst.noMsgProm());
		return label;
	}

	@Override
	public String getName() {
		
		return "Comment";
	}


	@Override
	public String getDescription() {
		return portalConst.UserMsgDesc();
	}

}
