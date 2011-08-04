package com.skynet.spms.action.logisticsCustomsDeclaration.documentRecords;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.TransprotationDocumentManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.TransprotationDocument;

/**
 * 描述：运输单证相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class TransprotationDocumentDatasourceAction implements DataSourceAction<TransprotationDocument>{
	
	@Autowired
	private TransprotationDocumentManager transprotationDocumentManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"transprotationDocument_dataSource"};
	}

	/**
	 * 新增运输单证相关信息
	 * @param item
	 */
	@Override
	public void insert(TransprotationDocument item) {
	}
	
	/**
	 * 更新运输单证相关信息
	 * @param newValues
	 * @param itemID
	 * @return 运输单证相关信息
	 */
	@Override
	public TransprotationDocument update(Map newValues, String itemID) {
		return null;
	}

	/**
	 * 删除运输单证相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
	}
	
	/**
	 * 查询运输单证相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 运输单证相关信息
	 */
	@Override
	public List<TransprotationDocument> doQuery(Map values, int startRow, int endRow) {
		return transprotationDocumentManager.getTransprotationDocument(values, 0, -1);
	}

	/**
	 * 获取所有运输单证信息
	 * @param startRow
	 * @param endRow
	 * @return 运输单证信息
	 */
	@Override
	public List<TransprotationDocument> getList(int startRow, int endRow) {
		return transprotationDocumentManager.getTransprotationDocument(null, 0, -1);
	}
}
