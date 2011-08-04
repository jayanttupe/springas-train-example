package com.skynet.spms.persistence.entity.organization.userInformation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 10-三月-2011 11:11:13
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_USER_INFO")
public class UserInformation extends BaseEntity {
	/**
	 * 用户性别标识，其中0标识男性，1标识女性
	 */
	private int sex;
	private String mobile;
	private String fax;
	private String telephone;
	private String contactAddresses;
	private String remark;
	private String stockAccessCode;
	
	public List<Attachment> signatureFile = new ArrayList<Attachment>();
	
	@Column(name="STOCK_ACCESS_CODE")
	public String getStockAccessCode() {
		return stockAccessCode;
	}
	public void setStockAccessCode(String stockAccessCode) {
		this.stockAccessCode = stockAccessCode;
	}
	@Column(name="SEX")
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name="TELEPHONE")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name="CONTACT_ADDRESSES")
	public String getContactAddresses() {
		return contactAddresses;
	}
	public void setContactAddresses(String contactAddresses) {
		this.contactAddresses = contactAddresses;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Reference(itemCls=Attachment.class)
	@OneToMany()
	@JoinColumn(name = "RELATED_BUSSINESS_ID")
	public List<Attachment> getSignatureFile() {
		return signatureFile;
	}
	public void setSignatureFile(List<Attachment> signatureFile) {
		this.signatureFile = signatureFile;
	}


}