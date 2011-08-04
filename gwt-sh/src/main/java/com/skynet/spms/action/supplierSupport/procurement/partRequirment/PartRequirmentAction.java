package com.skynet.spms.action.supplierSupport.procurement.partRequirment;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.PartRequirment.PartRequirementManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.partRequirement.PartRequirement;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 备件需求控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class PartRequirmentAction implements DataSourceAction<PartRequirement> {

	public String[] getBindDsName() {

		return new String[] { DSKey.S_PARTREQUIREMENT_DS };
	}

	@Autowired
	private PartRequirementManager manager;

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 
	 * 添加备件计划需求
	 * 
	 * @param item
	 *            备件计划需求对象
	 * @return void
	 */
	public void insert(PartRequirement item) {
		// 最后更新时间
		item.setLastTime(new Date());
		// 添加备件需求编号
		item.setPartRequirementNumber(uuidGeneral.getSequence("PN"));
		// 添加操作人员
		item.setCreateBy(GwtActionHelper.getCurrUser());
		// 业务状态
		BussinessStatusEntity bus = new BussinessStatusEntity();
		bus.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(bus);
		manager.addPartRequirement(item);
	}

	/**
	 * 
	 * 修改备件计划需求的方法
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            对象ID
	 * @return 备件计划需求对象
	 */
	public PartRequirement update(Map<String, Object> newValues, String itemID) {

		return manager.updatePartRequirement(newValues, itemID);
	}

	/**
	 * 
	 * 删除备件计划需求的方法
	 * 
	 * @param itemID
	 *            备件计划需求ID
	 * @return void
	 * @throws
	 */
	public void delete(String itemID) {
		manager.deletePartRequirement(itemID);
	}

	/**
	 * 
	 * 根据条件查询备件计划需求信息
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 备件计划需求对象集合
	 */
	public List<PartRequirement> doQuery(Map<String, Object> values,
			int startRow, int endRow) {

		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		return null;
	}

	/**
	 * 
	 * 分页查询备件计划需求的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 备件计划需求对象集合
	 */
	public List<PartRequirement> getList(int startRow, int endRow) {

		return manager.queryPartRequirementList(startRow, endRow);
	}

}
