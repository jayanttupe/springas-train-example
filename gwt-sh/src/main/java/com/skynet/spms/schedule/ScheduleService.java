package com.skynet.spms.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.LinkedHashSet;

import java.util.Map;

import java.util.Set;

import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.TaskScheduler;

import org.springframework.scheduling.Trigger;

import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import org.springframework.util.Assert;


@Component
public class ScheduleService  {

	@Autowired
	private TaskScheduler taskScheduler;


	private final Map<String,JobInfo> jobMaps=new HashMap<String,JobInfo>();
	
	
	private class JobInfo {
		
		JobMethodInvokingRunnable runnable;
		
		ScheduledFuture future;
		
		ScheduleTaskInfo jobInfo;
		public JobInfo(JobMethodInvokingRunnable runnable,ScheduledFuture future,ScheduleTaskInfo jobInfo){
			this.runnable=runnable;
			this.future=future;
			this.jobInfo=jobInfo;
		}
	}
	

	
	public void stopTimer() {

		for (JobInfo info:jobMaps.values()) {

			info.future.cancel(true);
		}

	}
	

	public void addTiggerTask(JobMethodInvokingRunnable runnable,
			ScheduleTaskInfo info) {
		
		ScheduledFuture future=this.taskScheduler.schedule(runnable, info.getTrigger());
		
		jobMaps.put(info.getTaskName(), new JobInfo(runnable,future,info));

		
	}
	
	public List<ScheduleTaskInfo> listTask(){
		
		List<ScheduleTaskInfo> jobList=new ArrayList<ScheduleTaskInfo>();
		for(JobInfo info:jobMaps.values()){
			
			ScheduleTaskInfo task=info.jobInfo;
			
			task.setIsRun(info.runnable.isRun());
			
			jobList.add(task);
		}
		return jobList;
	}
	
	public void editTask(ScheduleTaskInfo taskInfo){
		
		Trigger trigger= taskInfo.getTrigger();
		
		JobInfo jobInfo=jobMaps.get(taskInfo.getTaskName());		
		ScheduledFuture future=this.taskScheduler.schedule(jobInfo.runnable,trigger);
		
		JobInfo newJob=new JobInfo(jobInfo.runnable,future,taskInfo);
				
		jobMaps.put(taskInfo.getTaskName(), newJob);
		jobInfo.future.cancel(true);		
		
	}
	
	public ScheduleTaskInfo getTaskInfo(String taskName){
		return jobMaps.get(taskName).jobInfo;
	}
	public void restartTask(String taskName){
	
		jobMaps.get(taskName).runnable.start();
		
	}
	
	public void pauseTask(String taskName){
		jobMaps.get(taskName).runnable.stop();
	}
	
	public void removeTask(String taskName){
		
		JobInfo jobInfo=jobMaps.remove(taskName);
		jobInfo.future.cancel(true);
		
	}


}