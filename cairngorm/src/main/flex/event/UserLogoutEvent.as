package event {
import com.adobe.cairngorm.control.CairngormEvent;

public class UserLogoutEvent extends CairngormEvent {

    public static const USER_LOGOUT_EVENT:String = "userLogout";

    public function UserLogoutEvent() {
        super(USER_LOGOUT_EVENT);
    }
}
}
