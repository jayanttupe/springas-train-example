package command {
import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;

[Command(eventType="userLogin")]
public class UserLoginCommand extends CommandBase {

    public function execute(e:UserLoginEvent):void {
        var username:String = e.username;
        var password:String = e.password

        if (username == "user" && password == "password") {
            appModel.viewing = AppModelLocator.USER_LIST;
        } else {
            Alert.show("invalid username and password!");
        }

    }
}
}
