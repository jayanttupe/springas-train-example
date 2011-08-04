package com.skynet.spms.aop.event;

import com.skynet.spms.event.EventEntry;

public class FireEventException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5375535589685616608L;

	private final EventEntry entry;
	
	public FireEventException(EventEntry entry){
		this.entry=entry;
	}
	
	public EventEntry getEventEntry(){
		return entry;
	}
}
