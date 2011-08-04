
package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;

/**
 * Description 用户添加至用户组窗体
 * @see  com.skynet.spms.client.gui.basedatamanager.organization.usergroup.UserAddToGroupWindow
 * @author 山云峰
 * @version 0.5
 */
import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.basedatamanager.organization.role.RoleForAssignListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


public class AddRoleToGroupWindow extends BaseWindow {

	private RoleForAssignListgrid originRoleListgrid;
	private RoleForAssignListgrid targetRoleListgrid;
	private HLayout buttonPanel;
	private List<Record> roleRecordList = new ArrayList<Record>();
	
	public AddRoleToGroupWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, AddRoleToGroupWindow.this, -1);
			}
		});
		
		final UserGroupListgrid userGroupListgrid = (UserGroupListgrid)listGrid;
		final Record groupRecord = userGroupListgrid.getSelectedRecord();
		DataSourceTool dataSourceTool = new DataSourceTool();
		String moduleName = "organization.userGroup";
		String dsName = "role_dataSource";
		
		//用户组名提示信息
		String content = ConstantsUtil.organizationConstants.groupNameLabel() + groupRecord.getAttribute("groupName");
		Label lblGroupName = new Label(content);
		lblGroupName.setMargin(8);
		lblGroupName.setHeight("5%");
		
		//添加到组的用户
		targetRoleListgrid = new RoleForAssignListgrid();
		targetRoleListgrid.setMargin(5);
		targetRoleListgrid.setHeight("44%");
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				targetRoleListgrid.setDataSource(dataSource);
				targetRoleListgrid.drawRoleForAssignListgrid(false,false);
			}
		});
		
		//原始所有角色
		originRoleListgrid = new RoleForAssignListgrid();
		originRoleListgrid.setMargin(5);
		originRoleListgrid.setHeight("45%");
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				originRoleListgrid.setDataSource(dataSource);
				originRoleListgrid.fetchData();
				originRoleListgrid.drawRoleForAssignListgrid(true,false);
			}
		});
		originRoleListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				originRoleListgrid.fetchData(c);
			}
		});
		
		
		//按钮条
		buttonPanel = new BtnsHLayout();
		//buttonPanel.setHeight("10%");
		buttonPanel.setWidth100();
		buttonPanel.setAlign(Alignment.CENTER);
		
		IButton addToButton = new IButton("添加");
		addToButton.setIcon("icons/up_16.png"); 
		
		addToButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {	
				
				for(Record record : originRoleListgrid.getSelection()){
					boolean exist = false;
					for(Record r : roleRecordList){
						if( r.getAttribute("id").equals(record.getAttribute("id"))){  
							exist = true;
							break;
						}
					}
					if(!exist){
						roleRecordList.add(record);
					}	
				}
				Record[] records = new Record[roleRecordList.size()];
				roleRecordList.toArray(records);
				targetRoleListgrid.setData(records);
				originRoleListgrid.selectRecords(originRoleListgrid.getSelection(), false);
			}
		});
		IButton deleteButton = new IButton("删除");
		deleteButton.setIcon("icons/remove.png"); 
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(Record record : targetRoleListgrid.getSelection()){
					for(Record r : roleRecordList){
						if(r.getAttribute("id").equals(record.getAttribute("id"))){
							roleRecordList.remove(r);
							break;
						}
					}	
				}
				Record[] records = new Record[roleRecordList.size()];
				roleRecordList.toArray(records);
				targetRoleListgrid.setData(records);
			}
		});
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				DynamicForm form = new DynamicForm();
				form.setDataSource(userGroupListgrid.getDataSource());
				String[] roleIds = new String[roleRecordList.size()];
				for(int i=0; i<roleRecordList.size(); i++){
					Record record  = roleRecordList.get(i);
					roleIds[i] = record.getAttribute("id");
				}
				form.setValue("roleIds", roleIds);
				form.setValue("id", groupRecord.getAttribute("id"));
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value.equals(true)) {
									ShowWindowTools.showCloseWindow(srcRect, AddRoleToGroupWindow.this, -1);
								}
							}
						});
						
						//SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
					}
				});
			}
		});

		buttonPanel.addMember(addToButton);
		buttonPanel.addMember(deleteButton);
		buttonPanel.addMember(saveButton);
		
		VLayout vLayout = new VLayout();
		//vLayout.setMargin(10);
		vLayout.addMember(lblGroupName); 
		vLayout.addMember(targetRoleListgrid);
		vLayout.addMember(buttonPanel);
		vLayout.addMember(originRoleListgrid);
		return vLayout;
	}
}
