package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode;

/**
 * 一维条码采用Code 128
 * 每个属性以&ldquo;|&rdquo;分割 ，采用变长方式设计
 * 
 * 备件上一般使用二维码，这里的一维码的设计只是为了留有接口在以后如果有需要使用一维码的情况下，可以使用，现在暂时不使用。
 * 
 * 条码编码内容由以下业务属性组成（参见收料单，验收单域模型）
 * 
 * <ol>
 * 	<li>业务流水号</li>
 * 	<li>客户订单号</li>
 * 	<li>件号</li>
 * 	<li>数量</li>
 * 	<li>单位</li>
 * 	<li>供应商代码</li>
 * 	<li>箱号</li>
 * 	<li>修理订单号</li>
 * 	<li>客户代码</li>
 * 	<li>制造商代码</li>
 * 	<li>部件序列编号</li>
 * 	<li>部件唯一标识</li>
 * 	<li>备件证件电子扫描档案号</li>
 * 	<li>库存编号</li>
 * 	<li>仓库货位号</li>
 * 	<li>入库检验单编号</li>
 * </ol>
 * @author skynet189
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
public class Part1DBarCode extends Base1DBarCode {

}