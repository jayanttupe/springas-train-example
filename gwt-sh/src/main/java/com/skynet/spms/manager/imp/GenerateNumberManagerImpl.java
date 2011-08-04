package com.skynet.spms.manager.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.GenerateNumberManager;

@Service
@Transactional
public class GenerateNumberManagerImpl implements GenerateNumberManager {

	@Override
	public String GenerateNumber(Session session, String strTableName, String strColumnName, String strMark) {
		String curDateYM = this.GetCurDate();

		// 取得最大编号
		String strHql = "select max(" + strColumnName +") from " + strTableName + " where " + strColumnName + " like '%" + curDateYM + "%'";
		List<String> result = session.createQuery(strHql).list();
		if (result != null && result.get(0) != null) {
			// 当前数据库中最大的编号
			String maxNO = result.get(0).substring(9, 13);
			return strMark + curDateYM + String.format("%04d", (Integer.valueOf(maxNO) + 1));
		} else {
			return strMark + curDateYM + "0001";
		}
	}
	
	/**
	 * 取得当前系统时间
	 * @return
	 */
	private String GetCurDate()
	{
		Date curDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		return simpleDateFormat.format(new Date());
	}
}
