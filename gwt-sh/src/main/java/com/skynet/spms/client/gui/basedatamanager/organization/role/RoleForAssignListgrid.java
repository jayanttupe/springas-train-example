package com.skynet.spms.client.gui.basedatamanager.organization.role;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;


public class RoleForAssignListgrid extends ListGrid {

	public RoleForAssignListgrid() {
		
	}
	
	public void drawRoleForAssignListgrid(boolean canFilter,boolean canEdit){
        setCanEdit(true);
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22); 
        setAutoSaveEdits(false);
        
        addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					selectRecords(getSelection(), false);
					selectRecord(selectedRecord);
				}	
			}
		});
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();
        
        //角色名
		ListGridField nameField = new ListGridField("roleName");
		nameField.setCanEdit(false);
		fieldList.add(nameField);
		//中文名称
		ListGridField zhNameField = new ListGridField("roleTitle_zh");
		zhNameField.setCanEdit(false);
		fieldList.add(zhNameField);
		//英文名称
		ListGridField enNameField = new ListGridField("roleTitle_en");
		enNameField.setCanEdit(false);
		fieldList.add(enNameField);
		//审批额度
		ListGridField quotaField = new ListGridField("approvalQuota");
		quotaField.setCanEdit(canEdit);
		fieldList.add(quotaField);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
	
		setShowFilterEditor(canFilter);
		for(ListGridField field : fields){
			field.setCanFilter(canFilter);
		}

        setFields(fields);
	}


}
