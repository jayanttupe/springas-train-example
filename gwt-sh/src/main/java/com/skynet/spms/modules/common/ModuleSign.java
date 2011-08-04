package com.skynet.spms.modules.common;

public class ModuleSign {

	private boolean isValid=true;
	
	private boolean isLocal=true;
		
	public ModuleSign clone(){
		ModuleSign sign=new ModuleSign();
		sign.isLocal=false;
		sign.isValid=isValid;
		return sign;
	}
	
	public void setIsValid(boolean sign){
		this.isValid=sign;
	}
	
	public void setIsLocal(boolean isLocal){
		this.isLocal=isLocal;
	}
	
	public boolean isValid(){
		return isValid;
	}
	
	public boolean isLocal(){
		return isLocal;
	}
}
