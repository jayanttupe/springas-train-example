package command {
import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;

[Command(eventType="userLogin")]
//[Command(eventClass="event.UserLoginEvent"]
public class UserLoginCommand extends CommandBase {

    private function execute(e:UserLoginEvent):void {
        Alert.show("execute...");
        var username:String = e.username;
        var password:String = e.password

        if (username == "user" && password == "password") {
            Alert.show("11...");
            appModel.viewing = AppModelLocator.USER_LIST;
        } else {
            Alert.show("invalid username and password!");
        }

    }
}
}
