package com.skynet.spms.webservice.service;


import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.GetTaskDetailsInputParameters;
import com.skynet.spms.webservice.entity.GetTaskDetailsOutputParameters;
import com.skynet.spms.webservice.entity.QueryTasklistByTAGInputParameters;
import com.skynet.spms.webservice.entity.QueryTasklistInputParameters;
import com.skynet.spms.webservice.entity.SetTaskDetailsExeInputParameters;
import com.skynet.spms.webservice.entity.TasklistReocrdsOutputParameters;

public interface WsTaskService {

	public TasklistReocrdsOutputParameters getTasklist(
			QueryTasklistInputParameters request) throws FaultResponse;

	public GetTaskDetailsOutputParameters getTaskDetails(
			GetTaskDetailsInputParameters request) throws FaultResponse;
	
    public boolean setTaskDetailsExe(          
            SetTaskDetailsExeInputParameters request
        ) throws FaultResponse;

	public TasklistReocrdsOutputParameters getTaskListByRFIDTag(
			QueryTasklistByTAGInputParameters request)throws FaultResponse;
    
    
}
