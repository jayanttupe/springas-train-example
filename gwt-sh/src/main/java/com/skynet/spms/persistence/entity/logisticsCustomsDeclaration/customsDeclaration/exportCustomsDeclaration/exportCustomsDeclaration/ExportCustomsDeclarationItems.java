package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationPartItem.BaseCustomsPartItem;

/**
 * @author FANYX
 * @version 1.0   出口报关备件项
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_EXPORT_CUS_DEC_ITEMS")
public class ExportCustomsDeclarationItems extends BaseCustomsPartItem{
	
	/**
	 * 原产地
	 */
	private String countryOfOrigin;
	
	public ExportCustomsDeclarationItems() {

	}
	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Column(name="COUNTRY_OF_ORIGIN")
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}
