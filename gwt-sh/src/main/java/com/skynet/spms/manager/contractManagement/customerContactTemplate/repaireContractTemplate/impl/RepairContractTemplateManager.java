package com.skynet.spms.manager.contractManagement.customerContactTemplate.repaireContractTemplate.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.vo.ContractState;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.contractManagement.customerContactTemplate.repaireContractTemplate.IRepairContractTemplateManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.RepaireContractTemplate.RepairContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;
/**
 * 修理合同业务实现
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class RepairContractTemplateManager extends HibernateDaoSupport
		implements IRepairContractTemplateManager {

	private final static String HQL = "select o from RepairContractTemplate o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加合同
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addRepairContractTemplate(RepairContractTemplate item) {
		// 生成编号(模拟)
		item.setContractNumber(uuidGeneral.getSequence("RC"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		item.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		item.setM_BussinessStatusEntity(bStatus);
		item.setCreateDate(new Date());
		getHibernateTemplate().save(item);
	}

	/**
	 * 更新合同
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return RepairContractTemplate
	 */
	public RepairContractTemplate updateSRepairContractTemplate(
			Map<String, Object> newValues, String itemID) {
		RepairContractTemplate entity = getRepairContractTemplateById(itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除合同
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairContractTemplate(String itemID) {
		String hql = "update RepairContractTemplate set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页显示合同
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<RepairContractTemplate>
	 */
	public List<RepairContractTemplate> queryRepairContractTemplateList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false  order by o.createDate ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据合同编号查询
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairContractTemplate
	 */
	public RepairContractTemplate getRepairContractTemplateById(String sheetId) {
		return getHibernateTemplate()
				.get(RepairContractTemplate.class, sheetId);
	}

	/**
	 * 查询合同状态
	 * 
	 * @param @param itemID
	 * @param @return
	 * @return ContractState
	 */
	public ContractState getContractState(String itemID) {
		RepairContractTemplate o = getRepairContractTemplateById(itemID);
		ContractState state = new ContractState();
		state.setApprovalStage(o.getApprovalStage().name());
		state.setAuditStatus(o.getAuditStatus().name());
		return state;
	}

	/**
	 * 更新合同总金额
	 * 
	 * @param @param contractID
	 * @param @param amount
	 * @return void
	 */
	public void updateContractAmount(String contractID, Float amount) {
		RepairContractTemplate contract = getHibernateTemplate().get(
				RepairContractTemplate.class, contractID);
		contract.setExtendedValueTotalAmount(amount);
		getHibernateTemplate().update(contract);
	}

}
