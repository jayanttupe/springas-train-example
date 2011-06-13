package business {


	public interface ICreditCardDelegate extends IResponderAware {

		function validateCreditCard(cardholderName:String, cardNumber:String):void;

	}
}
