package business {

	import mx.rpc.remoting.mxml.RemoteObject;


	/**
	 * @version $Revision: $
	 */
	public class CreditCardDelegate extends AbstractBusinessDelegate implements ICreditCardDelegate {

		public function CreditCardDelegate(service:RemoteObject) {
			super(service);
		}

		public function validateCreditCard(cardholderName:String, cardNumber:String):void {
			var call:Object = service.validateCreditCard(cardholderName, cardNumber);
			call.addResponder(responder);
		}
	}
}
