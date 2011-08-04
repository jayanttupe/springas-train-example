package com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
/**
 * 采购报价Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementQuotationSheetRecordManager {
	
	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void addProcurementQuotationSheetRecord(ProcurementQuotationSheetRecord o);

	/**
	 * 更新报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public ProcurementQuotationSheetRecord updateProcurementQuotationSheetRecord(Map<String, Object> newValues, String itemID);

	
	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void deleteProcurementQuotationSheetRecord(String itemID);

	/**
	 * 分页查询报价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<ProcurementQuotationSheetRecord> queryProcurementQuotationSheetRecordList(int startRow, int endRow);
	
	/**
	 * 根据报价单Id 获得报价单
	 * @param sheetId 报价打ID
	 * @return 报价单
	 */
	public ProcurementQuotationSheetRecord getProcurementQuotationSheetRecordById(String sheetId);

}
