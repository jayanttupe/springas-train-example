package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CountryCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 13:45:31
 */
@Entity
@Table(name="SPMS_ENTERPRISE_INFO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="DISCRIMINATOR",
		discriminatorType=DiscriminatorType.STRING,
		length=50)
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
public abstract class BaseEnterpriseInformation extends BaseEntity {
	
	private String abbreviation;
	private String address_en;
	private String address_zh;
	private String email;
	private String enterpriseHomepageURL;
	private String enterpriseName_en;
	private String enterpriseName_zh;
	private String fax;
	/**
	 * true标识是主营企业，整个系统中只能有一个主营企业，代表系统的主运营单位。
	 */
	private boolean isMainEnterprise;
	/**
	 * 初始默认状态为false，一旦该企业执行过重要的业务关联数据，则isRunning状态为true，标识该企业已经有业务数据，不可随意删除。
	 */
	private boolean isRunning;
	private String linkman;
	private String logoURL;
	private String parentCompany;
	private String postCode;
	private String telephone;
	private String parentId;

	private List<ShippingAddress> m_ShippingAddress = new ArrayList<ShippingAddress>();
	private VAT m_VAT;
	private List<RecipeInvoiceAddress> m_RecipeInvoiceAddress = new ArrayList<RecipeInvoiceAddress>();
	private List<ConsigneeAddress> m_ConsigneeAddress = new ArrayList<ConsigneeAddress>();
	private List<BaseEnterpriseInformation> m_ChildEnterpriseInformation = new ArrayList<BaseEnterpriseInformation>();
	private List<BankInformation> m_BankInformation = new ArrayList<BankInformation>();
	private CAGECode m_CAGECode;
	private CountryCode m_CountryCode;
	
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	@Column(name="ABBREVIATION")
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	@Column(name="PARENT_COMPANY")
	public String getParentCompany() {
		return parentCompany;
	}
	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}
	@Column(name="LINKMAN")
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	@Column(name="ENTERPRISE_NAME")
	public String getEnterpriseName_zh() {
		return enterpriseName_zh;
	}
	public void setEnterpriseName_zh(String enterpriseName_zh) {
		this.enterpriseName_zh = enterpriseName_zh;
	}
	@Column(name="ENTERPRISE_NAME_EN")
	public String getEnterpriseName_en() {
		return enterpriseName_en;
	}
	public void setEnterpriseName_en(String enterpriseName_en) {
		this.enterpriseName_en = enterpriseName_en;
	}
	@Column(name="ADDRESS_ZH")
	public String getAddress_zh() {
		return address_zh;
	}
	public void setAddress_zh(String address_zh) {
		this.address_zh = address_zh;
	}
	@Column(name="ADDRESS_EN")
	public String getAddress_en() {
		return address_en;
	}
	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}
	@Column(name="TELEPHONE")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name="FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name="POST_CODE")
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="ENTERPRISE_HOMEPAGE_URL")
	public String getEnterpriseHomepageURL() {
		return enterpriseHomepageURL;
	}
	public void setEnterpriseHomepageURL(String enterpriseHomepageURL) {
		this.enterpriseHomepageURL = enterpriseHomepageURL;
	}
	@Column(name="LOGO_URL")
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	@Column(name="IS_MAIN_ENTERPRISE")
	public boolean isMainEnterprise() {
		return isMainEnterprise;
	}
	public void setMainEnterprise(boolean isMainEnterprise) {
		this.isMainEnterprise = isMainEnterprise;
	}
	@Column(name="IS_RUNNING")
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	@Reference(itemCls=BankInformation.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<BankInformation> getM_BankInformation() {
		return m_BankInformation;
	}
	public void setM_BankInformation(List<BankInformation> m_BankInformation) {
		this.m_BankInformation = m_BankInformation;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CAGE_CODE")
	public CAGECode getM_CAGECode() {
		return m_CAGECode;
	}
	public void setM_CAGECode(CAGECode m_CAGECode) {
		this.m_CAGECode = m_CAGECode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "COUNTRY_CODE")
	public CountryCode getM_CountryCode() {
		return m_CountryCode;
	}
	public void setM_CountryCode(CountryCode m_CountryCode) {
		this.m_CountryCode = m_CountryCode;
	}

	@Reference(itemCls=ConsigneeAddress.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<ConsigneeAddress> getM_ConsigneeAddress() {
		return m_ConsigneeAddress;
	}
	public void setM_ConsigneeAddress(List<ConsigneeAddress> m_ConsigneeAddress) {
		this.m_ConsigneeAddress = m_ConsigneeAddress;
	}
	@Reference(itemCls=RecipeInvoiceAddress.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<RecipeInvoiceAddress> getM_RecipeInvoiceAddress() {
		return m_RecipeInvoiceAddress;
	}
	public void setM_RecipeInvoiceAddress(
			List<RecipeInvoiceAddress> m_RecipeInvoiceAddress) {
		this.m_RecipeInvoiceAddress = m_RecipeInvoiceAddress;
	}
	
	@Reference(itemCls=ShippingAddress.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<ShippingAddress> getM_ShippingAddress() {
		return m_ShippingAddress;
	}
	public void setM_ShippingAddress(List<ShippingAddress> m_ShippingAddress) {
		this.m_ShippingAddress = m_ShippingAddress;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="VAT_ID")
	public VAT getM_VAT() {
		return m_VAT;
	}
	public void setM_VAT(VAT m_VAT) {
		this.m_VAT = m_VAT;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_STATUS_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}
	
	@Reference(itemCls=BaseEnterpriseInformation.class)
	@OneToMany()
	@JoinColumn(name = "PARENT_ID")
	public List<BaseEnterpriseInformation> getM_ChildEnterpriseInformation() {
		
		return m_ChildEnterpriseInformation;
	}
	public void setM_ChildEnterpriseInformation(
			List<BaseEnterpriseInformation> m_ChildEnterpriseInformation) {
		this.m_ChildEnterpriseInformation = m_ChildEnterpriseInformation;
	}

	@Column(name="PARENT_ID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}