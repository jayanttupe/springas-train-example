package com.skynet.spms.manager.base;

import java.util.List;

import com.skynet.spms.persistence.entity.base.remarkEntity.RemarkTextEntity;

public interface RemarkManager {

	 public void addRemarkForBusiness(RemarkTextEntity entity);

	 public List<RemarkTextEntity> getRemarksForBusiness(String businessKey, String tableName);

}