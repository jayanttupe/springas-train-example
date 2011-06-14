package event {
import flash.events.Event;

public class UserLoginEvent extends Event{

    public static const USER_LOGIN_EVENT:String = "userLoginEvent";

    public var username:String;
    public var password:String;

    public function UserLoginEvent(username:String, password:String) {
        super(USER_LOGIN_EVENT);
        this.username = username;
        this.password = password;
    }
}
}
