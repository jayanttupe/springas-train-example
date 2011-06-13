package event {
import com.adobe.cairngorm.control.CairngormEvent;

import flash.events.Event;

import model.ShoppingCart;

import view.checkout.GeneralInformationModel;
import view.checkout.PaymentInformationModel;

public class PurchaseCompleteEvent extends CairngormEvent {
    public static var EVENT_COMPLETE_PURCHASE:String = "completePurchase";

    public var generalInformation:GeneralInformationModel;
    public var paymentInformation:PaymentInformationModel;
    public var shoppingCart:ShoppingCart;

    /**
     * Constructor.
     */
    public function PurchaseCompleteEvent() {
        super(EVENT_COMPLETE_PURCHASE);
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone():Event {
        return new PurchaseCompleteEvent();
    }
}
}
