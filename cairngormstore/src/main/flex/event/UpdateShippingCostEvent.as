package event
{
  import com.adobe.cairngorm.control.CairngormEvent;
  import flash.events.Event;

  public class UpdateShippingCostEvent extends CairngormEvent
  {
    public static const SHIPPING_COST_CHANGE_EVENT : String = "updateShippingCost";

    public var cost : Number;

    /**
     * Constructor.
     */
    public function UpdateShippingCostEvent()
    {
      super( SHIPPING_COST_CHANGE_EVENT );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new UpdateShippingCostEvent();
    }
  }
}
