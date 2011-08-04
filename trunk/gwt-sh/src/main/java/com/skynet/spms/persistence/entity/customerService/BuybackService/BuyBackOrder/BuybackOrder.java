package com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackOrder;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
/**
 * 回购 提货指令
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_BUYBACKORDER")
public class BuybackOrder extends BaseApplicationForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4778333703456709451L;

}
