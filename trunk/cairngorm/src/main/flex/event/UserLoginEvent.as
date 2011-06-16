package event {
import com.adobe.cairngorm.control.CairngormEvent;

import flash.events.Event;


public class UserLoginEvent extends CairngormEvent{

    public static const USER_LOGIN_EVENT:String = "userLogin";

    public var username:String;
    public var password:String;

    public function UserLoginEvent() {
//    public function UserLoginEvent(username:String, password:String) {
        super(USER_LOGIN_EVENT);
//        this.username = username;
//        this.password = password;
    }

    override public function clone():Event{
        return new UserLoginEvent();
    }
}
}
