package com.skynet.spms.client.gui.portal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.entity.BindModInfo;
import com.skynet.spms.client.entity.BindOutcomingInfo;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.event.PortalRefreshEvent;
import com.skynet.spms.client.event.PortalRefreshEventHandler;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.ApprovalContractWindow;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.skynet.spms.client.service.WfTaskService;
import com.skynet.spms.client.service.WfTaskServiceAsync;
import com.skynet.spms.client.vo.approval.ApprovalRecordVO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.Offline;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TaskListMember implements PortalMember {

	private Logger log = Logger.getLogger("task list msg Panel");

	private PortalConstants portalConst = GWT.create(PortalConstants.class);
	
	private WfTaskServiceAsync wfTaskService = GWT.create(WfTaskService.class);
	
	private ApprovalServiceAsync approvalService = GWT.create(ApprovalService.class);
	

	private ListGrid taskList;

	private DataSourceTool dsTool = new DataSourceTool();

	SearchForm form = new SearchForm();
	
	public TaskListMember(final HandlerManager eventBus) {

		taskList = new TaskGrid(eventBus);
		
		taskList.setCanEdit(false);
		taskList.setShowRecordComponentsByCell(true);
		taskList.setShowRecordComponents(true);
		taskList.setCanHover(true);
		eventBus.addHandler(PortalRefreshEvent.HANDLER,
				new PortalRefreshEventHandler() {

					@Override
					public void onRefreshPortal(PortalRefreshEvent event) {
						taskList.invalidateCache();
//						taskList.fetchData();
					}

				});

		taskList.setWidth100();
		taskList.setHeight("90%");

		form.setWidth(500);
		form.setHeight("10%");
		form.setNumCols(4);  
		form.setColWidths(100, 150, 100, 150); 
	
	}

	@Override
	public Canvas getCanvas() {

		dsTool.onCreateSimpleDataSource(
				"com.skynet.spms.jbpm.entity.WfTaskInfo", "wfTaskSimpInfo",
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,DataInfo dataInfo) {

						taskList.setDataSource(dataSource);

						taskList.setFields(getFieldArray());

						taskList.setGroupByField("status");

						String viewState = (String) Offline.get("taskList");
						if (viewState != null) {
							log.info("viewState:" + viewState);
							taskList.setViewState(viewState);
						}
											
						
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  
						valueMap.put("all", "所有");  
						valueMap.put("AOG", "<span style='color:#FF0000;'>AOG</span>");  
				        valueMap.put("expedite", "<span style='color:#ff8020;'>加急</span>");  
				        valueMap.put("critical","<span style='color:#ffc040;'>紧急</span>");  
				        valueMap.put("routine", "<span style='color:#200000;'>正常</span>");  
				        
				        
						SelectItem priority = getPriorityField(dataInfo,"priority");
						priority.setDefaultToFirstOption(true); 
						priority.setWidth(150);
						priority.setTitle(portalConst.priority());  
						priority.setValueMap(valueMap);  
						priority.setImageURLPrefix("flowchart/pri/");  
						priority.setImageURLSuffix(".png");  
				        
						LinkedHashMap<String, String> valueIcons = new LinkedHashMap<String, String>();  
						valueIcons.put("all", "all");  
				        valueIcons.put("AOG", "AOG");  
				        valueIcons.put("expedite", "expedite");  
				        valueIcons.put("critical", "critical");  
				        valueIcons.put("routine", "routine");  
				        priority.setValueIcons(valueIcons);
				        //业务类型
				        LinkedHashMap<String, String> flowTypeMap = new LinkedHashMap<String, String>();  
				        flowTypeMap.put("all", "所有");  
				        flowTypeMap.put("Contract","合同" );  
				        flowTypeMap.put("Order", "订单");  
				        flowTypeMap.put("Command","指令");  

						SelectItem flowType= getPriorityField(dataInfo,"flowType");
						flowType.setDefaultToFirstOption(true); 
						flowType.setTitle(portalConst.flowType());  
						flowType.setValueMap(flowTypeMap); 
						flowType.setWidth(150);
						flowType.setImageURLPrefix("flowchart/types/");  
						flowType.setImageURLSuffix(".png"); 

						LinkedHashMap<String, String> flowTypeIcons = new LinkedHashMap<String, String>();  
						flowTypeIcons.put("all", "all");  
				        flowTypeIcons.put("Contract", "Contract");  
				        flowTypeIcons.put("Order", "Order");  
				        flowTypeIcons.put("Command", "Command");  
				        flowType.setValueIcons(flowTypeIcons);
				        
						form.setFields(priority,flowType);
						taskList.fetchData();

					}

					private SelectItem getPriorityField(DataInfo dataInfo,final String index) {
						final SelectItem priority =  new SelectItem(index);
						//priority.setTitle(portalConst.flowType());
						priority.setMultiple(false);
						priority.setAllowEmptyValue(false);
						LinkedHashMap<String,String> valMap=dataInfo.getFieldInfoByName(index).getValueMap();
						valMap.put("all","所有" );
						priority.setValueMap(valMap);
						
						priority.addChangedHandler(new ChangedHandler() {

							@Override
							public void onChanged(ChangedEvent event) {
								String val=priority.getValueAsString();
								if(!val.equals("all")){
									Criteria criteria=new Criteria();
									criteria.setAttribute(index,val);
									taskList.fetchData(criteria);
								}else{
									taskList.fetchData();
								}
								
							}

						});
						return priority;
					}

				});

		VLayout layout = new VLayout();

		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		
		HLayout formlayout = new HLayout();
		formlayout.setWidth100();
		formlayout.addMember(form); 
		formlayout.addMember(wrapper); 
		
		layout.addMember(formlayout);
		layout.addMember(taskList);
		
		
		
		return layout;
	}

	private ListGridField[] getFieldArray() {
		List<ListGridField> list = new ArrayList<ListGridField>();
		
		//优先级
		ListGridField pri=new ListGridField("priority" ,"priority");
		
		pri.setAlign(Alignment.CENTER);  
		pri.setType(ListGridFieldType.ICON);  
		/*pri.setImageURLPrefix("flags/16/");  
		pri.setImageURLSuffix(".png");  */
		pri.setTitle(portalConst.priority());
		pri.setWidth(50);  
		list.add(pri);	
	
		//任务名称
		ListGridField taskName = new ListGridField("taskName");
		taskName.setTitle(portalConst.taskName());
		list.add(taskName);
		
		//任务类型
		ListGridField flowType=new ListGridField("flowType","flowType");
		flowType.setAlign(Alignment.CENTER);  
		flowType.setType(ListGridFieldType.ICON);  

		flowType.setTitle(portalConst.flowType());
	    flowType.setWidth(60);
		list.add(flowType);

		//流程状态
		ListGridField status = new ListGridField("statusDesc","status");
		status.setAlign(Alignment.CENTER);  
		status.setType(ListGridFieldType.ICON);  
		status.setWidth(60);
		status.setTitle(portalConst.status());
		list.add(status);
		
		//相关表单
		ListGridField refForm = new ListGridField("refForm",100);
		refForm.setTitle(portalConst.refForm());
		refForm.setAlign(Alignment.CENTER);
//		refForm.setAutoFitWidth(true);
//		refForm.setWidth(150);
		list.add(refForm);

		//迁出
		ListGridField taken = new ListGridField("taken");
		taken.setTitle(portalConst.taken());
		taken.setAlign(Alignment.CENTER);		
		list.add(taken);

		//创建时间
		ListGridField createDate=new ListGridField("createTime");
		createDate.setTitle(portalConst.createTime());
		createDate.setType(ListGridFieldType.DATE);
		list.add(createDate);
		
		//提交时间
		ListGridField commitDate=new ListGridField("commitTime");
		commitDate.setTitle(portalConst.commitTime());
		commitDate.setType(ListGridFieldType.DATE);
		list.add(commitDate);
		

		return list.toArray(new ListGridField[0]);
	}

	@Override
	public String getName() {
		return "TaskList";
	}

	private class TaskGrid extends ListGrid {

		private final HandlerManager eventBus;

		public TaskGrid(HandlerManager eventBus) {
			this.eventBus = eventBus;
		}

		@Override
		protected Canvas createRecordComponent(final ListGridRecord record,
				final Integer colNum) {

			String fieldName = getFieldName(colNum);

			String status = record.getAttributeAsString("status");
			
			String priority = record.getAttributeAsString("priority");
			
			String flowType = record.getAttributeAsString("flowType");

			if (fieldName.equals("refForm")) {
				return getRefForms(record, eventBus);
		    }else if (fieldName.equals("outcomingPanel")) {
				
				if (status.equals("completed") || status.equals("unknow")) {
					return getOutComingPanel(record);
				} else {
					return getBlankCell(20);
				}
			} else if (fieldName.equals("taken")) {

				if (status.equals("completed") || status.equals("unknow")
						|| status.equals("waitexec")) {

					return getBlankCell(40);

				} else {
					return getTakenButton(record);
				}
		    } else if (fieldName.equals("priority")) {  
				
				HLayout recordCanvas = new HLayout(3);  
                recordCanvas.setHeight(22);  
                recordCanvas.setAlign(Alignment.CENTER);  
                ImgButton editImg = new ImgButton();  
                editImg.setShowDown(false);  
                editImg.setShowRollOver(false);  
                editImg.setLayoutAlign(Alignment.CENTER);  
                //SC.say(priority);
                editImg.setSrc("flowchart/pri/" + record.getAttribute("priority") + ".png");  
                //鼠标经过提示语；
                if(priority.equals("routine")){
                    editImg.setPrompt("正常");  
                 }else if(priority.equals("critical")){
                	editImg.setPrompt("紧急"); 
                }else if(priority.equals("AOG")){
                	editImg.setPrompt("AOG"); 
                }else if(priority.equals("expedite")){
                	editImg.setPrompt("加急"); 
                }else{
                	return editImg;
                }
                /*if(priority.equals("critical")){
                	editImg.setPrompt("紧急"); 
                }else if(priority.equals("AOG")){
                	editImg.setPrompt("AOG"); 
                }else if(priority.equals("expedite")){
                	editImg.setPrompt("加急"); 
                }else{
                	return editImg;
                }*/
                  
                  
                editImg.setHeight(16);  
                editImg.setWidth(16);  
                
                recordCanvas.addMember(editImg);  
                return recordCanvas;   

            } else if (fieldName.equals("statusDesc")) {  
				
				HLayout statusesCanvas = new HLayout(3);  
				statusesCanvas.setHeight(22);  
				statusesCanvas.setAlign(Alignment.CENTER);  
                ImgButton statusImg = new ImgButton();  
                statusImg.setShowDown(false);  
                statusImg.setShowRollOver(false);  
                statusImg.setLayoutAlign(Alignment.CENTER);              
                statusImg.setSrc("flowchart/statuses/" + record.getAttribute("status") + ".png");  
                
                if (status.equals("completed")) {
                	statusImg.setPrompt("完成");
				}else if(status.equals("waitexec")){
					statusImg.setPrompt("等待提交");
            	}else if(status.equals("waittake")){
					statusImg.setPrompt("等待签出");
            	}else {
					return statusImg;
				}

                statusImg.setHeight(16);  
                statusImg.setWidth(16);  
                
                statusesCanvas.addMember(statusImg);  
                return statusesCanvas;   

            }else if (fieldName.equals("flowType")) {  
				
				HLayout typeCanvas = new HLayout(3);  
				typeCanvas.setHeight(22);  
				typeCanvas.setAlign(Alignment.CENTER);  
                ImgButton typeImg = new ImgButton();  
                typeImg.setShowDown(false);  
                typeImg.setShowRollOver(false);  
                typeImg.setLayoutAlign(Alignment.CENTER);  
                typeImg.setSrc("flowchart/types/" + record.getAttribute("flowType") + ".png");    

             if(flowType.equals("Contract")){
                typeImg.setPrompt("合同");  
             }else if(flowType.equals("Order")){
            	typeImg.setPrompt("订单"); 
             }else if(flowType.equals("Command")){
            	typeImg.setPrompt("指令"); 
             }else{
            	return typeImg;
             }
                
                typeImg.setHeight(16);  
                typeImg.setWidth(16);  
                typeCanvas.addMember(typeImg);  
                return typeCanvas;   

            }else {  
                return null;  
            }  

		}

	}

	@Override
	public String getDescription() {
		return portalConst.TaskListDesc();
	}

	private Canvas getTakenButton(final ListGridRecord record) {

		IButton button = new IButton();
		button.setWidth(40);
		button.setTitle(portalConst.takenButton());
		final String taskID = record.getAttributeAsString("taskID");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				wfTaskService.takeTask(taskID, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						taskList.invalidateCache();
						taskList.fetchData();
					}
				});

			}

		});
		return button;

	}

	private Canvas getOutComingPanel(final ListGridRecord record) {

		String outcoming = record.getAttributeAsString("outcoming");

		Label label = new Label();
		label.setContents(outcoming);
		label.setWidth(20);
		label.setHeight(16);
		return label;
	}


	private Canvas getBlankCell(int width) {
		Canvas canvas = new Canvas();
		canvas.setWidth(width);
		canvas.setHeight(16);
		return canvas;
	}

	private Canvas getRefForms(final ListGridRecord record,
			final HandlerManager eventBus) {
		String json = record.getAttributeAsString("formInfo");
		
		final String procInstID=record.getAttributeAsString("procInstID");
		log.info("form info:"+json);
		JsArray<BindModInfo> infoList = BindModInfo.asArrayOfModList(json);
		if (infoList.length() == 0) {
			return getBlankCell(100);
		}
		final String taskID = record.getAttributeAsString("taskID");

		HLayout layout=new HLayout(infoList.length());
		layout.setWidth(100);
		layout.setHeight(16);
		layout.setAlign(Alignment.CENTER);
		
		for (int i = 0; i < infoList.length(); i++) {
			final BindModInfo formInfo = infoList.get(i);

			IButton btn = new IButton();
			btn.setTitle(formInfo.getTitle());	
			btn.setIcon("flowchart/approval.png");  
			log.info(formInfo.getTitle());
			btn.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
//					PopupTaskFormEvent modEvent = new PopupTaskFormEvent(
//							formInfo);
//
//					modEvent.setTaskID(taskID);
					String status = record.getAttributeAsString("status");
					JsArray<BindOutcomingInfo> outArray=null;
//					SC.say(status);
					if (status.equals("waitexec")) {
						String outcoming = record.getAttributeAsString("outcoming");
						 outArray= BindOutcomingInfo.asArrayOfOutcoming(outcoming);
					}
				//	if(formInfo.getequals("approval")){
					String businessKey = formInfo.getBusinessKey();
					log.info(formInfo.getBusinessKey());
						String cxt="form:"+formInfo.getTitle()+"\n" +businessKey+"\n"+"outcoming:" +"\n taskid:"+taskID;
						log.info(cxt);
						final ApprovalContractWindow approvalWin = new ApprovalContractWindow(outArray,formInfo,"审批合同", false,null, null, eventBus,taskID,procInstID);
						log.info("审批窗口创建成功");
						approvalWin.show();
						
						approvalService.getApprovalById(formInfo.getBusinessKey(), new AsyncCallback<ApprovalRecordVO>() {
							
							@Override
							public void onSuccess(ApprovalRecordVO vo) {
								log.info("approval number:"+vo.getApprovalNumber()+"\t create date:"+vo.getCreateDate() +"\t priority:"+vo.getM_Priority()+"\t sheetNo:"+vo.getSheetNo()+"\t sheetType:"+vo.getSheetType());
								approvalWin.setApprovalValue(vo);
							}
							
							@Override
							public void onFailure(Throwable arg0) {
								SC.say("系统异常","获取审批数据失败!");
							}
						});
						
					}
				//}

			});

			layout.addMember(btn);

		}

		return layout;
	}
	
//	private void showWindow(BindModInfo modInfo, String taskID,JsArray<BindOutcomingInfo> outArray){
//		String cxt="form:"+modInfo.getModName()+"\n" +modInfo.getBusinessKey()+"\n"+"outcoming:" +"\n taskid:"+taskID;
//
//		
//	}
	

}


