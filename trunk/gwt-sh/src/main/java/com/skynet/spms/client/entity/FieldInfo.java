package com.skynet.spms.client.entity;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBinaryField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceLinkField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.grid.ListGridField;

public class FieldInfo implements IsSerializable {

	private String typeName;

	private String title;

	private int length;

	private boolean required;

	private boolean canFilter;

	private boolean canEdit = false;

	private boolean isCol = false;

	private String fieldType;

	// private String childFieldName;
	private String childClsName;

	private String[][] valueArray;

	private boolean isHidden;

	public FieldInfo cloneInfo() {
		FieldInfo newInfo = new FieldInfo();
		newInfo.isHidden = isHidden;
		newInfo.canEdit = canEdit;
		newInfo.canFilter = canFilter;
		newInfo.childClsName = childClsName;
		// newInfo.childFieldName=childFieldName;
		newInfo.fieldType = fieldType;
		newInfo.isCol = isCol;
		newInfo.length = length;
		newInfo.required = required;
		newInfo.title = title;
		newInfo.typeName = typeName;
		newInfo.valueArray = valueArray;
		return newInfo;
	}

	public String toString() {
		return "name:" + title + " type:" + typeName + " canEdit:" + canEdit;
	}

	public FormItem generFormField() {
		FormItem field = null;
		if(!canEdit){
			field=new StaticTextItem(typeName,title);
			return field;
		}
		
		switch (DsFieldType.valueOf(fieldType)) {
		case TEXT:
			field = new TextItem(typeName, title);
			((TextItem) field).setLength(length);
			break;
		case TEXTAREA:
			field = new TextAreaItem(typeName, title);
			((TextAreaItem) field).setLength(length);
			break;
		case BOOLEAN:
			field = new BooleanItem(typeName, title);
			break;
		case DATE:
			field = new DateItem(typeName, title);
			break;
		case DATETIME:
			field = new DateTimeItem(typeName, title);
			break;
		case TIME:
			field=new TimeItem(typeName,title);
			break;
		case INTEGER:
			field = new IntegerItem(typeName, title);
			break;
		case ENUM:
			field = new SelectItem(typeName, title);
			field.setValueMap(getValueMap());
			break;
		case PASSWORD:
			field = new PasswordItem(typeName, title);
			break;
		case FLOAT:
			field = new FloatItem(typeName, title);
			break;
		case SEQUENCE:
			field = new HiddenItem(typeName);
			break;
		case LINK:
			field = new LinkItem(title);
			field.setName(typeName);
			break;
		case BINARY:
			field=new UploadItem(title);
			field.setName(typeName);
			break;
		default:
			field = new TextItem(typeName, title);
		}
		field.setRequired(required);

		return field;
	}

	public ListGridField generGridField() {

		ListGridField field = new ListGridField();
		field.setTitle(title);
		ListGridFieldType gridType = ListGridFieldType.TEXT;
		if (!"ENUM".equals(fieldType)) {
			gridType = ListGridFieldType.valueOf(fieldType);
		}
		field.setType(gridType);
		field.setWidth(length);
		field.setCanEdit(canEdit);
		field.setCanFilter(canFilter);
		field.setName(typeName);
		field.setRequired(required);
		field.setHidden(isHidden);
		
		
		if ("ENUM".equals(fieldType)) {
			field.setValueMap(getValueMap());
			field.setMultiple(true);
		}
		return field;
	}

	public DataSourceField generField() {
		DataSourceField field = null;
		switch (DsFieldType.valueOf(fieldType)) {
		case TEXT:
			field = new DataSourceTextField(typeName, title, length, required);
			break;
		case BOOLEAN:
			field = new DataSourceBooleanField(typeName, title, length,
					required);
			break;
		case DATE:
			field = new DataSourceDateField(typeName, title, length, required);
			break;
		case DATETIME:
			field = new DataSourceDateTimeField(typeName, title, length,
					required);
			break;
		case INTEGER:
			field = new DataSourceIntegerField(typeName, title, length,
					required);
			break;
		case ENUM:
			field = new DataSourceEnumField(typeName, title, length, required);

			field.setValueMap(getValueMap());
			break;
		case PASSWORD:
			field = new DataSourcePasswordField(typeName, title, length,
					required);
			break;
		case FLOAT:
			field = new DataSourceFloatField(typeName, title, length, required);
			break;
		case SEQUENCE:
			field = new DataSourceTextField(typeName, title, length, required);
			field.setPrimaryKey(true);
			break;
		case LINK:
			field = new DataSourceLinkField(typeName, title, length, required);
			break;
		case BINARY:
			field = new DataSourceBinaryField(typeName, title, length, required);
			break;
		default:
			field = new DataSourceTextField(typeName, title, length, required);
		}
		field.setCanFilter(canFilter);
		field.setCanEdit(canEdit);
		field.setHidden(isHidden);
		return field;
	}

	public LinkedHashMap<String, String> getValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < valueArray[0].length; i++) {
			valueMap.put(valueArray[0][i], valueArray[1][i]);
		}
		return valueMap;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setCanFilter(boolean canFilter) {
		this.canFilter = canFilter;
	}

	public void setCanEdit(boolean canEdit) {

		this.canEdit = canEdit;
	}

	public void setType(DsFieldType type) {
		this.fieldType = type.name();
	}

	public void setValueMap(String[][] array) {
		this.valueArray = array;
	}
	
//	public String[][] getValueArray(){
//		return valueArray;
//	}

	public void addPrefix(String fieldName) {
		typeName = fieldName + "." + typeName;
	}

	public String getName() {
		return typeName;
	}

	public void setCollProp(String childCls) {
		childClsName = childCls;
		isCol = true;
	}

	public void setHidden(boolean hidden) {
		this.isHidden = hidden;
	}

	public boolean isNonDisplay() {
		return isHidden || FieldType.SEQUENCE.name().equals(fieldType);
	}

	public DsFieldType getType() {
		return DsFieldType.valueOf(fieldType);
	}

}
