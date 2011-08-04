package com.skynet.spms.web.control;

import java.util.Calendar;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.PrintUserIdCardService;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.organization.userInformation.IDCard;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterProService;
import com.skynet.spms.service.UUIDGeneral;

@Controller
@GwtRpcEndPoint
public class PrintUserIdCardAction implements PrintUserIdCardService {

	@Autowired
	private PrinterProService printerProService;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UUIDGeneral uUIDGeneral;
	
	private Logger log=LoggerFactory.getLogger(PrintUserIdCardAction.class);

	@Override
	public String print(String userId, String printType)
			throws Exception {
		PrinterEnum printerType = PrinterEnum.valueOf(printType);
		User user = userManager.queryById(userId, User.class);
		if(user == null) return null;
		
		String cardNumber = uUIDGeneral.getPrintSequence();
		String cardInfo = createIdCardInfo(user,cardNumber);
		log.info(cardInfo);
		try {
			Future<String> future = printerProService.doPrint(printerType, cardInfo);
			String result = future.get(120L,TimeUnit.SECONDS);
			if(result.startsWith("1")){
				if(user.getM_IDCard()==null){
					IDCard idCard = new IDCard();
					user.setM_IDCard(idCard);
				}
				user.getM_IDCard().setIdCardNumber(cardNumber);
				Calendar c = Calendar.getInstance();
				c.add(Calendar.YEAR, 1);
				user.getM_IDCard().setExpiryDate(c.getTime());
				userManager.updateUser(user);
				return "success";
			}else{
				return "failure";
			}
		} catch(TimeoutException e){
			return "timeout";
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	private String createIdCardInfo(User user,String cardNumber){
		StringBuilder cardInfo = new StringBuilder();
		cardInfo.append("cardSender\n");
		cardInfo.append("size512bit\n");
		cardInfo.append("156\n");
		cardInfo.append("1\n");
		cardInfo.append("people\n");
		cardInfo.append("operator\n");
		cardInfo.append("jobNumber\n");
		cardInfo.append(cardNumber + "\n");
		cardInfo.append(user.getJobNumber() + "\n");
		cardInfo.append(user.getRealname() + "\n");
		String accessCode = user.getM_UserInformation().getStockAccessCode();
		String[] arr = accessCode.split(",");
		String[] kv = arr[0].split(":");
		cardInfo.append( kv[1]+ "\n");
		cardInfo.append(kv[0] + "\n");

		return cardInfo.toString();
	}
}
