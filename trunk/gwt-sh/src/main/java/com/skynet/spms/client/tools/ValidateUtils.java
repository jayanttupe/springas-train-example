package com.skynet.spms.client.tools;



import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.constants.MessageConstants;
import com.smartgwt.client.widgets.form.validator.ContainsValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.form.validator.IsFloatValidator;
import com.smartgwt.client.widgets.form.validator.IsIntegerValidator;
import com.smartgwt.client.widgets.form.validator.IsStringValidator;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MaskValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.form.validator.Validator;

public class ValidateUtils {

	private static MessageConstants messageConstants;
	
	static{
		messageConstants = GWT.create(MessageConstants.class);
	}
	//验证邮箱
	public static Validator emailValidator(String email){
		 RegExpValidator emailValidator = new RegExpValidator();    
         emailValidator.setErrorMessage(messageConstants.emailError());    
		 emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");    
		 return emailValidator;		
	}
	//验证确认密码
	public static Validator pwdConfirmValidator(String pwdConfirm){
		 MatchesFieldValidator matchesValidator = new MatchesFieldValidator(); 
		 matchesValidator.setOtherField("password");
		 matchesValidator.setErrorMessage(messageConstants.pwdConfirmError());
		 return matchesValidator;
	}
	
	//验证电话号码
	public static Validator telValidator(String telephone){
		MaskValidator  maskValidator = new MaskValidator ();
		maskValidator.setErrorMessage(messageConstants.telephoneError());
		maskValidator.setMask("^[0-9- \\*#]{3,20}$");
		return maskValidator;
		
	}
	//验证传真号码
	public static Validator faxValidator(String fax){
		MaskValidator faxValidator = new MaskValidator();
		faxValidator.setErrorMessage(messageConstants.faxError());
		//^([0-9]{2}-?)?0[0-9]{2}-[0-9]{8}$
		faxValidator.setMask("^[0-9- \\*#]{3,20}$");
		return faxValidator; 

		

	}
	
	//验证手机号码
	public static Validator mobileValidator(String mobile){
		MaskValidator maskValidator = new MaskValidator();
		maskValidator.setErrorMessage(messageConstants.mobileError());
		//^\\s*(1?)\\s*\\(?\\s*(\\d{3})\\s*\\)?\\s*-?\\s*(\\d{3})\\s*-?\\s*(\\d{4})\\s*$
		maskValidator.setMask("^[0-9]{11}$");
		//maskValidator.setTransformTo("$1($2) $3 - $4"); 
		return maskValidator;
		
	}
	
	//验证字符串长度范围
	public static Validator StringLenValidator(int startLen,int endLen){
		LengthRangeValidator lengthValidator = new LengthRangeValidator(); 
		if(startLen == endLen)
		{
			lengthValidator.setErrorMessage(messageConstants.stringLenError(startLen));
		}
		else{
			lengthValidator.setErrorMessage(messageConstants.stringLenRangeError(startLen,endLen));
		}
		lengthValidator.setMin(startLen);
		lengthValidator.setMax(endLen);
		 return lengthValidator;			
	}
	
	
	
	//验证小数范围
	public static Validator decimalRangeValidator(float minValue,float maxValue){
         FloatRangeValidator floatRangeValidator = new FloatRangeValidator();
         floatRangeValidator.setErrorMessage(ConstantsUtil.messageConstants.floatRangeError(minValue, maxValue));
         floatRangeValidator.setMin(minValue);
         floatRangeValidator.setMax(maxValue);
		 return floatRangeValidator;			
	}
	
	//验证是否纯字符串
	public static Validator isCharValidator(){
        IsStringValidator stringValidator = new IsStringValidator();
        stringValidator.setErrorMessage(messageConstants.stringError());
		 return stringValidator;			
	}
	
	
	
	//验证是否纯整数
	public static Validator isIntValidator(){
        IsIntegerValidator intValidator = new IsIntegerValidator();
        intValidator.setErrorMessage(messageConstants.intError());
		 return intValidator;			
	}
	
	
	
	//验证是否小数
	public static Validator isFloatValidator(){
        IsFloatValidator floatValidator = new IsFloatValidator();
        floatValidator.setErrorMessage(messageConstants.floatError());
		 return floatValidator;			
	}
	
	//验证是否字符串中的字符是否有效
	public static Validator containStringValidator(String containString){
        ContainsValidator containsValidator = new ContainsValidator();
        containsValidator.setSubstring(containString);
        containsValidator.setErrorMessage(messageConstants.containStringError());
		 return containsValidator;			
	}
	
	//验证整数范围
	public static Validator IntRangeValidator(int minValue,int maxValue){
		IntegerRangeValidator integerRangeValidator = new IntegerRangeValidator();
		integerRangeValidator.setErrorMessage(ConstantsUtil.messageConstants.intRangeError(minValue, maxValue));
		integerRangeValidator.setMin(minValue);
		integerRangeValidator.setMax(maxValue);
         
		return integerRangeValidator;			
	}
	
	//验证邮编
	public static Validator ZipCodeValidator(String zipCode){
		 RegExpValidator zipCodeValidator = new RegExpValidator();    
		 zipCodeValidator.setErrorMessage(messageConstants.zipCodeError()); 		 
     	 zipCodeValidator.setExpression("^[0-9]{5,6}(-[0-9]{5})?$");
		 return zipCodeValidator;		
	}
	
	//验证网址是否合法
	public static Validator urlValidator(String url){
		 RegExpValidator urlValidator = new RegExpValidator();    
		 urlValidator.setErrorMessage(messageConstants.urlError()); 
	     urlValidator.setExpression("(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?");
		 return urlValidator;		
	}

	//验证一位大写字母
	public static Validator capitalValidator(String capital){
		 RegExpValidator capitalValidator = new RegExpValidator();    
		 capitalValidator.setErrorMessage(messageConstants.capitalError());    
		 capitalValidator.setExpression("^[A-Z]$");    
		 return capitalValidator;		
	}
	
	//验证逻辑库代码
	public static Validator logicValidator(String logic){
		 RegExpValidator logicValidator = new RegExpValidator();    
		 logicValidator.setErrorMessage(messageConstants.logicError());    
		 logicValidator.setExpression("^([a-zA-Z0-9\\-]|[a-zA-Z0-9])+$");    
		 return logicValidator;		
	}

}
