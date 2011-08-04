package com.skynet.spms.client.gui.basedatamanager.organization.department;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo.EnterpriseInfoTree;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class DepartmentInfoTreeGrid extends TreeGrid {
	
	private HLayout gridLayout;
	
	private EnterpriseInfoTree enterpriseInfoTree;
		
	public HLayout getGridLayout() {
		return gridLayout;
	}
	public EnterpriseInfoTree getEnterpriseInfoTree() {
		return enterpriseInfoTree;
	}
	

	public DepartmentInfoTreeGrid(){
		
		enterpriseInfoTree = new EnterpriseInfoTree("organization.enterprise.COMACSC","COMACSC_dataSource");
		gridLayout = new HLayout();
		gridLayout.setHeight100();
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise.department", "department_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				DataSourceField idField = dataSource.getField("id");
				idField.setPrimaryKey(true);
				idField.setRequired(true);
				DataSourceField parentIdField = dataSource.getField("parentId");
				parentIdField.setRequired(true);
				parentIdField.setForeignKey("id");
				parentIdField.setRootValue((String)null);
				//dataSource.fetchData();

				setDataSource(dataSource);
				drawDepartmentInfoTreeGrid();
			}
		});
		
		gridLayout.addMember(enterpriseInfoTree);
		gridLayout.addMember(this);
		
		enterpriseInfoTree.addSelectionChangedHandler(new SelectionChangedHandler() {
			@Override
			public void onSelectionChanged(SelectionEvent event) {
					Record selectedRecord = enterpriseInfoTree.getSelectedRecord();
					if(selectedRecord != null){
						String enterpriseId = selectedRecord.getAttribute("id");
						Criteria criteria = new Criteria("enterpriseId", enterpriseId);
						fetchData(criteria);
					}else{
						Criteria criteria = new Criteria("enterpriseId", "clear");
						fetchData(criteria);
					}	
			}
		});
		
		enterpriseInfoTree.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				Record selectedRecord = event.getRecord();
				enterpriseInfoTree.selectRecords(enterpriseInfoTree.getSelection(), false);
				enterpriseInfoTree.selectRecord(selectedRecord);
				
			}
		});
	}
	
	public void drawDepartmentInfoTreeGrid() {  

		//setLoadDataOnDemand(true); 
		//setAutoFetchData(false);
		setShowConnectors(true);
		setHeight100();
        setWidth100();  
        setNodeIcon("icons/16/person.png");  
        setFolderIcon("icons/16/person.png");  
        setShowOpenIcons(false);  
        setShowDropIcons(false);  
        setClosedIconSuffix(""); 
        setCanEdit(false);
        
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setSelectionType(SelectionStyle.SIMPLE); 
        
        List<TreeGridField> list = new ArrayList<TreeGridField>();

        //部门名称
        TreeGridField tgfDepartment = new TreeGridField("department",160); 
        tgfDepartment.setFrozen(true);
        list.add(tgfDepartment);
        //描述
        TreeGridField tgfRemark = new TreeGridField("remark");  
        list.add(tgfRemark);
        
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