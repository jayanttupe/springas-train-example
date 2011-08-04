package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.logisticsDocuments.CIQDocuments;
import java.util.List;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceDocumentsType;

/**
 * 1、进出口货物报关单。一般进口货物应填写一式二份；需要由海关核销的货物，如加工贸易货物和保税货物等，应填写专用报关单一式三份；货物出口后需国内退税的，应另填一份
 * 退税专用报关单。
 * 
 * 2、货物发票。要求份数比报关单少一份，对货物出口委托国外销售，结算方式是待货物销售后按实销金额向出口单位结汇的，出口报关时可准予免交。
 * 
 * 3、陆运单、空运单和海运进口的提货单及海运出口的装货单。海关在审单和验货后，在正本货运单上签章放行退还报关贡，凭此提货或装运货物。<可选>
 * 
 * 4、货物装箱单。其份数同发票。但是散装货物或单一品种且包装内容一致的件装货物可免交。
 * 
 * 5、出口收汇核销单。一切出口货物报关时，应交验外汇管理部门加盖&ldquo;监督收汇&rdquo;
 * 章的出口收汇核销单，并将核销编号填在每张出口报关单的右上角处。
 * 
 * 6、根据海关对出口商品的监管条件，还须提供相应证明，如商检证、出口许可证、熏蒸证等。
 * 
 * 7、代理报关委托书
 * 
 * 8、出口货物明细单
 * 
 * 9、海关认为必要时，还应交验贸易合同、货物产地证书等。
 * 
 * 10、其它有关单证。包括：
 * 
 * （1）经海关批准准予减税、免税的货物，应交海关签章的减免税证明，北京地区的外资企业需另交验海关核发的进口设备清单；
 * 
 * （2）已向海关备案的加工贸易合同进出口的货物，应交验海关核发的&ldquo;登记手册&rdquo;。
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:04
 */
public class CIQDocument extends BaseEntity {

	private String documentName;
	private String documentNumber;
	private String registrant;
	private String remarkText;
	public ClearanceDocumentsType m_ClearanceDocumentsType;
	public List m_AuditStatusEntity;
	public List m_DocumentStatusEntity;
	public Attachment m_Attachment;

}