package com.skynet.spms.client.gui.basedatamanager.organization.user;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;


public class UserForGroupListgrid extends ListGrid {

	public UserForGroupListgrid() {
		
	}
	
	public void drawUserForGroupListgrid(boolean canFilter){
        setCanEdit(false);
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22); 
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();

		ListGridField nameField = new ListGridField("username");
		fieldList.add(nameField);

		ListGridField trueNameField = new ListGridField("realname");
		fieldList.add(trueNameField);
		
		ListGridField jobNumberField = new ListGridField("jobNumber");
		fieldList.add(jobNumberField);

		ListGridField enterpriseField = new ListGridField("m_BaseEnterpriseInformation.abbreviation");
		fieldList.add(enterpriseField);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
	
		setShowFilterEditor(canFilter);
		for(ListGridField field : fields){
			field.setCanFilter(canFilter);
		}

        setFields(fields);
	}


}
