package com.skynet.spms.web.form;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

public interface CommonViewForm<T extends BaseEntity> {

	public T readPersisten() ;

	public void writePersisten(T user);

}
