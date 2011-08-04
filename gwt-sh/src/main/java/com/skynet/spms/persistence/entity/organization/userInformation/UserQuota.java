package com.skynet.spms.persistence.entity.organization.userInformation;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:13
 */
/*@Entity
@Table(name="SPMS_USERQUOTA")*/
public class UserQuota extends BaseIDEntity{

	private Integer approvalQuota;

	@Column(name="APPROVAL_QUOTA")
	public Integer getApprovalQuota() {
		return approvalQuota;
	}

	public void setApprovalQuota(Integer approvalQuota) {
		this.approvalQuota = approvalQuota;
	}

	
}