package com.skynet.spms.persistence.entity.jbpm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;

@Embeddable
public class WfParamID implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8805155594207111779L;

//	private String procID;
	
	private String defFormName;	
	
	private String taskID;
	
	public WfParamID(){
		
	}

	public WfParamID(TaskInfoEntity taskInfo){
//		this.procID=taskInfo.getProcInstID();
		this.taskID=taskInfo.getTaskID();
	}

	@Column(name="TASK_INST_ID",length=128,nullable=false)
	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

//	@Column(name="PROC_INST_ID",length=128,nullable=false)
//	public String getProcID() {
//		return procID;
//	}
//
//	public void setProcID(String procID) {
//		this.procID = procID;
//	}

	@Column(name="DEF_FORM_NAME",length=64,nullable=false)
	public String getDefFormName() {
		return defFormName;
	}

	public void setDefFormName(String formName) {
		this.defFormName = formName;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(79,-137)
		.append(taskID)
		.append(defFormName)
		.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		WfParamID param=(WfParamID)obj;
		return new EqualsBuilder()
			.append(taskID,param.getTaskID())
//			.append(procID,param.getProcID())
			.append(defFormName, param.getDefFormName())
			.isEquals();
	}

	
	
}
