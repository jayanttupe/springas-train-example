package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder.BasePickupDeliveryVanning;

/**
 * @author wangyx
 * @version 1.0
 * @created 24-四月-2011 18:16:26
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_SHIPPING_JOB_DETAILS")
public class ShippingJobDetails extends BasePickupDeliveryVanning {
	/**
	 * 起运工作ID
	 */
	private String shippingJobID;

	@Column(name = "BOOKING_JOB_ID")
	public String getShippingJobID() {
		return shippingJobID;
	}

	public void setShippingJobID(String shippingJobID) {
		this.shippingJobID = shippingJobID;
	}
}
