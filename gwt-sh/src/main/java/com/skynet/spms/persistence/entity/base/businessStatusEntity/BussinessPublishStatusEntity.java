package com.skynet.spms.persistence.entity.base.businessStatusEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;

/**
 * 业务发布状态实体是针对本系统中，业务实体的发布状态的的跟踪，当业务实体被新建之后，只用在发布状态下才可以做提交操作的业务。
 * 发布操作是业务人员对于自身在操作业务的 时候，将进行业务提交其他人员或者部门处理业务之前的人工再确认过程，确保提交的准确性。
 * 
 * 已发布，标识当前的业务实体已经处于发布状态，处于发布状态的业务实体可以执行后续的其他业务操作，例如，提交、指令下达、
 * 在本业务实体上创建其他业务实体等业务延伸的操 作。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:37
 */
@Entity
@Table(name = "SPMS_BUSSINESS_PUBLISH_STATUS")
public class BussinessPublishStatusEntity extends BaseStatusEntity {

	
	private static final long serialVersionUID = 1894872613067807920L;
	private PublishStatus m_PublishStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "PUBLISH_STATUS")
	public PublishStatus getM_PublishStatus() {
		return m_PublishStatus;
	}

	public void setM_PublishStatus(PublishStatus m_PublishStatus) {
		this.m_PublishStatus = m_PublishStatus;
	}

}