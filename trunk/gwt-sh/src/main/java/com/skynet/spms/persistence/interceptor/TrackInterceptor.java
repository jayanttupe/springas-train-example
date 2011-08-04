package com.skynet.spms.persistence.interceptor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentSet;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.opertrack.TrackInfo;
import com.skynet.spms.opertrack.TrackQueueAdapter;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;

@Component("trackInterceptor")
public class TrackInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 999866849341267940L;

	@Autowired
	private TrackQueueAdapter adapter;

	private Logger log = LoggerFactory.getLogger(TrackInterceptor.class);

	@Override
	public boolean onFlushDirty(Object entity,

	Serializable id,

	Object[] currentState,

	Object[] previousState,

	String[] propertyNames,

	Type[] types) {

		if (!(entity instanceof BaseEntity)) {
			return false;
		}
		BaseEntity statusEntity = BaseEntity.class.cast(entity);

		log.debug(" entity update :" + entity.getClass().getSimpleName());

		TrackInfo info = TrackInfo.getUpdateTrack(statusEntity, id);
		for (int i = 0; i < propertyNames.length; i++) {

			String field = propertyNames[i];

			if ("version".equals(field)) {
				// Integer newVer = ((Integer) prevStatus) + 1;
				// currentState[i] = newVer;
				info.setVersion((Integer) currentState[i]);
			}
			if ("deleted".equals(field) && (Boolean) currentState[i]) {
				log.debug("this record been delete");
				info.setIsDel();
			}

			Object currStatus = currentState[i];
			Object prevStatus = null;
			//TODO:record status .
			if(currStatus instanceof BaseStatusEntity){
				
			}
			if (previousState != null) {
				prevStatus = previousState[i];
			}
			info.addUpdateStateInfo(field, prevStatus, currStatus);
		}
		adapter.addTrackInfo(info);

		return false;

	}

	@Override
	public boolean onLoad(Object entity,

	Serializable id,

	Object[] state,

	String[] propertyNames,

	Type[] types) {

		return false;

	}

	@Override
	public boolean onSave(Object entity,

	Serializable id,

	Object[] state,

	String[] propertyNames,

	Type[] types) {

		if (!(entity instanceof BaseEntity)) {
			return false;
		}
		BaseEntity statusEntity = BaseEntity.class.cast(entity);
		log.debug(" entity insert :" + entity.getClass().getSimpleName());

		TrackInfo info = TrackInfo.getInsertTrack(statusEntity, id);

		for (int i = 0; i < propertyNames.length; i++) {
			String field = propertyNames[i];
			if ("createDate".equals(field)) {
				state[i] = new Date();
			}
			if ("createBy".equals(field)) {
//				String user = getCurrUser();
				state[i] = info.getOperation();
			}
			if ("version".equals(field)) {
				info.setVersion((Integer) state[i]);
			}
			Object currStatus = state[i];
			info.addInsertStateInfo(field, currStatus);
		}
		adapter.addTrackInfo(info);
		return true;
	}

//	private String getCurrUser() {
//		String user=GwtActionHelper.getCurrUser();
//		if(StringUtils.equals(user, "anonymously")){
//			user="System";
//		}
//		return user;
//	}

	@Override
	public void onCollectionRecreate(Object collection, Serializable key)
			throws CallbackException {
//		log.info("collection recreate:" + collection);
//		if (collection instanceof PersistentSet) {
//			PersistentSet persistSet = (PersistentSet) collection;
//			Object owner = persistSet.getOwner();
//			if (!(owner instanceof BaseEntity)) {
//				return;
//			}
//
//			BaseEntity entity = (BaseEntity) owner;
//			log.info("owner is:" + entity.getClass());
//
//			Object[] array = persistSet.toArray();
//			log.info("set size:" + array.length);
//		}
	}

	public void onCollectionRemove(Object collection, Serializable key)
			throws CallbackException {
//		log.info("collection remove:" + collection);
//		if (collection instanceof PersistentSet) {
//			PersistentSet persistSet = (PersistentSet) collection;
//			Object owner = persistSet.getOwner();
//			if (!(owner instanceof BaseEntity)) {
//				return;
//			}
//			BaseEntity entity = (BaseEntity) owner;
//			log.info("owner is:" + entity.getClass());
//
//			Object[] array = persistSet.toArray();
//			log.info("set size:" + array.length);
//		}
	}

	@Override
	public void onCollectionUpdate(Object collection, Serializable key)
			throws CallbackException {
//		log.info("collection update:" + collection);
//		if (collection instanceof PersistentSet) {
//
//			PersistentSet persistSet = (PersistentSet) collection;
//			Object owner = persistSet.getOwner();
//			if (!(owner instanceof BaseEntity)) {
//				return;
//			}
//			BaseEntity entity = (BaseEntity) owner;
//			log.info("owner is:" + entity.getClass());
//
//			Object[] array = persistSet.toArray();
//			log.info("set size:" + array.length);
//		}

	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			adapter.logTrackInfo();
		} else {
			adapter.clearTrackInfo();
		}
	}

}
