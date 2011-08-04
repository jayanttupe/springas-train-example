package com.skynet.spms.persistence.entity.csdd.c;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * Commercial And Government Entity Code 商业及政府实体代码，用于唯一标识商业企业和政府的标识代码。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:38
 */
@Entity
@Table(name="SPMS_CAGE_CODE")
public class CAGECode extends BaseIDEntity {
	
	private String code;
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}