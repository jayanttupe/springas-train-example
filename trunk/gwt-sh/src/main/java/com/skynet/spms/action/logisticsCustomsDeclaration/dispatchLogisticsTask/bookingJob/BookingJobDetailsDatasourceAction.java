package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetailsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetails;

/**
 * 描述：物流委托书明细相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BookingJobDetailsDatasourceAction implements DataSourceAction<BookingJobDetails>{
	@Autowired
	private BookingJobDetailsManager bookingJobDetailsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"bookingJobDetails_dataSource"};
	}
	
	/**
	 * 新增物流委托书明细相关信息
	 * @param bookingJobDetails
	 */
	@Override
	public void insert(BookingJobDetails bookingJobDetails) {
		bookingJobDetailsManager.insertBookingJobDetails(bookingJobDetails);
	}

	/**
	 * 更新物流委托书明细相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流委托书明细相关信息
	 */
	@Override
	public BookingJobDetails update(Map<String, Object> newValues, String itemID) {
		return bookingJobDetailsManager.updateEntity(newValues, itemID, BookingJobDetails.class);
	}
	
	/**
	 * 删除物流委托书明细相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		bookingJobDetailsManager.deleteEntity(itemID, BookingJobDetails.class);
	}

	/**
	 * 查询物流委托书明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书明细相关信息
	 */
	@Override
	public List<BookingJobDetails> doQuery(Map values,int startRow, int endRow) {
		return bookingJobDetailsManager.getBookingJobDetails(values, startRow, endRow);
	}

	/**
	 * 获取所有物流委托书明细信息
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书明细信息
	 */
	@Override
	public List<BookingJobDetails> getList(int startRow, int endRow) {
		return bookingJobDetailsManager.getBookingJobDetails(null, 0, -1);
	}

}
