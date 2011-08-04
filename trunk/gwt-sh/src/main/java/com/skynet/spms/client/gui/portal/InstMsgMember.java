package com.skynet.spms.client.gui.portal;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.entity.MsgEntity;
import com.skynet.spms.client.event.InstMsgEvent;
import com.skynet.spms.client.event.InstMsgEventHandler;
import com.skynet.spms.client.event.PortalLeaveEventHandler;
import com.skynet.spms.client.event.PortalLeaveSeleEvent;
import com.skynet.spms.client.event.PortalRefreshEvent;
import com.skynet.spms.client.event.PortalRefreshEventHandler;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.InstMsgSendService;
import com.skynet.spms.client.service.InstMsgSendServiceAsync;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class InstMsgMember implements PortalMember {
	
	private PortalConstants portalConst=GWT.create(PortalConstants.class);


	private final class SendMsgHandler implements SubmitValuesHandler {
		@Override
		public void onSubmitValues(SubmitValuesEvent event) {

			Map valMap = event.getValuesAsMap();

			String msg = (String) valMap.get("msg");
//			String isBroadStr = (String) valMap.get("broadcast");
			String to = (String) valMap.get("to");

			boolean isBroad = new Boolean(false);

			if (to == null && isBroad == false) {
				log.log(Level.WARNING, "sendTo cannot null,auto choice broad ");
				isBroad = true;
			}

			Record record = new Record();
			record.setAttribute("char", "me:" + msg);
			charList.getDataAsRecordList().add(record);

			msgService.doSendMsg(msg, isBroad, to, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub

				}

			});
		}
	}

	private Logger log = Logger.getLogger("inst msg Panel");

	private InstMsgSendServiceAsync msgService = GWT
			.create(InstMsgSendService.class);

	final ListGrid charList = new ListGrid();

	final Timer timer;
	
	final DynamicForm form=new DynamicForm();
	
	public InstMsgMember(HandlerManager eventBus) {
		ListGridField field = new ListGridField("char");
		charList.setWidth100();
		charList.setHeight("70%");
		charList.setFields(field);
		charList.setCanSort(false);

		RecordList recList = new RecordList();
		charList.setData(recList);

		timer = new Timer() {

			@Override
			public void run() {
				fillMessage();
			}

		};
		
		timer.cancel();

//		eventBus.addHandler(PortalRefreshEvent.HANDLER,
//				new PortalRefreshEventHandler() {
//
//					@Override
//					public void onRefreshPortal(PortalRefreshEvent event) {
//						timer.scheduleRepeating(1000);
//						log.info("start timer");
//					}
//
//				});

		eventBus.addHandler(PortalLeaveSeleEvent.HANDLER,
				new PortalLeaveEventHandler() {
					@Override
					public void onLeavePortal(
							PortalLeaveSeleEvent portalLeaveSeleEvent) {
						log.info("on leave portal");
						timer.cancel();
					}
				});
		
		generForm();
		
		form.setWidth100();
		form.setHeight("30%");

	}

	private void fillMessage() {
		msgService.getMessageList(new AsyncCallback<List<MsgEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				timer.cancel();

			}

			@Override
			public void onSuccess(List<MsgEntity> result) {
				for (MsgEntity msgEntity : result) {
					log.info("iterator msg:" + msgEntity.getMessage());
					Record record = new Record();
					record.setAttribute("char", msgEntity.getMessage());
					charList.getDataAsRecordList().add(record);
				}

			}

		});
	}

	@Override
	public Canvas getCanvas() {
		VLayout inputLayout = new VLayout();
		inputLayout.setWidth100();
		inputLayout.setHeight100();
		
		inputLayout.addMember(charList);	
		inputLayout.addMember(form);

		return inputLayout;
	}

	public void generForm() {

		
		TextItem msgItem = new TextItem();
		msgItem.setName("msg");
		msgItem.setTitle(portalConst.instMsgText());

		TextItem toItem = new TextItem();
		toItem.setName("to");
		toItem.setTitle(portalConst.sendToUser());

//		CheckboxItem bcItem = new CheckboxItem();
//		bcItem.setName("broadcast");
//		bcItem.setTitle("BroadCast");

		SubmitItem btnItem = new SubmitItem();
		btnItem.setTitle(portalConst.sendMsg());

		form.setFields(msgItem, toItem, btnItem);

		form.addSubmitValuesHandler(new SendMsgHandler());
	}

	@Override
	public String getName() {
		return "InstMsg";
	}

	@Override
	public String getDescription() {
		return portalConst.instMsg();
	}

}
