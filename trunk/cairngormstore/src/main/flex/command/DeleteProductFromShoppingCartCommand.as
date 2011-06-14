package command
{
  import com.adobe.cairngorm.commands.ICommand;
  import com.adobe.cairngorm.control.CairngormEvent;
  import com.adobe.cairngorm.samples.store.model.ShopModelLocator;
  import com.adobe.cairngorm.samples.store.event.UpdateShoppingCartEvent;

import event.UpdateShoppingCartEvent;

import modelpkg.ShopModelLocator;

/**
   * @version  $Revision: $
   */
  public class DeleteProductFromShoppingCartCommand  implements ICommand
  {
    public function DeleteProductFromShoppingCartCommand()
    {
    }

    public function execute( event : CairngormEvent ) : void
    {
      var shoppingEvent : UpdateShoppingCartEvent =
          UpdateShoppingCartEvent( event );

      ShopModelLocator.getInstance().shoppingCart.deleteElement(
          shoppingEvent.product );
    }
  }
}
