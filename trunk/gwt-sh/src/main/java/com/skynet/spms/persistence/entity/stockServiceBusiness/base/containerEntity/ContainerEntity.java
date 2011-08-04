package com.skynet.spms.persistence.entity.stockServiceBusiness.base.containerEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag.SPMSRFIDTag;

/**
 * @author FANYX
 * @version 1.0  容器实体
 * @created 13-六月-2011 11:08:57
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CONTAINER_ENTITY")
public class ContainerEntity extends BaseIDEntity {

	
	private String containerMaterial;
	private String containerNumber;
	/**
	 * 周转箱，包装箱，托盘
	 */
	private String containerType;
	private String remark;
	private String stockRoomNumber;
	public SPMSRFIDTag m_SPMSRFIDTag;

}