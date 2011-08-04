package com.skynet.spms.action.portal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.GetEntityCls;
import com.skynet.spms.opertrack.TrackManager;
import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;

@Component
public class TrackLogAction implements DataSourceAction<GeneralOperateLogEntity>,GetEntityCls{

	@Autowired
	private TrackManager trackManager;
	
	@Override
	public String[] getBindDsName() {
		
		return new String[]{"generalTrackLog"};
	}

	@Override
	public Class getEntityClass() {
		return GeneralOperateLogEntity.class;
	}

	@Override
	public void insert(GeneralOperateLogEntity item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public GeneralOperateLogEntity update(Map<String, Object> newValues,
			String itemID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(String itemID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<GeneralOperateLogEntity> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return trackManager.queryList(startRow,endRow,values);
	}

	@Override
	public List<GeneralOperateLogEntity> getList(int startRow, int endRow) {
		List<GeneralOperateLogEntity> list=trackManager.getList(startRow, endRow);
		
		return list;
	}

}
