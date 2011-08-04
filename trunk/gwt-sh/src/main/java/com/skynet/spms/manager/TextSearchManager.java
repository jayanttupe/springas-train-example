package com.skynet.spms.manager;

import java.util.concurrent.Future;

import org.hibernate.search.FullTextSession;

public interface TextSearchManager {


	Future<?> doIndexWork(Class<?>[] clsArray);

}
