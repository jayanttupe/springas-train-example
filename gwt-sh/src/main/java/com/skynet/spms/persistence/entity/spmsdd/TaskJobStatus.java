package com.skynet.spms.persistence.entity.spmsdd;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:11
 */
public enum TaskJobStatus {
	accepted,
	/**
	 * 分派任务给其他操作人员
	 */
	dispatched,
	difficulty,
	excuting,
	cancled,
	fulfill
}