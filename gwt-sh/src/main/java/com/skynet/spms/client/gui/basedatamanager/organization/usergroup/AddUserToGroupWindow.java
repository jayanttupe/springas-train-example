/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-18   山云峰	   
 * Date        Author      Changes
 * 2011-3-24   黄贇 
 */
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
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserForGroupListgrid;
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


public class AddUserToGroupWindow extends BaseWindow {

	private UserForGroupListgrid originUserListgrid;
	private UserForGroupListgrid targetUserListgrid;
	private HLayout buttonPanel;
	private List<Record> userRecordList = new ArrayList<Record>();
	
	public AddUserToGroupWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, AddUserToGroupWindow.this, -1);
			}
		});
		
		final UserGroupListgrid userGroupListgrid = (UserGroupListgrid)listGrid;
		final Record groupRecord = userGroupListgrid.getSelectedRecord();
		DataSourceTool dataSourceTool = new DataSourceTool();
		String moduleName = "organization.userinfo";
		String dsName = "user_dataSource";
		
		//用户组名提示信息
		String content = ConstantsUtil.organizationConstants.groupNameLabel() + groupRecord.getAttribute("groupName");
		Label lblGroupName = new Label(content);
		lblGroupName.setMargin(8);
		lblGroupName.setHeight("5%");
		
		//添加到组的用户
		targetUserListgrid = new UserForGroupListgrid();
		targetUserListgrid.setMargin(5);
		targetUserListgrid.setHeight("44%");
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				targetUserListgrid.setDataSource(dataSource);
				targetUserListgrid.drawUserForGroupListgrid(false);
				Criteria criteria = new Criteria("groupId", groupRecord.getAttribute("id"));
				targetUserListgrid.fetchData(criteria, new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						for(Record record : targetUserListgrid.getRecords()){
							userRecordList.add(record);
						}
					}
				});
			}
		});
		
		//原始所有用户
		originUserListgrid = new UserForGroupListgrid();
		originUserListgrid.setMargin(5);
		originUserListgrid.setHeight("45%");
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				originUserListgrid.setDataSource(dataSource);
				originUserListgrid.fetchData();
				originUserListgrid.drawUserForGroupListgrid(true);
			}
		});
		originUserListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				originUserListgrid.fetchData(c);
			}
		});
		
		
		
		buttonPanel = new BtnsHLayout();
		//buttonPanel.setHeight("10%");
		buttonPanel.setWidth100();
		buttonPanel.setAlign(Alignment.CENTER);
		
		IButton addToButton = new IButton("添加");
		addToButton.setIcon("icons/up_16.png"); 
		addToButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {	
				for(Record record : originUserListgrid.getSelection()){
					boolean exist = false;
					for(Record r : userRecordList){
						if( r.getAttribute("id").equals(record.getAttribute("id"))){  
							exist = true;
							break;
						}
					}
					if(!exist){
						userRecordList.add(record);
					}	
				}
				Record[] records = new Record[userRecordList.size()];
				userRecordList.toArray(records);
				targetUserListgrid.setData(records);
				originUserListgrid.selectRecords(originUserListgrid.getSelection(), false);
			}
		});
		IButton deleteButton = new IButton("删除");
		deleteButton.setIcon("icons/remove.png"); 
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(Record record : targetUserListgrid.getSelection()){
					for(Record r : userRecordList){
						if(r.getAttribute("id").equals(record.getAttribute("id"))){
							userRecordList.remove(r);
							break;
						}
					}	
				}
				Record[] records = new Record[userRecordList.size()];
				userRecordList.toArray(records);
				targetUserListgrid.setData(records);
			}
		});
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				DynamicForm form = new DynamicForm();
				form.setDataSource(userGroupListgrid.getDataSource());
				String[] userIds = new String[userRecordList.size()];
				for(int i=0; i<userRecordList.size(); i++){
					Record record  = userRecordList.get(i);
					userIds[i] = record.getAttribute("id");
				}
				form.setValue("userIds", userIds);
				form.setValue("id", groupRecord.getAttribute("id"));
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value.equals(true)) {
									ShowWindowTools.showCloseWindow(srcRect, AddUserToGroupWindow.this, -1);
								}
							}
						});
						
						//Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
					}
				});
			}
		});

		buttonPanel.addMember(addToButton);
		buttonPanel.addMember(deleteButton);
		buttonPanel.addMember(saveButton);
		
		VLayout vLayout = new VLayout();
		vLayout.addMember(lblGroupName); 
		vLayout.addMember(targetUserListgrid);
		vLayout.addMember(buttonPanel);
		vLayout.addMember(originUserListgrid);
		return vLayout;
	}
}
