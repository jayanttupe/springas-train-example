package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class EnterpriseInfoTree extends TreeGrid {
	
	private DataInfo dataInfo;

	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}

	public EnterpriseInfoTree(String moduleName,String dsName) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName,dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				DataSourceField idField = dataSource.getField("id");
				idField.setPrimaryKey(true);
				idField.setRequired(true);
				DataSourceField parentIdField = dataSource.getField("parentId");
				parentIdField.setRequired(true);
				parentIdField.setForeignKey("id");
				parentIdField.setRootValue((String)null);
				
				setDataSource(dataSource);
				setDataInfo(dataInfo);
				fetchData();
				drawEnterpriseInfoTreeGrid();
			}
		});
	}

	public void drawEnterpriseInfoTreeGrid() {

		//setLoadDataOnDemand(true); 
		setShowConnectors(true);
		setAutoFetchData(false);
		setHeight100();
        setWidth(200);  
        setNodeIcon("silk/person.png");  
        setFolderIcon("silk/person.png");  
        setShowOpenIcons(false);  
        setShowDropIcons(false);  
        setClosedIconSuffix("");
        setCanEdit(false);
        
        setSelectionAppearance(SelectionAppearance.CHECKBOX); 
        setSelectionType(SelectionStyle.SINGLE); 
        
        List<TreeGridField> list = new ArrayList<TreeGridField>();

        TreeGridField tgfAbbreviation = new TreeGridField("abbreviation",160);  
        //tgfAbbreviation.setFrozen(true);
        list.add(tgfAbbreviation);
	    
	    TreeGridField[] fields = new TreeGridField[list.size()];
	    list.toArray(fields);
        this.setFields(fields);
        
        addDataArrivedHandler(new DataArrivedHandler() {
			@Override
			public void onDataArrived(DataArrivedEvent event) {
				getData().openAll();
				
			}
		});
     }  
}  