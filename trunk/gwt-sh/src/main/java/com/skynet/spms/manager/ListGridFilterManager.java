package com.skynet.spms.manager;

import java.util.List;

/**
 * 
 * 描述：用于表格的Filter过滤功能
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 * @param <T>
 */
public interface ListGridFilterManager<T> {
	/**
	 * 过滤listGrid当中的数据
	 * 
	 * @param t
	 *            类型名
	 * @param clientCriteria
	 *            客户端的criteria
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回给类型名的对象集合
	 */
	public List<T> doQueryFilter(Class<T> t, List clientCriteria, int startRow,
			int endRow);
}
