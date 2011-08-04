package com.skynet.spms.persistence.entity.base.baseCodeEntity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-三月-2011 12:47:18
 */
@Entity
@Table(name="SPMS_BASE_CODE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="DISCRIMINATOR",
		discriminatorType=DiscriminatorType.STRING,
		length=50)
public abstract class BaseCodeEntity extends BaseIDEntity {

	private String code;

	@Column(name="CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}