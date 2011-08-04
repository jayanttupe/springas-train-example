package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS;

/**
 * @author 朱江
 * @version 1.0
 * @created 15-三月-2011 12:33:20
 */
public enum TaskState {
	/**
	 * SPMS端新建，open
	 */
	OPN,
	/**
	 * 已下发到RFID PC端,dispatched
	 */
	DSP,
	/**
	 * 手持机已经接收,received
	 */
	REC,
	/**
	 * 手持机正在处理,work in process
	 */
	WIP,
	/**
	 * 任务结束,over
	 */
	OVR,
	/**
	 * 任务取消, cancel
	 */
	CAN,
	/**
	 * 任务关闭，close
	 */
	CLS
}