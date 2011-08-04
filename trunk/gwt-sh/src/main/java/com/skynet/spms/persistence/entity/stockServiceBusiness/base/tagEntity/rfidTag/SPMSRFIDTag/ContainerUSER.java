package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag.SPMSRFIDTag;

/**
 * 采用512bit的标签，每个数据属性以定长方式进行存储。
 * 
 * USER区5位，编码内容由以下业务属性组成（参见容器实体域模型）
 * <ol>
 * 	<li>材质，1位，作用是标识该容器的材质，如：木制，金属，塑料</li>
 * 	<li>库房编号，4位，作用是标识该容器所属的仓库</li>
 * </ol>
 * @author 朱江
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
public class ContainerUSER {

}