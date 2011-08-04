package com.skynet.spms.persistence.entity.base.baseOrderEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * 抽象层的基础指令实体，封装了基本指令信息。本系统内的跨部门，跨企业的命令操作都需要用到指令来完成。基础指令实体继承了BaseEntity实体，
 * 同时聚合Busin essStatusEntity实体和BusinessStatusEntity实体，实现对指令实体的执行状态跟踪以及发布状态的跟踪。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */

@MappedSuperclass
public class BaseOrderEntity extends BaseEntity {
	/** 指令编号 **/
	private String orderNumber;
	/** 指令下达人员 **/
	private String orderedBy;
	/** 指令描述信息 **/
	private String description;
	private Boolean isOrderSet;
	public BussinessStatusEntity m_BussinessStatusEntity;
	public BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "ORDERED_BY")
	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "IS_ORDER_SET")
	public Boolean isOrderSet() {
		return isOrderSet;
	}

	public void setOrderSet(Boolean isOrderSet) {
		this.isOrderSet = isOrderSet;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_STATUS_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_STATUS_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

}