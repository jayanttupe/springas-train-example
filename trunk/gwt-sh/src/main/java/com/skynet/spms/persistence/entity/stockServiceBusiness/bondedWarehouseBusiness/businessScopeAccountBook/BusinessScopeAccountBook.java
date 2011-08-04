package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-6
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BUSINESS_SCOPE_ACCOUNT")
public class BusinessScopeAccountBook extends BaseEntity {
	
	/**
	 * 电子账册项号
	 */
	private String accountBookItemsNumber;

	/**
	 * 描述
	 */
	private String describe;
	
	/**
	 * 电子账册明细
	 */
	private List<BusinessScopeAccountBookItems> businessScopeAccountBookItems;

	public BusinessScopeAccountBook() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "ACCOUNT_BOOK_ITEMS_NUMBER")
	public String getAccountBookItemsNumber() {
		return accountBookItemsNumber;
	}

	public void setAccountBookItemsNumber(String accountBookItemsNumber) {
		this.accountBookItemsNumber = accountBookItemsNumber;
	}

	@Column(name = "DESCRIBE")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@OneToMany(targetEntity= BusinessScopeAccountBookItems.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="BUSINESS_SCOPE_ID")
	public List<BusinessScopeAccountBookItems> getBusinessScopeAccountBookItems() {
		return businessScopeAccountBookItems;
	}

	public void setBusinessScopeAccountBookItems(
			List<BusinessScopeAccountBookItems> businessScopeAccountBookItems) {
		this.businessScopeAccountBookItems = businessScopeAccountBookItems;
	}

}