package com.skynet.spms.client.entity;


public class CommonApproveParam implements WfParams{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8955679146829021079L;

	private float amount;
	
	private String businessUser;
	
	private String[] managerArray=new String[3];
	
	private String businessType;
	
	
	public CommonApproveParam(){
		managerArray[0]="FinancialSupervisor";
		managerArray[1]="BusinessActor";
		managerArray[2]="AirQualityHeader";
	}
			
	public void setManagerForType(String mang){
//		this.managerArray[1]=mang;
	}
	
	public void setManagerArray(String[] array){
		this.managerArray=array;
	}
	
	public String[] getManagerArray(){
		return managerArray;
	}

	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getBusinessUser() {
		return businessUser;
	}

	public void setBusinessUser(String businessUser) {
		this.businessUser = businessUser;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public int getManagerNumber(){
		return managerArray.length;
	}
	
	@Override
	public String toString(){
		return "amount:"+amount+" type:"+businessType+" pk:";
	}
}
