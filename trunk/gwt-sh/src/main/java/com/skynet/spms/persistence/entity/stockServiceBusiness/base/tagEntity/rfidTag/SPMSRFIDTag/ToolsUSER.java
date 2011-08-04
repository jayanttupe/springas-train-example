package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag;

/**
 * 采用512bit的标签，每个数据属性以定长方式进行存储。
 * 
 * USER区44位，编码内容由以下业务属性组成
 * <ol>
 * 	<li>库寿日期，8位，作用是标识该工具的库寿日期，格式YYYYMMDD</li>
 * 	<li>当前件号，8位，作用是标识该工具的件号</li>
 * 	<li>数量，2位，作用是标识该工具的数量</li>
 * 	<li>数量单位，2位，作用是标识该工具的计量单位</li>
 * 	<li>货位，13位，作用是标识该工具的货位</li>
 * 	<li>当前业务，4bits，作用是标识该工具的业务类型</li>
 * 	<li>当前状态，4bits，作用是标识该工具的状态，如：可用，不可用，报废等</li>
 * 	<li>库房编号，4位，作用是标识该航材的仓库（待定，货位中已包括库房）</li>
 * </ol>
 * @author 曹宏炜
 * @version 1.0
 * @created 13-六月-2011 11:08:58
 */
public class ToolsUSER {

}