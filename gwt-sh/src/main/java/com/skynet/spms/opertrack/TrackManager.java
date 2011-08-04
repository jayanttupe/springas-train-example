package com.skynet.spms.opertrack;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;



public interface TrackManager {

	void insertTrackRecord(List<TrackInfo> info);

	List<GeneralOperateLogEntity> getList(int startRow, int endRow);

	List<GeneralOperateLogEntity> queryList(int startRow, int endRow,
			Map<String, Object> values);
	
	List<GeneralOperateLogEntity> queryByEntity(String entityName,String pk,int startRow,int endRow);
	
	List<PropertyValue> queryFieldTrack(String fieldName,String entityName,String pk,int startRow,int endRow);
	

}