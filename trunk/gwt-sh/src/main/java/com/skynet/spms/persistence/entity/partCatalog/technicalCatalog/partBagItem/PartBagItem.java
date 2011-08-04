package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.partBagItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * @author 山云峰
 * @version 1.0
 * @param <ItemPartIndex>
 * @created 25-七月-2011 14:18:06
 */

@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_BAG_ITEM")

public class PartBagItem extends BaseEntity{

	private PartIndex itemPartIndex;
	private int requireCount;
	
	private String partIndexId;
	
	@Column(name = "PART_INDEX_ID")
	public String getPartIndexId() {
		return partIndexId;
	}
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}


	@ManyToOne()
	@JoinColumn(name = "ITEM_PART_INDEX_ID")
	public PartIndex getItemPartIndex() {
		return itemPartIndex;
	}
	public void setItemPartIndex(PartIndex itemPartIndex) {
		this.itemPartIndex = itemPartIndex;
	}
	
	@Column(name="REQUIRE_COUNT")
	public int getRequireCount() {
		return requireCount;
	}
	public void setRequireCount(int requireCount) {
		this.requireCount = requireCount;
	}
	
}