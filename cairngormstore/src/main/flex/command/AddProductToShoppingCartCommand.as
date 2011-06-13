package command {
import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;

import event.UpdateShoppingCartEvent;

import model.ShopModelLocator;

/**
 * @version  $Revision: $
 */
public class AddProductToShoppingCartCommand implements ICommand {

    public function AddProductToShoppingCartCommand() {
    }

    public function execute(event:CairngormEvent):void {
        var shoppingEvent:UpdateShoppingCartEvent =
                UpdateShoppingCartEvent(event);

        ShopModelLocator.getInstance().shoppingCart.addElement(
                shoppingEvent.product, shoppingEvent.quantity);
    }
}
}
