package com.skynet.spms.client.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author 曹宏炜
 * 元数据类，用于构造本系统需要用到的元数据结构
 */
public class DataInfo implements IsSerializable{
	
//	private transient Logger /log = Logger.getLogger("Data Info");

	private Map<String,FieldInfo> fieldMap=new LinkedHashMap<String,FieldInfo>();
	
	private String dsDefineName;
		
	private String clsName;

	public List<FieldInfo> getFieldList() {
		return new ArrayList<FieldInfo>(fieldMap.values());
	}
	
	public FieldInfo getFieldInfoByName(String name){
		return fieldMap.get(name);
	}
	
	public List<DataSourceField> getDsFieldList(){
		List<DataSourceField> dsList=new ArrayList<DataSourceField>();
		for(FieldInfo field:fieldMap.values()){
			DataSourceField dsField=field.generField();
//			log.info("ds field type:"+dsField.getName()+" desc:"+dsField.getTitle()+
//					" hidden:"+dsField.getHidden()+" type:"+dsField.getType());
			dsList.add(dsField);			
		}
		return dsList;
	}

	public void addFieldInfo(FieldInfo fieldInfo){
		fieldMap.put(fieldInfo.getName(),fieldInfo);
	}

	public List<ListGridField> getGridFieldList(){
		List<ListGridField> gridList=new ArrayList<ListGridField>();
		for(FieldInfo field:fieldMap.values()){
			
			gridList.add(field.generGridField());
		}
		return gridList;
	}
	
	public Map<String,ListGridField> getGridFieldMap(){
		Map<String,ListGridField> gridList=new LinkedHashMap<String,ListGridField>();
		for(FieldInfo field:fieldMap.values()){
			
			gridList.put(field.getName(),field.generGridField());
		}
		return gridList;
	}
	
	public List<FormItem> getFormFieldList(){
		List<FormItem> gridList=new ArrayList<FormItem>();
		for(FieldInfo field:fieldMap.values()){			
			gridList.add(field.generFormField());
		}
		return gridList;
	}

	public String getDsDefineName() {
		return dsDefineName;
	}

	public void setDsDefineName(String dsDefineName) {
		this.dsDefineName = dsDefineName;
	}


	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	
	
}
