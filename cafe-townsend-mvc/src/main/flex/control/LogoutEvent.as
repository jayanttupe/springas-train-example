package control {
import flash.events.Event;

public class LogoutEvent extends Event {

    public static const LOGOUT_EVENT:String = "logout";

    public function LogoutEvent() {
        super(LOGOUT_EVENT);
    }
}
}