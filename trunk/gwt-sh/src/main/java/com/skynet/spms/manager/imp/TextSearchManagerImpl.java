package com.skynet.spms.manager.imp;

import java.util.concurrent.Future;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.TextSearchManager;

@Service
@Transactional
public class TextSearchManagerImpl implements TextSearchManager{
	
	@Autowired
	private CommonDao commDao;
	
	
	@Override
	public Future<?>  doIndexWork(Class<?>[] clsArray){
		return commDao.getFullTextSession().createIndexer(clsArray).start();
	}
	
}
