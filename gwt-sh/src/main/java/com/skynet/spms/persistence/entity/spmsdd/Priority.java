package com.skynet.spms.persistence.entity.spmsdd;

/**
 * 是针对工作任务的优先级定义，分为AOG，正常，加急，紧急。其中AOG要求4个小时内响应并准备出货。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:57
 */
public enum Priority {
	/**
	 * 飞机停场紧急模式
	 */
	AOG,
	/**
	 * 正常工作任务模式
	 */
	routine,
	/**
	 * 加急工作任务模式
	 */
	expedite,
	/**
	 * 紧急工作任务模式
	 */
	critical
}