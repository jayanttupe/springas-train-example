package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetails;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * 物流委托书明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface BookingJobDetailsManager extends CommonManager<BookingJobDetails>{

	/**
	 * 新增物流委托书明细
	 * @param bookingJobDetails
	 */
	public void insertBookingJobDetails(BookingJobDetails bookingJobDetails);

	/**
	 * 获取物流委托书明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书相关信息
	 */
	public List<BookingJobDetails> getBookingJobDetails(Map values, int startRow, int endRow);
}
