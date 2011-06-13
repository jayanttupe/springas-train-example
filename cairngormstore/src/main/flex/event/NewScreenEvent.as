package event
{
  import flash.events.Event;

  public class NewScreenEvent extends Event
  {
    public static const NEW_SCREEN_EVENT : String = "newScreen";

    public var position : int;

    /**
     * Constructor.
     */
    public function NewScreenEvent()
    {
      super( NEW_SCREEN_EVENT );
    }

    /**
     * Override the inherited clone() method, but don't return any state.
     */
    override public function clone() : Event
    {
      return new NewScreenEvent();
    }
  }
}
