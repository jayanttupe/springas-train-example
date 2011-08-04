package com.skynet.spms.action.supplierSupport.procurement.procurementPaln;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 采购计划控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class ProcurementPlanAction implements DataSourceAction<ProcurementPlan> {

	public String[] getBindDsName() {

		return new String[] { DSKey.S_PROCUREMENTPLAN_DS };
	}

	@Resource
	UUIDGeneral uuidGeneral;

	@Autowired
	private ProcurementPlanManager manager;

	/**
	 * 
	 * 添加采购计划
	 * 
	 * @param item
	 *            采购计划对象
	 * @return void
	 */
	public void insert(ProcurementPlan item) {
		item.setCreateBy(GwtActionHelper.getCurrUser());
		item.setCreateDate(new Date());
		item.setProcurementPlanNumber(uuidGeneral.getSequence("PL"));
		// 构建审批状态
		item.setAuditStatus(AuditStatus.create);
		// 构建发布状态
		BussinessPublishStatusEntity publish = new BussinessPublishStatusEntity();
		publish.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(publish);
		// 投建业务状态
		BussinessStatusEntity status = new BussinessStatusEntity();
		status.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(status);

		manager.addProcurementPlan(item);
	}

	/**
	 * 
	 * 修改采购计划
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购计划ID
	 * @return 采购计划对象
	 */
	public ProcurementPlan update(Map<String, Object> newValues, String itemID) {

		return manager.updateProcurementPlan(newValues, itemID);
	}

	/**
	 * 
	 * 删除采购计划的方法
	 * 
	 * @param itemID
	 *            采购计划ID
	 * @return void
	 */
	public void delete(String itemID) {
		manager.deleteProcurementPlan(itemID);
	}

	/**
	 * 
	 * 根据条件查询采购计划的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementPlan> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (values.get("id") != null) {
			return manager.queryProcurementById(values.get("id").toString());
		}
		return null;

	}

	/**
	 * 
	 * 分页查询采购计划的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementPlan> getList(int startRow, int endRow) {

		return manager.queryProcurementPlanList(startRow, endRow);
	}

}
