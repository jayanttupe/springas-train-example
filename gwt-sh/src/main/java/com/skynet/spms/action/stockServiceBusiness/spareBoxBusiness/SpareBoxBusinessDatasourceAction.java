package com.skynet.spms.action.stockServiceBusiness.spareBoxBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.reparePartBusiness.ReparePartBusinessManager;
import com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.SpareBoxBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness.ReparePartBusiness;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBox;

/**
 * 描述：航材包相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class SpareBoxBusinessDatasourceAction implements DataSourceAction<SpareBox>{
	@Autowired
	private SpareBoxBusinessManager spareBoxBusinessManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"spareBoxBusiness_dataSource"};
	}

	/**
	 * 新增航材包相关信息
	 * @param spareBox
	 */
	@Override
	public void insert(SpareBox spareBox) {
		spareBoxBusinessManager.insertEntity(spareBox);
	}

	/**
	 * 更新航材包相关信息
	 * @param newValues
	 * @param number
	 * @return 航材包相关信息
	 */
	@Override
	public SpareBox update(Map newValues, String number) {
		return (SpareBox) spareBoxBusinessManager.updateEntity(newValues, number, SpareBox.class);
	}

	/**
	 * 删除航材包相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		spareBoxBusinessManager.deleteEntity(number, SpareBox.class);
	}

	/**
	 * 查询航材包相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材包相关信息
	 */
	@Override
	public List<SpareBox> doQuery(Map values, int startRow, int endRow) {
		return spareBoxBusinessManager.getSpareBox(values, 0, -1);
	}

	/**
	 * 获取所有航材包信息
	 * @param startRow
	 * @param endRow
	 * @return 航材包信息
	 */
	@Override
	public List<SpareBox> getList(int startRow, int endRow) {
		return spareBoxBusinessManager.getSpareBox(null, 0, -1);
	}
}