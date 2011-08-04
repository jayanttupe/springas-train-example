package com.skynet.spms.persistence.entity.base.businessStatusEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.RequisitionStatus;

/**
 * 业务状态实体是针对本系统中，业务实体的操作执行的跟踪，一旦本系统的用户对于系统中业务实体执行新建、提交、打回、确认、分派、处理、关闭操作，
 * 本业务状态实体将组合在 业务实体中，对业务实体进行有效的状态跟踪。
 * 
 * 业务状态实体的版本(version)用于管理操作人员对于业务实体的每次动作操作的跟踪。当用户新建业务实体后，业务实体会组合进一个业务状态实体信息，
 * 其中包括了操作 人员信息，版本号信息，以及可能填写的备注信息。版本号默认由1开始递增，步长为1。每次针对业务实体执行动作行为后，版本号自动累积增加。
 * 
 * 版本的管理有利于对于业务实体的操作动作的历史记录，通常能记录下哪个操作人员，在什么时候，针对业务实体做什么动作，以及版本号的更新情况。
 * 系统默认显示当前版本号最高 的为当前业务实体的默认状态。
 * 
 * 已新建，标识业务实体被操作人员新创建的状态； 已提交，标识业务实体被操作人员由新建或者修改向系统进行提交业务实体。
 * 已打回，标识业务实体被处理人员由于业务原因将业务实体打回给业务操作人员。 已确认，标识业务实体提交后，已被处理人员确认，并准备开始处理业务。
 * 已分派，标识业务实体提交后，被处理人员进行分派给其他业务人员进行处理。 已处理，标识业务实体提交后，该业务实体已被经处理人员进行了相关业务处理。
 * 已关闭，标识业务实体提交后，处理人员的处理结果符合业务人员的期望，则有该业务实体的发起人关闭业务。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:37
 */
@Entity
@Table(name = "SPMS_BUSSINESS_STATUS")
public class RequisitionStatusEntity extends BaseStatusEntity {

	public RequisitionStatus m_RequisitionStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "Requisition_STATUS")
	public RequisitionStatus getM_RequisitionStatus() {
		return m_RequisitionStatus;
	}

	public void setM_RequisitionStatus(RequisitionStatus m_RequisitionStatus) {
		this.m_RequisitionStatus = m_RequisitionStatus;
	}

}