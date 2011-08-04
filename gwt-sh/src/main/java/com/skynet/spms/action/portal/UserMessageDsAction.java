package com.skynet.spms.action.portal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.GetEntityCls;
import com.skynet.spms.manager.portal.UserMsgRecordManager;
import com.skynet.spms.persistence.entity.organization.userInformation.PersonalCalendar;
import com.skynet.spms.persistence.entity.organization.userInformation.UserMessageRecord;

@Component
public class UserMessageDsAction implements DataSourceAction<UserMessageRecord>,GetEntityCls{


	@Autowired
	private UserMsgRecordManager mang;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"userMessage"};
	}

	@Override
	public Class getEntityClass() {
		return UserMessageRecord.class;
	}

	@Override
	public void insert(UserMessageRecord item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserMessageRecord update(Map<String, Object> newValues, String itemID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(String itemID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<UserMessageRecord> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<UserMessageRecord> getList(int startRow, int endRow) {
		return mang.getUserMessages(GwtActionHelper.getCurrUser(), startRow, endRow);
	}

}
