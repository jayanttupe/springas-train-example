package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：物流委托书相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BookingJobDatasourceAction implements DataSourceAction<BookingJob>{
	@Autowired
	private BookingJobManager bookingJobManager;

	
	@Override
	public String[] getBindDsName() {
		return new String[]{"bookingJob_dataSource"};
	}
	
	/**
	 * 新增物流委托书相关信息
	 * @param bookingJob
	 */
	@Override
	public void insert(BookingJob bookingJob) {
	}

	/**
	 * 更新物流委托书相关信息
	 * @param newValues
	 * @param number
	 * @return 物流委托书相关信息
	 */
	@Override
	public BookingJob update(Map newValues, String number) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("bookingStatus")) {
			bookingJobManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {
			BookingJob bookingJob = new BookingJob();		
			BeanPropUtil.fillEntityWithMap(bookingJob, newValues);
			return bookingJobManager.saveBookingJob(bookingJob);	
		}
	}

	/**
	 * 删除物流委托书相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		bookingJobManager.deleteEntity(itemID, BookingJob.class);
	}

	/**
	 * 查询物流委托书相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书相关信息
	 */
	@Override
	public List<BookingJob> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return bookingJobManager.getBookingJob(values, 0, -1);
	}

	/**
	 * 获取所有物流委托书信息
	 * @param startRow
	 * @param endRow
	 * @return 物流委托书信息
	 */
	@Override
	public List<BookingJob> getList(int startRow, int endRow) {
		return bookingJobManager.getBookingJob(null, 0, -1);
	}

}
