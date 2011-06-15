package command {
import control.LogoutEvent;

import model.AppModelLocator;

[Command(eventType="logout")]
public class LogoutCommand extends CommandBase {

    public function execute(cgEvent:LogoutEvent):void {
        // null out the user object stored in the modelpkg locator
        appModel.user = null;

        // main viewstack selectedIndex is bound to this modelpkg locator value
        // so this now switches the view from the employee list back to the initial login screen
        appModel.viewing = AppModelLocator.EMPLOYEE_LOGIN;
    }
}
}