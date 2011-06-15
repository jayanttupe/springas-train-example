package command {
import event.UserLogoutEvent;

import model.AppModelLocator;

[Command(eventType="userLogout")]
public class UserLogoutCommand extends CommandBase {
    public function UserLogoutCommand() {
    }

    public function execute(e:UserLogoutEvent):void{
        appModel.viewing = AppModelLocator.USER_LOGIN;
    }

}
}
