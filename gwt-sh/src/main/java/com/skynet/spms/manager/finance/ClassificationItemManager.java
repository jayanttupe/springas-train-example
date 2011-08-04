package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.ClassificationItem;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.Subjects;

public interface ClassificationItemManager extends CommonManager<ClassificationItem> {

	public void insertSubjects(Subjects subject);
	
	public List<Subjects> queryAllSubjects();
	
	public Subjects querySubjectsById(String subjectId);
	
	public Subjects querySubjectsByName(String subjectName);
}
