package event
{
  import flash.events.Event;
  import com.adobe.cairngorm.control.CairngormEvent;

  public class ValidateOrderEvent extends CairngormEvent
  {
    public static var EVENT_VALIDATE_ORDER : String = "validateOrder";

    public var position : int;

    /**
     * Constructor.
     */
    public function ValidateOrderEvent()
    {
    super( EVENT_VALIDATE_ORDER );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new ValidateOrderEvent();
    }
  }
}
