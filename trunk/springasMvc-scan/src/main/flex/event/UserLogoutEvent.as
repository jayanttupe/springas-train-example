package event {
import flash.events.Event;

public class UserLogoutEvent extends Event{

     public static const USER_LOGOUT_EVENT:String = "userLogout";

    public function UserLogoutEvent() {
        super(USER_LOGOUT_EVENT);
    }
}
}
