package com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.leasePrice;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;


/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:26
 */
/*@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_SUPPLIER_LEASE_RPICE")
public class SupplierLeasePrice extends BaseLeasePrice {

}*/