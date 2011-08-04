package com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
/**
 * 调拨单Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementTransferSheetManager {
	
	
	/**
	 * 添加调拨单
	 * @param o 待添加的调拨单
	 */
	public void addProcurementTransferSheet(ProcurementTransferSheet o);

	/**
	 * 更新调拨单
	 * @param newValues 新的数据对象
	 * @param itemID 调拨单Id
	 * @return 调拨单对象
	 */
	public ProcurementTransferSheet updateProcurementTransferSheet(Map<String, Object> newValues, String itemID);

	/**
	 * 删除调拨单
	 * @param itemID 调拨单id
	 */
	public void deleteProcurementTransferSheet(String itemID);

	/**
	 * 分页查询调拨单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单列表
	 */
	public List<ProcurementTransferSheet> queryProcurementTransferSheetList(int startRow, int endRow);
	
	/**
	 * 根据调拨单Id 获得调拨单
	 * @param sheetId 报价打ID
	 * @return 调拨单
	 */
	public ProcurementTransferSheet getProcurementTransferSheetById(String sheetId);

}
