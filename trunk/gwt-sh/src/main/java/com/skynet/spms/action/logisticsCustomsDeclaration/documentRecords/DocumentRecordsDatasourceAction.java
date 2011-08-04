package com.skynet.spms.action.logisticsCustomsDeclaration.documentRecords;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.DocumentRecordsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.CIQDocument;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.DocumentRecords;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.TransprotationDocument;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：单证记录相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DocumentRecordsDatasourceAction implements DataSourceAction<DocumentRecords>{
	@Autowired
	private DocumentRecordsManager documentRecordsManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"documentRecords_dataSource"};
	}

	/**
	 * 新增单证记录相关信息
	 * @param item
	 */
	@Override
	public void insert(DocumentRecords item) {
		documentRecordsManager.insertEntity(item);
	}
	
	/**
	 * 更新单证记录相关信息
	 * @param newValues
	 * @param itemID
	 * @return 单证记录相关信息
	 */
	@Override
	public DocumentRecords update(Map newValues, String itemID) {
		DocumentRecords documentRecords = new DocumentRecords();
		BeanPropUtil.fillEntityWithMap(documentRecords, newValues);
		
		List<TransprotationDocument> transprotationDocumentList =
			OneToManyTools.ConvertToEntity(documentRecords.getTransprotationDocumentList(), TransprotationDocument.class);
		documentRecords.setTransprotationDocumentList(transprotationDocumentList);
		
		List<CIQDocument> cIQDocumentList = OneToManyTools.ConvertToEntity(documentRecords.getcIQDocumentList(), CIQDocument.class);
		documentRecords.setcIQDocumentList(cIQDocumentList);

		return documentRecordsManager.saveDocumentRecords(documentRecords);
	}

	/**
	 * 删除单证记录相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		documentRecordsManager.deleteEntity(itemID,DocumentRecords.class);
	}
	
	/**
	 * 查询单证记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 单证记录相关信息
	 */
	@Override
	public List<DocumentRecords> doQuery(Map values,
			int startRow, int endRow) {
		return documentRecordsManager.getDocumentRecords(values, 0, -1);
	}

	/**
	 * 获取所有单证记录信息
	 * @param startRow
	 * @param endRow
	 * @return 单证记录信息
	 */
	@Override
	public List<DocumentRecords> getList(int startRow, int endRow) {
		
		return documentRecordsManager.getDocumentRecords(null, 0, -1);
	}

}
