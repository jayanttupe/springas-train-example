package com.skynet.spms.schedule;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.util.Assert;

public class ScheduleTaskInfo {
	
	private final String taskName;
	
	private String cronStr;
	
	private long rate;
	
	private TimeUnit unit;
	
	private boolean isRun;	
	

	
	public ScheduleTaskInfo(ScheduledJob jobAnno){
		this.taskName=jobAnno.jobName();
		cronStr=jobAnno.cron();
		
		rate=jobAnno.fixedRate();	
		unit=jobAnno.timeUnit();
				
		Assert.isTrue(StringUtils.isNotBlank(cronStr)||rate>0);
	}
	

	public ScheduleTaskInfo(String taskName) {
		this.taskName=taskName;
	}


	public long getRate() {
		return rate;
	}


	public void setRate(long rate) {
		this.rate = rate;
	}


	public TimeUnit getUnit() {
		return unit;
	}


	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}


	public void setIsRun(boolean done) {
		this.isRun=done;		
	}
	
	public boolean isRun(){
		return isRun;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getCronStr() {
		return cronStr;
	}

	public void setCronStr(String cronStr) {
		this.cronStr = cronStr;
	}

	
	public Trigger getTrigger(){

		if(StringUtils.isNotBlank(cronStr)){
			return new CronTrigger(cronStr);
		}else if(rate!=-1){
			long period=unit.toMillis(rate);
			return new PeriodicTrigger(period);
		}else{
			throw new IllegalArgumentException("unknow Trigger type");
		
		
		}
	}


}
