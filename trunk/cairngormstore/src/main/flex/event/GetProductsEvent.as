package event
{
  import flash.events.Event;
  import com.adobe.cairngorm.control.CairngormEvent;

  public class GetProductsEvent extends CairngormEvent
  {
    public static var EVENT_GET_PRODUCTS : String = "getProducts";

    public var position : int;

    /**
     * Constructor.
     */
    public function GetProductsEvent()
    {
      super( EVENT_GET_PRODUCTS );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new GetProductsEvent();
    }
  }
}
