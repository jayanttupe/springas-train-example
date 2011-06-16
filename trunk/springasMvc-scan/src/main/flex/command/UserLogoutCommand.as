package command {
import event.UserLogoutEvent;

import model.AppModelLocator;

[Component]
[Command(eventType="userLogout")]
public class UserLogoutCommand{
    public function UserLogoutCommand() {
    }

    public function execute(e:UserLogoutEvent):void{
        AppModelLocator.getInstance().viewing = AppModelLocator.USER_LOGIN;
    }

}
}
