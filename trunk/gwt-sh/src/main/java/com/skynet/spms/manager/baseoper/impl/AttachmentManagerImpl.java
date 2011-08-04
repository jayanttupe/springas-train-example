package com.skynet.spms.manager.baseoper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.baseoper.AttachmentsManager;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.base.Attachment;

@Service
@Transactional
public class AttachmentManagerImpl extends CommonManagerImpl<Attachment> implements
		AttachmentsManager{

	private Logger log=LoggerFactory.getLogger(AttachmentManagerImpl.class);

}
