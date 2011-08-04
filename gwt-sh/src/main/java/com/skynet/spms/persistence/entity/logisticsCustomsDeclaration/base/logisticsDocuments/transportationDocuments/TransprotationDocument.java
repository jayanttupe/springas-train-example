package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.logisticsDocuments.transportationDocuments;
import java.util.List;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.TransportDocumentsType;

/**
 * 运输单证是指在物流过程中，与运代商发生的运输相关的各种来回沟通的单证，可以通过扫描或者电子件的方式附件在运输单证中。在录入单证的时候需要指定单证的类型，单证的名
 * 称，单证的编号，并且确认单证的登记人姓名信息。
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:09
 */
public class TransprotationDocument extends BaseEntity {

	private String documentName;
	private String documentNumber;
	private String registrant;
	private String remarkText;
	public TransportDocumentsType m_TransportDocumentsType;
	public List m_AuditStatusEntity;
	public List m_DocumentStatusEntity;
	public Attachment m_Attachment;

}