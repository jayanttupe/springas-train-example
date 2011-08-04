package com.skynet.spms.webservice;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;

public class WebServiceUtils {
	
	public static final XMLGregorianCalendar convertDate(Date objDate){
		try{
			if (objDate == null) {
				return null;
			}
			
			GregorianCalendar cal=(GregorianCalendar) GregorianCalendar.getInstance();
			cal.setTime(objDate);		
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}

	}
	
	public static final Date convertXmlDate(XMLGregorianCalendar objDate){
		try{
			if (objDate == null) {
				return null;
			}
			
			return objDate.toGregorianCalendar().getTime();
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
	}
	
	public static TaskStatus convertXmlTaskStatus(com.skynet.spms.webservice.entity.TaskStatus status) {
		switch(status){
			case WIP:
				return TaskStatus.WIP;
			case OVR:
				return TaskStatus.OVR;
			case CAN:
				return TaskStatus.CAN;
			case OPN:
				return TaskStatus.OPN;
			default:
				throw new IllegalArgumentException("enum not found:"+status.name());
		}
	}

	public static com.skynet.spms.webservice.entity.TaskStatus convertTaskStatus(TaskStatus status) {
		switch(status){
			case WIP:
				return com.skynet.spms.webservice.entity.TaskStatus.WIP;
			case OVR:
				return com.skynet.spms.webservice.entity.TaskStatus.OVR;
			case CAN:
				return com.skynet.spms.webservice.entity.TaskStatus.CAN;
			case OPN:
				return com.skynet.spms.webservice.entity.TaskStatus.OPN;
			default:
				throw new IllegalArgumentException("enum not found:"+status.name());
		}
	}
	
	public static TaskItemStatus convertXmlTaskItemStatus(com.skynet.spms.webservice.entity.TaskItemStatus status) {
		switch(status){
			case WIP:
				return TaskItemStatus.WIP;
			case OVR:
				return TaskItemStatus.OVR;
			case CAN:
				return TaskItemStatus.CAN;
			case OPN:
				return TaskItemStatus.OPN;
			default:
				throw new IllegalArgumentException("enum not found:"+status.name());
		}
	}

	public static com.skynet.spms.webservice.entity.TaskItemStatus convertTaskItemStatus(TaskItemStatus status) {
		switch(status){
			case WIP:
				return com.skynet.spms.webservice.entity.TaskItemStatus.WIP;
			case OVR:
				return com.skynet.spms.webservice.entity.TaskItemStatus.OVR;
			case CAN:
				return com.skynet.spms.webservice.entity.TaskItemStatus.CAN;
			case OPN:
				return com.skynet.spms.webservice.entity.TaskItemStatus.OPN;
			default:
				throw new IllegalArgumentException("enum not found:"+status.name());
		}
	}
	
	public static com.skynet.spms.webservice.entity.TaskType convertTaskType(TaskType objTaskType) {
		if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.SH)) {
			return com.skynet.spms.webservice.entity.TaskType.SHELVING;
		} else if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.PK)) {
			return com.skynet.spms.webservice.entity.TaskType.PICKING;
		} else if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.SC)) {
			return com.skynet.spms.webservice.entity.TaskType.SEND_CARD_TASK;
		} else if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.BX)) {
			return com.skynet.spms.webservice.entity.TaskType.PACKING;
		} else if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.RC)) {
			return com.skynet.spms.webservice.entity.TaskType.COMPLEMENT_CODE;
		} else if (objTaskType.equals(com.skynet.spms.persistence.entity.spmsdd.TaskType.ST)) {
			return com.skynet.spms.webservice.entity.TaskType.STOCK_COUNT;
		}
		
		return null;
	}
	
	/**
	 * 任务类型转换
	 * @param objTaskType
	 * @return
	 */
	public static TaskType convertXmlTaskType(com.skynet.spms.webservice.entity.TaskType objTaskType) {
		if (objTaskType.equals(com.skynet.spms.webservice.entity.TaskType.SHELVING)) {
			return com.skynet.spms.persistence.entity.spmsdd.TaskType.SH;
		} else if (objTaskType.equals(com.skynet.spms.webservice.entity.TaskType.PICKING)) {
			return com.skynet.spms.persistence.entity.spmsdd.TaskType.PK;
		} else if (objTaskType.equals(com.skynet.spms.webservice.entity.TaskType.SEND_CARD_TASK)) {
			return com.skynet.spms.persistence.entity.spmsdd.TaskType.SC;
		} else if (objTaskType.equals(com.skynet.spms.webservice.entity.TaskType.PACKING)) {
			return com.skynet.spms.persistence.entity.spmsdd.TaskType.BX;
		} else if (objTaskType.equals(com.skynet.spms.webservice.entity.TaskType.COMPLEMENT_CODE)) {
			return com.skynet.spms.persistence.entity.spmsdd.TaskType.RC;
		}
		
		return null;
	}
}
