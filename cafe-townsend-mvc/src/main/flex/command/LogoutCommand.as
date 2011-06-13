package command {
import control.LogoutEvent;

import model.AppModelLocator;

[Command(eventType="logout")]
public class LogoutCommand extends CommandBase {

    public function execute(cgEvent:LogoutEvent):void {
        // null out the user object stored in the model locator
        model.user = null;

        // main viewstack selectedIndex is bound to this model locator value
        // so this now switches the view from the employee list back to the initial login screen
        model.viewing = AppModelLocator.EMPLOYEE_LOGIN;
    }
}
}