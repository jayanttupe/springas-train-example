package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;


import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class UserGroupListgrid extends ListGrid {

	public UserGroupListgrid(){
		
	}
	public void drawUserGroupListgrid() {
		setCanEdit(false);
		setCanSelectText(true);
        setShowFilterEditor(true);
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setHoverWidth(200);   
        setHoverHeight(20); 
        setCellHeight(22); 
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();
        
        //用户组名称 
        ListGridField groupNameField = new ListGridField("groupName");
        groupNameField.setCanFilter(true);
        fieldList.add(groupNameField);
        //用户组描述
        ListGridField descriptionField = new ListGridField("description");
        descriptionField.setCanFilter(true);
        fieldList.add(descriptionField);
        
        ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
        
    }  
}
