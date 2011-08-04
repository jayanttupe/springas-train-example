package com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem;
import java.util.List;
import java.util.Map;
import com.skynet.spms.action.supplierSupport.procurement.procurementTransferSheet.procurementTransferSheetItem.ProcurementTransferSheetItemAction;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem;
/**
 * 调拨单明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementTransferSheetItemManager {
	
	
	/**
	 * 添加调拨单明细
	 * @param o 待添加的调拨单明细
	 */
	public void addProcurementTransferSheetItem(ProcurementTransferSheetItem o);

	/**
	 * 更新调拨单明细
	 * @param newValues 新的数据对象
	 * @param itemID 调拨单明细Id
	 * @return 调拨单明细对象
	 */
	public ProcurementTransferSheetItem updateProcurementTransferSheetItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除调拨单明细
	 * @param itemID 调拨单明细id
	 */
	public void deleteProcurementTransferSheetItem(String itemID);

	/**
	 * 分页查询调拨单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单明细列表
	 */
	public List<ProcurementTransferSheetItem> queryProcurementTransferSheetItemList(int startRow, int endRow);
	
	/**
	 * 根据调拨单明细Id 获得调拨单明细
	 * @param sheetId 报价打ID
	 * @return 调拨单明细
	 */
	public ProcurementTransferSheetItem getProcurementTransferSheetItemById(String sheetId);

	/**
	 * 根据调拨单Id 获得调拨单明细信息
	 * @param transferId 调拨单Id
	 * @return 调拨单明细列表
	 */
	public List<ProcurementTransferSheetItem> queryProcurementTransferSheetItemByTransferId(String transferId);
}
