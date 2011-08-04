package com.skynet.spms.client.entity;

public enum TaskStatus {
	
	Receive(10),Edit(20),Save(30);
	
	private int val;
	
	TaskStatus(int i){
		val=i;
	}

	public int getStatus(){
		return val;
	}
}
