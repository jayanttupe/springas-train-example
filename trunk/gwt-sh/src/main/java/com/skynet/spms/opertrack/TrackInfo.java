package com.skynet.spms.opertrack;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;
import com.skynet.spms.persistence.entity.spmsdd.GeneralOperateLogStatus;

public class TrackInfo {
	
	
	private GeneralOperateLogEntity logEntity=new GeneralOperateLogEntity();

	private Class entityCls;
	
	public static TrackInfo getUpdateTrack(BaseEntity entity,Serializable id){
		return new TrackInfo(entity,id,GeneralOperateLogStatus.modify);
	}
	
	public static TrackInfo getInsertTrack(BaseEntity entity,Serializable id){
		return new TrackInfo(entity,id,GeneralOperateLogStatus.create);
	}
	private TrackInfo(BaseEntity entity, Serializable id,GeneralOperateLogStatus type) {
		
		logEntity.setRecordId((String) id);
		logEntity.setActionDate(new Date());
		logEntity.setOperator(getCurrUser());
		logEntity.setM_GeneralOperateLogStatus(type);
		logEntity.setRecordName(entity.getClass().getSimpleName());
		this.entityCls=entity.getClass();
	}	
	
	private String getCurrUser() {
		String user=GwtActionHelper.getCurrUser();
		if(StringUtils.equals(user, "anonymously")){
			user="System";
		}
		return user;
	}

	private Set<ValueInfo> valSet=new LinkedHashSet<ValueInfo>();
	
	public Set<ValueInfo> getValSet(){
		return valSet;
	}
	
	public void addUpdateStateInfo(String field,Object prevStatus,Object currStatus){
		
		ValueInfo info=new ValueInfo(prevStatus,currStatus,field);
		valSet.add(info);
	}
	
	public void addInsertStateInfo(String field,Object currStatus){
		ValueInfo info=new ValueInfo(currStatus,field);
		valSet.add(info);
	}

	
	
	public void setIsDel() {
		logEntity.setM_GeneralOperateLogStatus(GeneralOperateLogStatus.remove);
	}

	public void setVersion(Integer newVer) {
		logEntity.setVersion(newVer);
	}	
	
	public GeneralOperateLogEntity getLogEntity(){
		return logEntity;
	}
	
	
	public Class  getEntityCls(){
		return entityCls;
	}
	
	public String getOperation(){
		return logEntity.getOperator();
	}
}
