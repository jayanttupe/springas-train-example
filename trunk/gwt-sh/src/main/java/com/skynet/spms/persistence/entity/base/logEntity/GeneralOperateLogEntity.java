package com.skynet.spms.persistence.entity.base.logEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.spms.opertrack.PropertyValue;
import com.skynet.spms.opertrack.ValueInfo;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.GeneralOperateLogStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:46
 */
@Entity
@Table(name = "SPMS_GENE_OPER_LOG")
public class GeneralOperateLogEntity extends BaseIDEntity {
	
	private Logger log = LoggerFactory.getLogger(GeneralOperateLogEntity.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472149793181056687L;

	private GeneralOperateLogStatus m_GeneralOperateLogStatus;

	private String recordId;

	private String recordName;

	private String operator;
		
	private Date actionDate;
	
	private String actionDescription;
	
	private int version;
	
	public void setVersion(int ver){
		this.version=ver;
	}
	
	@Column(name="VERSION")
	public int getVersion(){
		return version;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "OPERATE_STATUS")
	public GeneralOperateLogStatus getM_GeneralOperateLogStatus() {
		return m_GeneralOperateLogStatus;
	}

	@Column(name = "RECORD_ID")
	public String getRecordId() {
		return recordId;
	}

	@Column(name = "RECORD_NAME")
	public String getRecordName() {
		return recordName;
	}

	public void setM_GeneralOperateLogStatus(
			GeneralOperateLogStatus m_GeneralOperateLogStatus) {
		this.m_GeneralOperateLogStatus = m_GeneralOperateLogStatus;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	

	@Column(name = "ACTION_DATE")
	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	@Lob
	@Column(name = "ACTION_DESCRIPTION")
	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	
	
	@Transient
	public  List<PropertyValue> getValueSet(){
		List<PropertyValue> set=new ArrayList<PropertyValue>();
		
		if(actionDescription.contains("xml")){
			try {
				Document doc=DocumentHelper.parseText(actionDescription);
				
				List list=doc.getRootElement().element("fields").elements("field");
				
				for(Object val:list){
					Element elem=(Element)val;
					
					PropertyValue valueInfo=new PropertyValue(elem);
					set.add(valueInfo);
				}
				
			} catch (DocumentException e) {
				log.error("xml format fail",e);
			}			
			
		}		
		
		return set;
	}

}