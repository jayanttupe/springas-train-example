package com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
/**
 *采购询价单
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementInquirySheetManager {
	
	
	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void addProcurementInquirySheet(ProcurementInquirySheet o);

	/**
	 * 更新询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public ProcurementInquirySheet updateProcurementInquirySheet(Map<String, Object> newValues, String itemID);

	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void deleteProcurementInquirySheet(String itemID);

	/**
	 * 分页查询询价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<ProcurementInquirySheet> queryProcurementInquirySheetList(int startRow, int endRow);
	
	/**
	 * 根据询价单Id 获得询价单
	 * @param sheetId 询价打ID
	 * @return 询价单
	 */
	public ProcurementInquirySheet getProcurementInquirySheetById(String sheetId);

}
