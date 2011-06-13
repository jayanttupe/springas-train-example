package event
{
  import flash.events.Event;
  import com.adobe.cairngorm.control.CairngormEvent;

  public class SortProductsEvent extends CairngormEvent
  {
    public static var EVENT_SORT_PRODUCTS : String = "sortProducts";

    public var sortBy : String;

    /**
     * Constructor.
     */
    public function SortProductsEvent()
    {
      super( EVENT_SORT_PRODUCTS );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new SortProductsEvent();
    }
  }
}
