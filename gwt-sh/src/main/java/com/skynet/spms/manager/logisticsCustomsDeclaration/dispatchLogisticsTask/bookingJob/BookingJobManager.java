package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJob;

/**
 * 物流委托书相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface BookingJobManager extends CommonManager<BookingJob>{

	/**
	 * 保存物流委托书相关信息
	 * @param bookingJob
	 * @return 物流委托书相关信息
	 */
	public BookingJob saveBookingJob(BookingJob bookingJob);

	/**
	 * 获取物流委托书相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书相关信息
	 */
	public List<BookingJob> getBookingJob(Map values, int startRow, int endRow);
	
	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setWorkStatus(String orderID);
}
