package event {
import com.adobe.cairngorm.control.CairngormEvent;

public class UserQueryEvent extends CairngormEvent {

    public static const USER_QUERY_EVENT:String = "userQuery";

    public function UserQueryEvent() {
        super(USER_QUERY_EVENT);
    }
}
}
