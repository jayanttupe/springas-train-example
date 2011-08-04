package com.skynet.spms.manager.supplierSupport.procurement.ProcurementContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.procurementContractTemplate.ProcurementContractTemplate;
/**
 * 
 * 描述：供应商明细操作接口
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
public interface IProcurementContractManager {
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param pact
	 *            供应商合同实体
	 */
	public void addSheet(ProcurementContractTemplate pact);

	/**
	 * 
	 * 描述：根据ID查询一条记录
	 * 
	 * @param id
	 *            UUID
	 * @return 返回查询到的实体，未查询到返回null
	 */
	public ProcurementContractTemplate getById(String id);
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
	public ProcurementContractTemplate update(Map<String, Object> newValues,
			String itemID);

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
	public List<ProcurementContractTemplate> doQuery(
			Map<String, Object> values, int startRow, int endRow);

	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<ProcurementContractTemplate> getList(int startRow, int endRow);


	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID);
}
