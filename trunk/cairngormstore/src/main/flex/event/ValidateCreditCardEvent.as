package event {

	import flash.events.Event;
	import com.adobe.cairngorm.control.CairngormEvent;

	public class ValidateCreditCardEvent extends CairngormEvent {

		public static const EVENT_VALIDATE_CREDIT_CARD:String = "validateCreditCard";

		public var cardholderName:String;

		public var cardNumber:String;

		/**
		 * Constructor.
		 */
		public function ValidateCreditCardEvent() {
			super(EVENT_VALIDATE_CREDIT_CARD);
		}

		/**
		 * Override the inherited clone() method, but don't return any state.
		 */
		override public function clone():Event {
			return new ValidateCreditCardEvent();
		}
	}
}
