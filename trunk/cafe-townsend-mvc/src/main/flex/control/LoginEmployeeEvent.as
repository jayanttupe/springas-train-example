package control {
import flash.events.Event;

public class LoginEmployeeEvent extends Event {

    public static const LOGIN_EMPLOYEE_EVENT:String = "loginEmployee";

    public var username:String;
    public var password:String;

    public function LoginEmployeeEvent(username:String, password:String) {
        super(LOGIN_EMPLOYEE_EVENT);
        this.username = username;
        this.password = password;
    }
}
}