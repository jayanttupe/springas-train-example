package com.skynet.spms.jbpm.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.jbpm.forminfo.FormBindHandler;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;

@Component
public class WfPersistenceParamDao {
	
	@Autowired
	private SessionFactory factory;
	
	private Map<String,FormBindHandler> bindMap;
	
	@Autowired
	private ApplicationContext context;
	
	@PostConstruct
	public void initFormBindMap(){
		Map<String,FormBindHandler> map=new HashMap<String,FormBindHandler>();
		
		for(String name:context.getBeanNamesForType(FormBindHandler.class)){
			FormBindHandler handler=context.getBean(name, FormBindHandler.class);
			
			map.put(handler.getBindForm(), handler);
		}
		
		bindMap=Collections.unmodifiableMap(map);
	}

		
	private Session getSession(){
		return factory.getCurrentSession();
	}
	public void saveOrUpdateParam(WfPersistenceParam param){
		
		WfPersistenceParam instParam=(WfPersistenceParam) getSession().get(WfPersistenceParam.class, param.getWfParamID());
		if(instParam==null){
			getSession().save(param);
		}else{
			instParam.updateParam(param);
		}
		getSession().flush();

	}
	
	public List<WfPersistenceParam> getParamsByProcInstID(String procInstID,
			String taskID) {
		@SuppressWarnings("unchecked")
		List<WfPersistenceParam> list= getSession().createQuery("from WfPersistenceParam param where " +
			" param.wfParamID.taskID=:taskID ")
			.setParameter("taskID",taskID)
			.list();
				
		return list;
	}
	
	public List<WfPersistenceParam> generWfPersistenParams(String formNames,TaskInfoEntity taskInfo) {

		if (StringUtils.isBlank(formNames)) {
			return new ArrayList<WfPersistenceParam>();
		}

		String[] formArray = StringUtils.split(formNames, ",");
		formArray = StringUtils.stripAll(formArray);

		List<WfPersistenceParam> list=new ArrayList<WfPersistenceParam>();
		for (String form : formArray) {
			FormBindHandler handler = bindMap.get(form);
			WfPersistenceParam wfParam = handler.getBindByTaskInfo(taskInfo);			
			list.add(wfParam);
		}
		return list;
	}
	
	

}
