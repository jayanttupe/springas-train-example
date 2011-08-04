package com.skynet.spms.datasource.entity;

import java.lang.reflect.Method;
import java.util.Date;

import com.skynet.spms.client.entity.DsFieldType;
import com.skynet.spms.client.entity.FieldInfo;
import com.skynet.spms.datasource.annotation.DataDictBind;
import com.skynet.spms.datasource.annotation.DsField;
import com.skynet.spms.datasource.annotation.FilterField;


public class FieldMetaInfo  {

	private final FieldInfo fieldInfo;

//	private int order;

	private String relBinField;

	private Class<? extends Enum> enumCls;

	public Class<? extends Enum> getEnumCls() {
		return enumCls;
	}

	private String ddEnum;

	public String getDataDictEnum() {
		return ddEnum;
	}

	public FieldMetaInfo cloneNew() {
		FieldMetaInfo newInfo = new FieldMetaInfo(fieldInfo.cloneInfo());

		newInfo.ddEnum = ddEnum;
		newInfo.enumCls = enumCls;
//		newInfo.order = order;
		newInfo.relBinField = relBinField;

		return newInfo;
	}

	private FieldMetaInfo(FieldInfo info) {
		this.fieldInfo = info;
	}

	public FieldMetaInfo() {
		fieldInfo = new FieldInfo();

		fieldInfo.setLength(255);
		fieldInfo.setRequired(false);
		fieldInfo.setCanFilter(false);
	}

//	@Override
//	public int compareTo(FieldMetaInfo o) {
//		int sign = NumberUtils.compare(this.order, o.order);
//		if (sign == 0) {
//			return fieldInfo.getName().compareTo(o.fieldInfo.getName());
//		} else {
//			return sign;
//		}
//	}

//	public void addOrder(float order2) {
//		this.order *= order2;
//	}

	public FieldInfo getFieldInfo() {

		return fieldInfo;
	}

	public void fillFieldInfoWithCls(Method method) {

		fieldInfo.setLength(255);
		fieldInfo.setRequired(false);
		fieldInfo.setHidden(false);
		fieldInfo.setCanEdit(true);

//		order = 0;
		
		DsFieldType type = getFieldTypeByCls(method.getReturnType());
		fieldInfo.setType(type);
		if (type == DsFieldType.ENUM) {
			enumCls = method.getReturnType().asSubclass(Enum.class);
		}

		if (method.isAnnotationPresent(FilterField.class)) {
			fieldInfo.setCanFilter(true);
		}

		if (method.isAnnotationPresent(DataDictBind.class)) {
			DataDictBind ddBind = method.getAnnotation(DataDictBind.class);
			fieldInfo.setType(DsFieldType.ENUM);
			ddEnum = ddBind.dataDictName();
		}

	}

	public void fillFieldInfo(Method method) {

		DsField anno = method.getAnnotation(DsField.class);

		fieldInfo.setLength(anno.width());
		fieldInfo.setRequired(anno.require());
		fieldInfo.setHidden(anno.hidden());
		fieldInfo.setCanEdit(anno.editable());

//		order = ((int) (anno.order() * 100));

		if (anno.type() != DsFieldType.ANY) {
			fieldInfo.setType(anno.type());
		} else {
			DsFieldType type = getFieldTypeByCls(method.getReturnType());
			fieldInfo.setType(type);
			if (type == DsFieldType.ENUM) {
				enumCls = method.getReturnType().asSubclass(Enum.class);
			}
		}
		if (DsFieldType.BINARY.equals(fieldInfo.getType())) {
			relBinField = anno.refBinField();
		}

		if (method.isAnnotationPresent(FilterField.class)) {
			fieldInfo.setCanFilter(true);
		}

		if (method.isAnnotationPresent(DataDictBind.class)) {
			DataDictBind ddBind = method.getAnnotation(DataDictBind.class);
			fieldInfo.setType(DsFieldType.ENUM);
			ddEnum = ddBind.dataDictName();
		}

	}

	private static DsFieldType getFieldTypeByCls(Class<?> cls) {
		if (Date.class.isAssignableFrom(cls)) {
			return DsFieldType.DATETIME;
		} else if (cls.equals(int.class) || cls.equals(Integer.class)
				|| cls.equals(Long.class) || cls.equals(long.class)) {
			return DsFieldType.INTEGER;
		} else if (cls.equals(float.class) || cls.equals(Float.class)
				|| cls.equals(double.class) || cls.equals(Double.class)) {
			return DsFieldType.FLOAT;
		} else if (cls.equals(String.class)) {
			return DsFieldType.TEXT;
		} else if (cls.isEnum()) {
			return DsFieldType.ENUM;
		} else {
			return DsFieldType.TEXT;
		}
	}

	public void setTypeName(String fieldName) {
		fieldInfo.setTypeName(fieldName);
	}

	public void addPrefix(String fieldName) {
		fieldInfo.addPrefix(fieldName);
	}

	public String getName() {
		return fieldInfo.getName();
	}

	public void setTitle(String name) {
		fieldInfo.setTitle(name);
	}

	public void setCanEdit(boolean contains) {
		fieldInfo.setCanEdit(contains);
	}

	public void setValueArray(String[][] valArray) {
		fieldInfo.setValueMap(valArray);
	}

	public String toString() {
		return fieldInfo.toString();
	}

	public boolean isNonDisplay() {
		return fieldInfo.isNonDisplay();
	}

	public void setPk() {
		fieldInfo.setType(DsFieldType.SEQUENCE);
		fieldInfo.setHidden(true);
		fieldInfo.setLength(32);
	}

	public void setColInfo(Class cls) {
		fieldInfo.setCollProp(cls.getName());
	}

	public String getRelBinField() {
		return relBinField;
	}
}
