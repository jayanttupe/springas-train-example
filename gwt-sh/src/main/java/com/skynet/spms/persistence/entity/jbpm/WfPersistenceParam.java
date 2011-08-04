package com.skynet.spms.persistence.entity.jbpm;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;

@Entity
@Table(name = "SPMS_JBPM_PERSISTENCE_PARAM")
public class WfPersistenceParam {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6550934383781742707L;

	private WfParamID wfParamID;	
	
	private String refFormName;
	
	private String refBusinessKey;

	
	public WfPersistenceParam(){
		
	}
	
	public void updateParam(WfPersistenceParam param){
		this.refBusinessKey=param.refBusinessKey;
		this.refFormName=param.refFormName;
	}
	
	public WfPersistenceParam(TaskInfoEntity taskInfo){
		this.wfParamID=new WfParamID(taskInfo);
	}

	@EmbeddedId
	public WfParamID getWfParamID(){
		return wfParamID;
	}
	
	public void setWfParamID(WfParamID id){
		this.wfParamID=id;
	}

	@Column(name="REF_FORM_NAME",length=128)
	public String getRefFormName() {
		return refFormName;
	}

	public void setRefFormName(String refFormName) {
		this.refFormName = refFormName;
	}

	@Column(name="REF_BUSINESS_KEY",length=128)
	public String getRefBusinessKey() {
		return refBusinessKey;
	}

	public void setRefBusinessKey(String refBusinessKey) {
		this.refBusinessKey = refBusinessKey;
	}

//	@Enumerated(EnumType.STRING)
//	@Column(name="PRIORITY_TYPE",length=32)
//	public PriorityType getPriority() {
//		return priority;
//	}
//
//	public void setPriority(PriorityType priority) {
//		this.priority = priority;
//	}
//
//	@Enumerated(EnumType.STRING)
//	@Column(name="FLOW_TYPE",length=32)
//	public FlowType getFlowType() {
//		return flowType;
//	}
//
//	public void setFlowType(FlowType flowType) {
//		this.flowType = flowType;
//	}


	
}
