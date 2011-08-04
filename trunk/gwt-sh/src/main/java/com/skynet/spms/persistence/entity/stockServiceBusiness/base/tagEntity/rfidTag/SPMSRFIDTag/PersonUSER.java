package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag;

/**
 * 采用512bit的标签，每个数据属性以定长方式进行存储。
 * 
 * USER区54位，编码内容由以下业务属性组成（参见用户信息域模型）
 * <ol>
 * 	<li>工号，10位，作用是标识该人员的工号</li>
 * 	<li>姓名，20位，作用是标识该人员的姓名</li>
 * 	<li>权限组，20位，作用是标识该人员所属的权限组，据此确认其可进行的操作</li>
 * 	<li>库房编号，4位，作用是标识该人员所属的仓库</li>
 * </ol>
 * @author 曹宏炜
 * @version 1.0
 * @created 13-六月-2011 11:08:58
 */
public class PersonUSER {

}