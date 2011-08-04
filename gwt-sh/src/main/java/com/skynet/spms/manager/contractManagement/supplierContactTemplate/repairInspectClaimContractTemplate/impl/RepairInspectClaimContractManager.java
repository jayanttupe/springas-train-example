package com.skynet.spms.manager.contractManagement.supplierContactTemplate.repairInspectClaimContractTemplate.impl;

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
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.contractManagement.supplierContactTemplate.repairInspectClaimContractTemplate.IRepairInspectClaimContractManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.RepaireContractTemplate.RepairContractTemplate;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class RepairInspectClaimContractManager extends
		HibernateDaoSupport implements
		IRepairInspectClaimContractManager {

	private final static String HQL = "select o from RepairInspectClaimContractTemplate o fetch all properties ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	public void addRepairInspectClaimContractTemplate(
			RepairInspectClaimContractTemplate o) {
		// 生成业务编号
		o.setContractNumber(uuidGeneral.getSequence("RS"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		o.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		o.setM_BussinessStatusEntity(bStatus);
		getHibernateTemplate().save(o);
		//如果是依赖于客户的合同，查找到客户合同，并作两个合同外键关联
		if(null!=o.getCustomerContractID()){
			RepairContractTemplate customerContract = getHibernateTemplate().get(
					RepairContractTemplate.class, o.getCustomerContractID());
			if(null!=customerContract){
				customerContract.setSuppContractId(o.getId());
				customerContract.setSuppContractNumber(o.getContractNumber());
				getHibernateTemplate().update(customerContract);
			}
		}
	}

	public RepairInspectClaimContractTemplate updateRepairInspectClaimContractTemplate(
			Map<String, Object> newValues, String itemID) {
		RepairInspectClaimContractTemplate entity =getRepairInspectClaimContractTemplateById(itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	public void deleteRepairInspectClaimContractTemplate(String itemID) {
		String hql = "update RepairInspectClaimContractTemplate set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public List<RepairInspectClaimContractTemplate> queryRepairInspectClaimContractTemplateList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public RepairInspectClaimContractTemplate getRepairInspectClaimContractTemplateById(
			String sheetId) {
		return getHibernateTemplate().get(RepairInspectClaimContractTemplate.class, sheetId);
	}
	
	@Override
	public void updateContractAmount(String contractID,Float amount) {
		RepairInspectClaimContractTemplate o = getRepairInspectClaimContractTemplateById(contractID);
		o.setExtendedValueTotalAmount(amount);
		getHibernateTemplate().update(o);
	}

}
