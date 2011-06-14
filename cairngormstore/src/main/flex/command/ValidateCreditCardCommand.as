package command {
import business.ICreditCardDelegate;

import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.samples.store.business.ICreditCardDelegate;
	import com.adobe.cairngorm.samples.store.event.PurchaseCompleteEvent;
	import com.adobe.cairngorm.samples.store.event.ValidateCreditCardEvent;
	import com.adobe.cairngorm.samples.store.model.ShopModelLocator;

import event.PurchaseCompleteEvent;
import event.ValidateCreditCardEvent;

import modelpkg.ShopModelLocator;

import mx.rpc.IResponder;

	import org.as3commons.lang.Assert;


	/**
	 * @version $Revision: $
	 */
	public class ValidateCreditCardCommand extends SequenceCommand implements IResponder {

		private var _delegate:ICreditCardDelegate;

		public function ValidateCreditCardCommand(delegate:ICreditCardDelegate) {
			Assert.notNull(delegate, "The credit card delegate must not be null");
			_delegate = delegate;
		}

		public override function execute(event:CairngormEvent):void {
			var cardEvent:ValidateCreditCardEvent = ValidateCreditCardEvent(event);
			var cardholderName:String = cardEvent.cardholderName;
			var cardNumber:String = cardEvent.cardNumber;

			_delegate.responder = this;
			_delegate.validateCreditCard(cardholderName, cardNumber);
		}

		public function result(event:Object):void {
			var validationPassed:Boolean = (event.result == true);
			ShopModelLocator.getInstance().creditCardInvalid = false;
			if (validationPassed) {
				executeNextCommand();
			} else {
				ShopModelLocator.getInstance().creditCardInvalid = true;
			}
		}

		public function fault(event:Object):void {
		}

		public override function executeNextCommand():void {
			// Create the "next" event.
			var purchaseEvent:PurchaseCompleteEvent = new PurchaseCompleteEvent();

			purchaseEvent.generalInformation = ShopModelLocator.getInstance().generalInfo;

			purchaseEvent.paymentInformation = ShopModelLocator.getInstance().paymentInfo;

			purchaseEvent.shoppingCart = ShopModelLocator.getInstance().shoppingCart;

			// Dispatch the event.
			nextEvent = purchaseEvent;

			super.executeNextCommand();

			// Clear the event.
			nextEvent = null;
		}
	}
}
