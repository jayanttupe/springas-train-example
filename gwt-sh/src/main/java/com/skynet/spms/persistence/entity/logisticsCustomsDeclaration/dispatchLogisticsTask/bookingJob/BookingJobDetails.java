package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder.BasePickupDeliveryVanning;

/**
 * @author wangyx
 * @version 1.0
 * @created 25-四月-2011 14:40:39
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BOOKING_JOB_DETAILS")
public class BookingJobDetails extends BasePickupDeliveryVanning {
	/**
	 * 订舱工作ID
	 */
	private String bookingJobID;

	@Column(name = "BOOKING_JOB_ID")
	public String getBookingJobID() {
		return bookingJobID;
	}

	public void setBookingJobID(String bookingJobID) {
		this.bookingJobID = bookingJobID;
	}
}
