package com.skynet.spms.persistence.entity.base.baseEntity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 16:57:21
 */
@MappedSuperclass
public class BaseIDEntity implements Serializable {

	/**
	 * 全局的唯一标识，在hibernate中建议采用uuid的id映射标识。
	 */
	private String id;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid")
	@Column(name="ID", length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}