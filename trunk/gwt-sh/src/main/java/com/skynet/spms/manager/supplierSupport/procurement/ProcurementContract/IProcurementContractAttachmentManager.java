package com.skynet.spms.manager.supplierSupport.procurement.ProcurementContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 
 * 描述：供应商合同附件操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
public interface IProcurementContractAttachmentManager {

	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *           附件实体
	 */
	public void addSheet(Attachment item);

	/**
	 * 
	 * 描述：根据ID查询一条记录
	 * 
	 * @param id
	 *            UUID
	 * @return 返回查询到的实体，未查询到返回null
	 */
	public Attachment getById(String id);
	/**
	 * 
	 * 描述： 修改
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的实体的uuid
	 * @return 返回修改后的实体
	 */
	public Attachment update(Map<String, Object> newValues, String itemID);

	/**
	 * 描述： 分页查询
	 * 
	 * @param values
	 *            查询条件集合,各个条件为and的关系
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询结果集合
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow);

	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<Attachment> getList(int startRow, int endRow);

	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID);

}
