package com.skynet.spms.manager.attachment.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.manager.attachment.IAttachmentManager;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.base.Attachment;

@Service
@Transactional
public class AttachmentManager extends CommonManagerImpl<Attachment> implements IAttachmentManager {

/*	@Autowired
	private SessionFactory factory;*/

	public Attachment addAttachment(Attachment o) {
		o.setModifyDate(new Date());
		getSession().save(o);
		return o;
	}

	public void deleteAttachment(String uuid) {
		Attachment o = (Attachment) getSession().load(Attachment.class, uuid);
		getSession().delete(o);
	}

	@SuppressWarnings("unchecked")
	public List<Attachment> getListByBussId(String bID) {
		String hql="select o from Attachment o where o.uuid=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, bID);
		return query.list();
	}

	@Override
	public Attachment updateAttachment(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return super.updateEntity(newValues, itemID,Attachment.class);
	}

}
