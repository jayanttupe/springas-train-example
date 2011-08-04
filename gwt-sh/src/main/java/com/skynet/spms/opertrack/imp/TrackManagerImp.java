package com.skynet.spms.opertrack.imp;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Embeddable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.ComponentType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.datasource.QueryGeneral;
import com.skynet.spms.opertrack.PropertyValue;
import com.skynet.spms.opertrack.TrackInfo;
import com.skynet.spms.opertrack.TrackManager;
import com.skynet.spms.opertrack.ValueInfo;
import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TrackManagerImp implements TrackManager {


	private Logger log = LoggerFactory.getLogger(TrackManagerImp.class);

//	 private SessionFactory sessionFactory;
	
	@Autowired
	private QueryGeneral queryGeneral;

	@Autowired
	private ApplicationContext context;

	private Session getSession() {
			
		return getSessionFactory().getCurrentSession();

	}
	
	private SessionFactory getSessionFactory() {
		
		return context.getBean(SessionFactory.class);
	}
	
	
	private ClassMetadata getMetaData(Class cls){
		return getSessionFactory().getClassMetadata(cls);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.skynet.spms.opertrack.imp.TrackManager#insertTrackRecord(com.skynet
	 * .spms.opertrack.TrackInfo)
	 */
	@Async
	@Override
	public void insertTrackRecord(List<TrackInfo> infoList) {
		log.info("save track info,size:"+infoList.size());
		for(TrackInfo info:infoList){
			
			getSession().save(getStatusEntity(info));
		}
		infoList.clear();
	}
	
	private GeneralOperateLogEntity getStatusEntity(TrackInfo info) {
		
		Document doc=DocumentHelper.createDocument();
		
		Element rootElem=doc.addElement("entityvalue");
		
		Element fieldsElem=rootElem.addElement("fields");
		
		fillElement(fieldsElem,info);
		
		StringWriter writer=new StringWriter();
		XMLWriter output=new XMLWriter(writer);
		
		try{
			output.write(doc);
		}catch(Exception e){
			log.error("xml general fail");
		}
		
		String desc= writer.toString();
		GeneralOperateLogEntity logEntity=info.getLogEntity();
		logEntity.setActionDescription(desc);
		
		return logEntity;
	}

	


	@Override
	@SuppressWarnings("unchecked")
	public List<GeneralOperateLogEntity> getList(int startRow,int endRow){
		
		Date lastMonth=DateUtils.addMonths(new Date(), -1);
		List<GeneralOperateLogEntity> list= getSession().createQuery(
				"from GeneralOperateLogEntity where actionDate > :date" +
				" order by actionDate desc")
		.setDate("date", lastMonth)
		.setMaxResults(endRow)
		.list();
		
		return list.subList(startRow,list.size());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralOperateLogEntity> queryList(int startRow, int endRow,
			Map<String, Object> values) {
		
		Criterion criteria=queryGeneral.generQueryHQL(values,GeneralOperateLogEntity.class);
		
		List<GeneralOperateLogEntity> list=getSession()
			.createCriteria(GeneralOperateLogEntity.class)
			.add(criteria)
			.addOrder(Order.desc("actionDate"))
			.setMaxResults(endRow).list();
				
		return list.subList(startRow,list.size());
	}
	

	@Override
	public List<GeneralOperateLogEntity> queryByEntity(String entityName,
			String pk, int startRow, int endRow) {
		
		return getSession().createQuery("from GeneralOperateLogEntity  " +
				"where recordName=:name and recordId=:id order by actionDate desc ")
				.setString("name", entityName)
				.setString("id", pk)
				.list();
		
		
	}
	
	private static final String XPATH="//field[@name='${0}']";

	@Override
	public List<PropertyValue> queryFieldTrack(String fieldName,
			String entityName, String pk, int startRow, int endRow) {
		List<GeneralOperateLogEntity> operList=getSession().createQuery("from GeneralOperateLogEntity  " +
		"where recordName=:name and recordId=:id order by actionDate desc ")
		.setString("name", entityName)
		.setString("id", pk)
		.list();
		
		List<PropertyValue> propList=new ArrayList<PropertyValue>();
		int idx=0;
		for(GeneralOperateLogEntity entity:operList){
			String dom=entity.getActionDescription();
			if(!dom.contains("xml")){
				continue;
			}
			try{
				Document doc=DocumentHelper.parseText(entity.getActionDescription());
				
				String xpath=SimpleTmpTool.generByTmp(XPATH, fieldName);
				
				Element elem=(Element) doc.selectSingleNode(xpath);
				
				if(elem==null){
					continue;
				}
				
				PropertyValue val=new PropertyValue(elem);
				val.setModifyDate(entity.getActionDate());
				val.setId(idx);
				propList.add(val);
				idx++;
			}catch(Exception e){
				log.error("format fail",e);
			}
		}
		
		

		return propList;
	}
	
	private void fillElement(Element rootElem,TrackInfo info){
		
		
		
		ClassMetadata metaData=getMetaData(info.getEntityCls());
		for(ValueInfo valInfo:info.getValSet()){
			if(valInfo.isEqualsObj()){
				continue;
			}

			Element fieldElem=rootElem.addElement("field");
			
			String fieldName=valInfo.getFieldName();
			Type type=metaData.getPropertyType(fieldName);
			
			Class cls=type.getReturnedClass();
						
			if(cls.isAnnotationPresent(Embeddable.class)){
				fillEmbedField(rootElem,(ComponentType)type,null,valInfo);
			}else{
				fillSimpField(fieldElem,type,valInfo);
			}			
		}

		return;
	}
	
	private void fillEmbedField(Element rootElem,ComponentType compType,String fieldPrefix,ValueInfo info){
		
		
		for(String name:compType.getPropertyNames()){
			
			Type type=compType.getSubtypes()[compType.getPropertyIndex(name)];
			
			Class cls=type.getReturnedClass();
			
			String fieldName=name;
			if(StringUtils.isNotBlank(fieldPrefix)){
				fieldName=fieldPrefix+"."+name;
			}
			
			if(info.isEqualsObj(fieldName)){
				continue;
			}
			
			if(cls.isAnnotationPresent(Embeddable.class)){
				fillEmbedField(rootElem,(ComponentType)type,fieldName,info);
			}
			
			String fullFieldName=info.getFieldName()+"."+name;
			Element fieldElem=rootElem.addElement("field");
			fieldElem.addAttribute("name", fullFieldName);
			fieldElem.addAttribute("type", type.getName());
			if(!info.isInsert()){
				fieldElem.addElement("old").addText(info.getPreviousProp(fieldName));
				fieldElem.addElement("new").addText(info.getCurrentProp(fieldName));
			}else{
				fieldElem.addElement("new").addText(info.getCurrentProp(fieldName));
			}
						
			
		}	
		
	}
	
	
	
	private void fillSimpField(Element fieldElem,Type type,ValueInfo info){
		
		fieldElem.addAttribute("name", info.getFieldName());
		fieldElem.addAttribute("type", type.getName());
		
		if(!info.isInsert()){
			fieldElem.addElement("old").addText(info.getPrevious());
			fieldElem.addElement("new").addText(info.getCurrent());
		}else{
			fieldElem.addElement("new").addText(info.getCurrent());
		}		
		
	}

	

}
