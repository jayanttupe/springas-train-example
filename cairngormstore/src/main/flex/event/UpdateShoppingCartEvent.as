package event
{
  import com.adobe.cairngorm.control.CairngormEvent;
  import flash.events.Event;

import vo.ProductVO;

public class UpdateShoppingCartEvent extends CairngormEvent
  {
    public static const EVENT_ADD_PRODUCT_TO_SHOPPING_CART : String = "addProductToShoppingCart";
    public static const EVENT_DELETE_PRODUCT_FROM_SHOPPING_CART : String = "deleteProductFromShoppingCart";

    public var product : ProductVO;
    public var quantity : Number;

    /**
     * Constructor.
     */
    public function UpdateShoppingCartEvent( type : String, bubbles : Boolean = true, cancelable : Boolean = false )
    {
      super( type, bubbles, cancelable );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new UpdateShoppingCartEvent( type, bubbles, cancelable );
    }
  }
}
