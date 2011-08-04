package com.skynet.spms.persistence.entity.partCatalog.base.basePrice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:20
 */
@Entity
@Table(name="SPMS_ALTERNATE_SUPPLY_LOC")
public class AlternateSupplyLocationText extends BaseEntity {

	//外键引用
	private String priceId;
	//可选地址
	private String location;

	@Column(name="LOCATION")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name="PRICE_ID")
	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	
}