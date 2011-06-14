package command {
import control.LoginEmployeeEvent;

import modelpkg.AppModelLocator;

import mx.controls.Alert;

import vo.User;

[Command(eventType="loginEmployee")]
public class LoginEmployeeCommand extends CommandBase {

    public function execute(cgEvent:LoginEmployeeEvent):void {
        // after casting, retreive the username & password payload from the incoming event
        var username:String = cgEvent.username;
        var password:String = cgEvent.password;

        // if the auth info is correct
        if (username == "Flex" && password == "SpringMVC") {
            // store the user info in a new user object in the modelpkg locator
            model.user = new User(username, password);

            // main viewstack selectedIndex is bound to this modelpkg locator value
            // so this now switches the view from the login screen to the employee list
            model.viewing = AppModelLocator.EMPLOYEE_LIST;
        } else {
            // if the auth info was incorrect, prompt with an alert box and remain on the login screen
            Alert.show("We couldn't validate your username & password. Please try again.", "Login Failed");
        }
    }
}
}