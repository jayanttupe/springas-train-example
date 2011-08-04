package com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.Attachment;

public interface ProPlanAttachmentManager {

	/**
	 * 添加一条记录
	 * 
	 * @param sheet
	 */
	public void addSheet(Attachment item);

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	public Attachment getById(String id);

	public Attachment update(Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询
	 * 
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Attachment> doQuery(Map<String, Object> values,
			int startRow, int endRow);

	/**
	 * 分页查询
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Attachment> getList(int startRow, int endRow);

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 */
	public void delete(String itemID);

}
