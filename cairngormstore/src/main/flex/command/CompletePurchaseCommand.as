package command
{
  import com.adobe.cairngorm.commands.ICommand;
  import com.adobe.cairngorm.control.CairngormEvent;

import event.PurchaseCompleteEvent;

import model.ShopModelLocator;
import model.ShoppingCart;

import view.checkout.GeneralInformationModel;
import view.checkout.PaymentInformationModel;

/**
   * @version  $Revision: $
   */
  public class CompletePurchaseCommand implements ICommand
  {
    public function CompletePurchaseCommand()
    {
    }

    public function execute( event : CairngormEvent ) : void
    {
      var purchaseEvent : PurchaseCompleteEvent =
          PurchaseCompleteEvent( event );

      var generalInformation : GeneralInformationModel = purchaseEvent.generalInformation;
      var paymentInformation : PaymentInformationModel = purchaseEvent.paymentInformation;
      var shoppingCart : ShoppingCart = purchaseEvent.shoppingCart;

      // In a real-world application, in here, we would let the back-end know
      // about a purchase.

      ShopModelLocator.getInstance().orderConfirmed = true;
    }
  }
}
