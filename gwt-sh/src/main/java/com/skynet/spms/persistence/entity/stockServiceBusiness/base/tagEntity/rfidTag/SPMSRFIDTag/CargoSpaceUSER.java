package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag;

/**
 * 采用512bit的标签，每个数据属性以定长方式进行存储。
 * 
 * USER区41位，编码内容由以下业务属性组成（参见货位实体域模型）
 * <ol>
 * 	<li>仓库代码，4位，作用是标识该货位所属的仓库</li>
 * 	<li>所属区域，2位，作用是标识该货位所属的仓库的物理区域</li>
 * 	<li>业务区域，4位，作用是标识该货位所属的仓库的业务区域</li>
 * 	<li>货位编号，13位，作用是标识该货位的排、列、层、格的信息</li>
 * 	<li>货位是否可用，1位，作用是标识该货位是否可用（待定）</li>
 * 	<li>匹配件号，15位，（待定）</li>
 * 	<li>数量单位，2位，（待定）</li>
 * 	<li>最大数量，2位，（待定）</li>
 * 	<li>已用数量，2位，（待定）</li>
 * </ol>
 * @author 曹宏炜
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
public class CargoSpaceUSER {

}