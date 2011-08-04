package com.skynet.spms.jbpm.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.skynet.spms.jbpm.WorkflowEnum;
import com.skynet.spms.jbpm.dao.WfPersistenceParamDao;
import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.jbpm.entity.WfInstParamCols;
import com.skynet.spms.jbpm.forminfo.FormBindHandler;
import com.skynet.spms.jbpm.manager.WfConstants;
import com.skynet.spms.jbpm.manager.WfProcessDeployManager;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;

@Component
public class WfTaskOperService  {


	private Logger log = LoggerFactory.getLogger(WfTaskOperService.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	private ExecutionService execService;

	@Autowired
	private WfProcessDeployManager deployManager;
	
	@Autowired
	WfPersistenceParamDao persistenceParamDao;
	
	private Map<String, ProcessDefinition> defineMap = new HashMap<String, ProcessDefinition>();

	private String basePath = "classpath:com/skynet/spms/jbpm/jpdl";

	@Autowired
	private ResourceLoader loader;
	
	
	@PostConstruct
	public void init() throws IOException {

		File base = loader.getResource(basePath).getFile();

		for (File jpdlFile : base.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {

				return name.endsWith(".jpdl.xml") && (!name.startsWith("."));
			}

		})) {

			try {
				String key = getProcessKey(jpdlFile);
				if (key == null) {
					log.warn("file " + jpdlFile.getAbsolutePath()
							+ " not found process key define.");
					continue;
				}
				ProcessDefinition pd = deployManager.deployProcess(key,
						jpdlFile);

				log.info(String
						.format("Deployed process with ID:%s, Name:%s, Key:%s, Version:%s, Deploy ID:%s ",
								pd.getId(), pd.getName(), pd.getKey(),
								pd.getVersion(), pd.getDeploymentId()));
				defineMap.put(key, pd);
			} catch (IOException e) {
				log.warn("file " + jpdlFile.getAbsolutePath()
						+ " not valid jpdl file.");
			}
		}
		
		

	}

	private Pattern titlePtn = Pattern.compile(
			"^\\s*(<process)[^(key)]*key=\"([^\"]+)", Pattern.MULTILINE);

	private String getProcessKey(File defFile) throws IOException {
		String fullJpdl = FileUtils.readFileToString(defFile, "UTF-8");

		Matcher match = titlePtn.matcher(fullJpdl);
		if (match.find()) {
			return match.group(2);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.jbpm.service.impl.WfTaskOperService#startProcessInstanceWithKeyAndVariables(com.skynet.spms.jbpm.WorkflowEnum, java.lang.String, com.skynet.spms.jbpm.entity.WfInstParamCols)
	 */
	
	public ProcessInstance startProcessInstanceWithKeyAndVariables(
			WorkflowEnum wf, String businessKey, WfInstParamCols paramCols) {
		ProcessDefinition pd = defineMap.get(wf.name());

		if (paramCols == null) {
			paramCols=new WfInstParamCols();
		}
		
		paramCols.addBusinessKey(businessKey);

		ProcessInstance procInst = execService.startProcessInstanceById(
				pd.getId(), paramCols.getInstVarMap(), businessKey);

//		execService.createVariables(procInst.getId(),paramCols.getPersistVarMap(), true);
		
		return procInst;
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.jbpm.service.impl.WfTaskOperService#changeTaskStatus(java.lang.String, int)
	 */
	
	public void changeTaskStatus(String taskID, int status) {
		Task task = taskService.getTask(taskID);

		task.setProgress(status);
		taskService.saveTask(task);
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.jbpm.service.impl.WfTaskOperService#commitTask(java.lang.String, java.lang.String, java.util.Map)
	 */
	
	public void commitTask(String taskID, String outcoming, Map<String, ?> map) {
				
		Task task=taskService.getTask(taskID);
		String busKey=(String) taskService.getVariable(taskID,WfInstParamCols.BUSINESS_KEY);
		
		TaskInfoEntity info=new TaskInfoEntity(task,busKey);
		
		List<WfPersistenceParam> paramList=persistenceParamDao.generWfPersistenParams(task.getFormResourceName(),info);
		for(WfPersistenceParam param:paramList){
			persistenceParamDao.saveOrUpdateParam(param);
		}
		if (StringUtils.isBlank(outcoming)) {
			if (map == null) {
				taskService.completeTask(taskID);
			} else {
				taskService.completeTask(taskID, map);
			}
		} else {
			if (map == null) {
				taskService.completeTask(taskID, outcoming);
			} else {
				taskService.completeTask(taskID, outcoming, map);
			}
		}
	}

	

	
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.jbpm.service.impl.WfTaskOperService#takeTask(java.lang.String, java.lang.String)
	 */
	
	public void takeTask(String taskID, String user) {

		taskService.takeTask(taskID, user);

	}
}
