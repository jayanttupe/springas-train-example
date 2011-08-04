package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag;

/**
 * 采用512bit的标签，每个数据属性以定长方式进行存储。
 * 
 * USER区64位，编码内容由以下业务属性组成（参见备件实体域模型）
 * <ol>
 * 	<li>制造商，4位，作用是标识该航材的制造商</li>
 * 	<li>出厂序列号，15位，作用是标识该航材的序号或批号，序号控的记录序号，批号控的记录批号</li>
 * 	<li>库寿日期，8位，作用是标识该航材的库寿日期，格式YYYYMMDD</li>
 * 	<li>当前件号，8位，作用是标识该航材的件号</li>
 * 	<li>数量，2位，作用是标识该航材的数量</li>
 * 	<li>数量单位，2位，作用是标识该航材的计量单位</li>
 * 	<li>货位，13位，作用是标识该航材的货位</li>
 * 	<li>当前业务，4bits，作用是标识该航材的业务类型</li>
 * 	<li>当前状态，4bits，作用是标识该航材的状态，如：可用，不可用，报废等</li>
 * 	<li>库房编号，4位，作用是标识该航材的仓库（移库业务时记录目标库房）</li>
 * </ol>
 * @author 曹宏炜
 * @version 1.0
 * @created 13-六月-2011 11:08:58
 */
public class PartUSER {

}