package com.skynet.spms.persistence.entity.base.baseEntity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 抽象层的基础状态实体，依据该基础状态实体扩展出其他业务所需要的跟踪状态实体。该基础状态实体实现了Serializable接口，因此继承这个实体的业务实体都能实现
 * 远程传输。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:36
 */
@MappedSuperclass
public class BaseStatusEntity extends BaseIDEntity {

	private String operator;
	private Integer version;
	private Date actionDate;
	private String actionDescription;
	
	@Column(name="OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name="STATUS_VERSION")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_DATE")
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	@Column(name="ACTION_DESCRIPTION")
	public String getActionDescription() {
		return actionDescription;
	}
	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

}