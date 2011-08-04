package com.skynet.spms.opertrack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;

@Service
public class TrackQueueAdapter {

	private Logger log = LoggerFactory.getLogger(TrackQueueAdapter.class);

	@Autowired
	private TrackManager trackManager;
	
	private ThreadLocal<BlockingQueue<TrackInfo>> threadLocale=new ThreadLocal<BlockingQueue<TrackInfo>>(){
		 protected BlockingQueue<TrackInfo> initialValue(){
			return new LinkedBlockingQueue<TrackInfo>();
		}
	};

	public void addTrackInfo(TrackInfo info) {
	
		BlockingQueue<TrackInfo> infoQueue=threadLocale.get();
		infoQueue.add(info);
	}
		
	public void clearTrackInfo(){
		threadLocale.remove();
	}

	
	public void logTrackInfo() {
		BlockingQueue<TrackInfo> infoQueue=threadLocale.get();
		if(infoQueue.isEmpty()){
			log.info("current session no bind track queue");
			return;
		}
		log.info("record commit track info:"+infoQueue.size());
		
		List<TrackInfo> list=new ArrayList<TrackInfo>();
		for(TrackInfo info:infoQueue){
			list.add(info);
		}
		trackManager.insertTrackRecord(list);			
		clearTrackInfo();	

	}
}
