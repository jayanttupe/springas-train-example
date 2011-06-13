package command {

	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;

import event.ValidateCreditCardEvent;

import model.ShopModelLocator;

import mx.collections.ArrayCollection;
	import mx.events.ValidationResultEvent;
	import mx.validators.Validator;

import view.checkout.PaymentInformationModel;

/**
	 * @version $Revision: $
	 */
	public class ValidateOrderCommand extends SequenceCommand {

		public function ValidateOrderCommand() {
		}

		public override function execute(event:CairngormEvent):void {
			var model:ShopModelLocator = ShopModelLocator.getInstance();

			if (model.shoppingCart.getElements().length == 0) {
				ShopModelLocator.getInstance().cartEmpty = true;
			} else {
				if (validateCheckOutForm()) {
					executeNextCommand();
				} else {
					ShopModelLocator.getInstance().formIncomplete = true;
				}
			}
		}

		public override function executeNextCommand():void {
			// Create the "next" event.
			var cardDetails:PaymentInformationModel = ShopModelLocator.getInstance().paymentInfo;

			var cardEvent:ValidateCreditCardEvent = new ValidateCreditCardEvent();
			cardEvent.cardholderName = cardDetails.cardHolder;
			cardEvent.cardNumber = cardDetails.cardNumber;

			// Dispatch the event.
			nextEvent = cardEvent;

			super.executeNextCommand();

			// Clear the event.
			nextEvent = null;
		}

		public function validateCheckOutForm():Boolean {
			var generalInfoValid:Boolean = validate(ShopModelLocator.getInstance().generalInfoValidators);

			var paymentInfoValid:Boolean = validate(ShopModelLocator.getInstance().paymentValidators);

			return generalInfoValid && paymentInfoValid;
		}

		private function validate(validators:ArrayCollection):Boolean {
			var valid:Boolean = true;

			for (var i:uint = 0; i < validators.length; i++) {
				var validator:Validator = Validator(validators[i]);
				var validationResult:ValidationResultEvent = validator.validate();

				if (validationResult.type == ValidationResultEvent.INVALID) {
					valid = false;

						// We don't break so all the fields can validate.
				}
			}

			return valid;
		}
	}
}
