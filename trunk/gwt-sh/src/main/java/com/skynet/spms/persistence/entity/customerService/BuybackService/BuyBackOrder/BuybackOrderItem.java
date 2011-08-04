package com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackOrder;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
/**
 * 回购 提货指令明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_BUYBACKORDERITEM")
public class BuybackOrderItem extends BaseApplicationForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 432378269378094910L;

}
