//package com.skynet.spms.manager.customerService.RepairService.contract.impl;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.Resource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.skynet.spms.client.vo.repairmodule.MockItem;
//import com.skynet.spms.client.vo.repairmodule.MockRecord;
//import com.skynet.spms.manager.customerService.RepairService.contract.MockManager;
//import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
//import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecord;
//import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecordItem;
//import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecord;
//import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecordItem.RepairQuoteRecordItem;
//import com.skynet.spms.tools.EnumUtil;
//
//@Service
//@Transactional
//public class MockManagerImpl implements MockManager {
//
//	Log log = LogFactory.getLog(MockManagerImpl.class);
//
//	@Resource
//	SessionFactory sessionFactory;
//
//	@Resource
//	EnumUtil enumUtil;
//
//	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//
//	@Override
//	public MockRecord getInspectOutlayRecord(String contractID) {
//
//		MockRecord mockRecord = new MockRecord();
//
//		List<MockItem> items = new ArrayList<MockItem>();
//
//		log.info("The contract's id is:" + contractID);
//
//		String hql = "select o from SupplierSupportInspectOutlayRecord o where o.supplierSupportContractId=? and o.deleted=false ";
//
//		String itemHql = "select o from SupplierSupportInspectOutlayRecordItem o where o.inspectOutlayRecordId=? and o.deleted=false order by o.createDate ";
//
//		Session session = sessionFactory.getCurrentSession();
//
//		Query query = session.createQuery(hql);
//
//		query.setParameter(0, contractID);
//
//		InspectOutlayRecord record = (InspectOutlayRecord) query.uniqueResult();
//
//		if (record != null) {
//			log.info("Hits record.");
//
//			mockRecord.setField1(sdf.format(record.getDetectionDate()));
//
//			mockRecord.setField2(record.getDetectionUnit());
//
//			mockRecord.setField3(record.getInspectionDescription());
//
//			Query itemQuery = session.createQuery(itemHql);
//
//			itemQuery.setParameter(0, record.getId());
//
//			List<InspectOutlayRecordItem> reocrdItems = itemQuery.list();
//
//			for (InspectOutlayRecordItem inspectOutlayRecordItem : reocrdItems) {
//
//				MockItem item = new MockItem();
//
//				item.setAmount(inspectOutlayRecordItem.getAmount());
//
//				InternationalCurrencyCode currency = inspectOutlayRecordItem
//						.getM_InternationalCurrencyCode();
//
//				if (null != currency) {
//					item.setInternationalCurrencyCode(enumUtil
//							.getDisplayNameByValue(
//									InternationalCurrencyCode.class, currency));
//				} else {
//					item.setInternationalCurrencyCode("unknown");
//				}
//
//				item.setItemDescription(inspectOutlayRecordItem
//						.getItemDescription());
//
//				item.setQuantity(inspectOutlayRecordItem.getQuantity());
//
//				item.setRemarkText(inspectOutlayRecordItem.getRemarkText());
//
//				item.setUnitOfPrice(inspectOutlayRecordItem.getUnitOfPrice());
//
//				items.add(item);
//
//			}
//
//			mockRecord.setItems(items);
//
//		}
//
//		return mockRecord;
//	}
//
//	@Override
//	public MockRecord getRepairQutoeRecord(String contractID) {
//		MockRecord mockRecord = new MockRecord();
//
//		List<MockItem> items = new ArrayList<MockItem>();
//
//		log.info("The contract's id is:" + contractID);
//
//		String hql = "select o from RepairQuoteRecord o where o.supplierSupportContractId=? and o.deleted=false ";
//
//		String itemHql = "select o from RepairQuoteRecordItem o where o.repairQuoteRecordId=? and o.deleted=false order by o.createDate ";
//
//		Session session = sessionFactory.getCurrentSession();
//
//		Query query = session.createQuery(hql);
//
//		query.setParameter(0, contractID);
//
//		RepairQuoteRecord record = (RepairQuoteRecord) query.uniqueResult();
//
//		if (record != null) {
//			log.info("Hits record.");
//
//			mockRecord.setField1(sdf.format(record.getDetectionDate()));
//
//			mockRecord.setField2(record.getUsefulLife().toString());
//
//			mockRecord.setField3(record.getRepairQuotedescription());
//
//			Query itemQuery = session.createQuery(itemHql);
//
//			itemQuery.setParameter(0, record.getId());
//
//			List<RepairQuoteRecordItem> reocrdItems = itemQuery.list();
//
//			for (RepairQuoteRecordItem inspectOutlayRecordItem : reocrdItems) {
//
//				MockItem item = new MockItem();
//
//				item.setAmount(inspectOutlayRecordItem.getAmount());
//
//				InternationalCurrencyCode currency = inspectOutlayRecordItem
//						.getM_InternationalCurrencyCode();
//
//				if (null != currency) {
//					item.setInternationalCurrencyCode(enumUtil
//							.getDisplayNameByValue(
//									InternationalCurrencyCode.class, currency));
//				} else {
//					item.setInternationalCurrencyCode("unknown");
//				}
//
//				item.setItemDescription(inspectOutlayRecordItem
//						.getItemDescription());
//
//				item.setQuantity(inspectOutlayRecordItem.getQuantity());
//
//				item.setRemarkText(inspectOutlayRecordItem.getRemarkText());
//
//				item.setUnitOfPrice(inspectOutlayRecordItem.getUnitOfPrice());
//
//				items.add(item);
//
//			}
//
//			mockRecord.setItems(items);
//
//		}
//
//		return mockRecord;
//	}
//
//}
