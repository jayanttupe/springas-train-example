package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetailsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetails;

@Service
@Transactional
public class BookingJobDetailsManagerImpl extends CommonManagerImpl<BookingJobDetails> implements BookingJobDetailsManager {

	@Override
	public List<BookingJobDetails> getBookingJobDetails(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(BookingJobDetails.class);
		criteria.add(Restrictions.eq("deleted", false));
		
		if (values != null)
		{
			Set set = values.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext())
			{
				Map.Entry entry =(Map.Entry)iterator.next();
				String fieldName = (String)entry.getKey().toString();
				if (fieldName != "temp")
					criteria.add(Restrictions.eq(fieldName, values.get(fieldName).toString()));
			}
			if(endRow>0){
				criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
			}
		}
		
		return criteria.list();
	}

	@Override
	public void insertBookingJobDetails(BookingJobDetails bookingJobDetails) {
		
		bookingJobDetails.setCreateBy(GwtActionHelper.getCurrUser());
		bookingJobDetails.setCreateDate(new Date());
		getSession().saveOrUpdate(bookingJobDetails);
	}
}
