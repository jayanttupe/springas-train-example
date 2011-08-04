package com.skynet.spms.manager.approval.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.entity.CommonApproveParam;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.jbpm.WorkflowEnum;
import com.skynet.spms.jbpm.entity.WfInstParamCols;
import com.skynet.spms.jbpm.service.WfTaskOperService;
import com.skynet.spms.manager.approval.ApprovalManager;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.approvalEntityManage.ApprovalRecord;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.ApprovalAvailableEntity;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class ApprovalManagerImpl extends CommonManagerImpl<ApprovalRecord>
		implements ApprovalManager {

	private Log log = LogFactory.getLog(ApprovalManagerImpl.class);

	@Autowired
	private UUIDGeneral uUIDGeneral;

	@Autowired
	private WfTaskOperService taskOperService;

	@Override
	public void saveApproval(ApprovalRecord o, String businessType) {
		String approvalNumber = uUIDGeneral.getSequence("apvrd");
		o.setApprovalNumber(approvalNumber);
		getSession().save(o);

		startApproveFlow(o.getId(), businessType, o.getAmount());
	}

	private void startApproveFlow(String id, String businessType, float amount) {

		WfInstParamCols paramCols = new WfInstParamCols();
		CommonApproveParam param = new CommonApproveParam();
		param.setAmount(amount);
		param.setManagerForType("SalesSupervisor");
		if ("sale".equals(businessType)) {
			param.setManagerForType("SalesSupervisor");
		}
		param.setBusinessType(businessType);
		param.setBusinessUser(GwtActionHelper.getCurrUser());

		paramCols.addInstWfParam("param", param);

		taskOperService.startProcessInstanceWithKeyAndVariables(
				WorkflowEnum.common_approval, id, paramCols);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ApprovalRecord getApprovalRecordById(String bussinessKey,
			boolean fetch) {
		ApprovalRecord o = null;
		StringBuffer hql = new StringBuffer();
		hql.append("from ApprovalRecord r ");
		if (fetch) {
			hql.append(" left join fetch r.m_ApprovalAvailableEntity m");
		}
		hql.append(" where r.id = ? ");
		if (fetch) {
			hql.append(" order by m.createDate desc");
		}
		Query query = getSession().createQuery(hql.toString());
		query.setParameter(0, bussinessKey);

		List list = query.list();

		if (list.size() > 0) {
			o = (ApprovalRecord) list.get(0);
		}

		return o;
	}

	@Override
	public void updateApproval(ApprovalRecord approval) {
		getSession().update(approval);

	}

	@Override
	public void saveApprovalAvailable(ApprovalAvailableEntity available) {
		getSession().save(available);

	}

	@Override
	public void updateSheetApprovalStatus(String approvalrecordId,
			String bizType, String status) {
		ApprovalRecord approvalRecord = (ApprovalRecord) getSession().get(
				ApprovalRecord.class, approvalrecordId);
		if (approvalRecord == null) {
			return;
		}
		String hql = "update ";
		if (WorkFlowBusinessType.BUYBACKCONTRACT.equals(bizType)) {
			hql += "BuybackContractTemplate";
		} else if (WorkFlowBusinessType.PROCUREMENTCONTRACT.equals(bizType)) {
			hql += "ProcurementContractTemplate";
		} else if (WorkFlowBusinessType.SALESCONTRACT.equals(bizType)) {
			hql += "SalesContractTemplate";
		} else if (WorkFlowBusinessType.CUSTOMERREPAIRCONTRACT.equals(bizType)) {
			hql += "RepairContractTemplate";
		} else if (WorkFlowBusinessType.SUPPIERREPAIRCONTRCT.equals(bizType)) {
			hql += "RepairInspectClaimContractTemplate";
		} else if (WorkFlowBusinessType.CONSIGNRENEWFORM.equals(bizType)) {
			hql += "ConsignRenew";
		} else if (WorkFlowBusinessType.PROCUREMENTPLAN.equals(bizType)) {
			hql += "ProcurementPlan";
		} else if (WorkFlowBusinessType.LEASECONTRACT.equals(bizType)) {
			hql += "LeaseContractTemplate";
		} else if (WorkFlowBusinessType.SSLEASECONTRACT.equals(bizType)) {
			hql += "SSLeaseContractTemplate";
		}
		String auditStatus = "";
		if ("deploy".equals(status)) {
			auditStatus = AuditStatus.pass.toString();
		} else if ("refuse".equals(status)) {
			auditStatus = AuditStatus.noPass.toString();
		}
		hql += " set auditStatus='" + auditStatus + "' where id='"
				+ approvalRecord.getSheetNo() + "'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	// @Override
	// public BaseContactEntity getContactBySheetNoAndSheetType(String
	// sheetNo,String sheetType) {
	// String entityName = this.getContactBySheetType(sheetType);
	// BaseContactEntity contact = null;
	// try {
	// Class entityCls = Class.forName(entityName);
	// Object o = getSession().get(entityCls,sheetNo);
	// if(!(o instanceof BaseContactEntity)){
	// log.error("该实体没有继承自BaseContact");
	// }else{
	// contact = (BaseContactEntity) o;
	// }
	// } catch (Exception e) {
	// log.error(e);
	// }
	//
	// return contact;
	// }

	// private String getContactBySheetType(String sheetType) {
	// //根据单据类型返回实体名称
	// return sheetType;
	// }

}
