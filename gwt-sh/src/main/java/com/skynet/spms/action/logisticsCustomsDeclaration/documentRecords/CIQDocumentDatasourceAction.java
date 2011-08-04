package com.skynet.spms.action.logisticsCustomsDeclaration.documentRecords;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.CIQDocumentManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.CIQDocument;

/**
 * 描述：单证相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CIQDocumentDatasourceAction implements DataSourceAction<CIQDocument>{
	
	@Autowired
	private CIQDocumentManager ciqDocumentManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"cIQDocument_dataSource"};
	}

	/**
	 * 新增单证相关信息
	 * @param item
	 */
	@Override
	public void insert(CIQDocument item) {
	}
	
	/**
	 * 更新单证相关信息
	 * @param newValues
	 * @param itemID
	 * @return 单证相关信息
	 */
	@Override
	public CIQDocument update(Map newValues, String itemID) {
		return null;
	}

	/**
	 * 删除单证相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
	}
	
	/**
	 * 查询单证相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 单证相关信息
	 */
	@Override
	public List<CIQDocument> doQuery(Map values, int startRow, int endRow) {
		return ciqDocumentManager.getCIQDocument(values, 0, -1);
	}

	/**
	 * 获取所有单证信息
	 * @param startRow
	 * @param endRow
	 * @return 单证信息
	 */
	@Override
	public List<CIQDocument> getList(int startRow, int endRow) {
		return ciqDocumentManager.getCIQDocument(null, 0, -1);
	}
}
