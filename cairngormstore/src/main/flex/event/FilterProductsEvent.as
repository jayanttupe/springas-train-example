package event
{
  import flash.events.Event;
  import com.adobe.cairngorm.control.CairngormEvent;

  public class FilterProductsEvent extends CairngormEvent
  {
    public static var EVENT_FILTER_PRODUCTS : String = "filterProducts";

    public var filterOn : String;
    public var min : uint;
    public var max : uint;

    /**
     * Constructor.
     */
    public function FilterProductsEvent()
    {
      super( EVENT_FILTER_PRODUCTS );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new FilterProductsEvent();
    }
  }
}
