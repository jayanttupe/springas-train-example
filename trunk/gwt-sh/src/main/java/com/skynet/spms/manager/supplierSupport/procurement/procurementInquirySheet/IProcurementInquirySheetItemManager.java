package com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheetItem;
/**
 * 采购询价单明细
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementInquirySheetItemManager {
	
	/**
	 * 添加询价单明细
	 * @param o 待添加的询价单明细
	 */
	public void addProcurementInquirySheetItem(ProcurementInquirySheetItem o);
	
	/**
	 * 更新询价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 询价单明细Id
	 * @return 询价单明细对象
	 */
	public ProcurementInquirySheetItem updateProcurementInquirySheetItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除询价单明细
	 * @param itemID 询价单明细id
	 */
	public void deleteProcurementInquirySheetItem(String itemID);

	/**
	 * 分页查询询价单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单明细列表
	 */
	public List<ProcurementInquirySheetItem> queryProcurementInquirySheetItemList(int startRow, int endRow);
	
	/**
	 * 根据询价单明细Id 获得询价单明细
	 * @param sheetId 询价单明细Id
	 * @return 询价单明细
	 */
	public ProcurementInquirySheetItem getProcurementInquirySheetItemById(String sheetId);
	
	/**
	 * 根据询价单id查询询价单明细
	 * @param inquiryId 询价单Id
	 * @return 询价单明细
	 */
	public List<ProcurementInquirySheetItem> queryProcurementInquirySheetItemListByInquiryId(String inquiryId);
}
