package business {
import org.springextensions.actionscript.cairngorm.IResponderAware;


public interface ICreditCardDelegate extends IResponderAware {

		function validateCreditCard(cardholderName:String, cardNumber:String):void;

	}
}
