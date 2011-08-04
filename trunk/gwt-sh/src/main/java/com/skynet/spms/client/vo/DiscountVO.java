package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 折扣VO
 * @author fl
 *
 */
public class DiscountVO  implements IsSerializable {
	private float m_DiscountPercentCode;

	public float getM_DiscountPercentCode() {
		return m_DiscountPercentCode;
	}

	public void setM_DiscountPercentCode(float m_DiscountPercentCode) {
		this.m_DiscountPercentCode = m_DiscountPercentCode;
	}
	
}
