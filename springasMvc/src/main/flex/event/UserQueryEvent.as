package event {
import flash.events.Event;

public class UserQueryEvent extends Event {

    public static const USER_QUERY_EVENT:String = "userQuery";

    public function UserQueryEvent():void{
        super(USER_QUERY_EVENT);
    }

}
}
