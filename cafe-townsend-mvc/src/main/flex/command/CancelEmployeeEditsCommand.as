package command {
import control.CancelEmployeeEditsEvent;

import modelpkg.AppModelLocator;

[Command(eventType="cancelEmployeeEdits")]
public class CancelEmployeeEditsCommand extends CommandBase {

//    private var _model:AppModelLocator;

    public function execute(cgEvent:CancelEmployeeEditsEvent):void {
        // decided we don't need to store the edited employee details,
        // so null out the temp employee in the modelpkg locators
        this.model.employeeTemp = null;
//        modelpkg.employeeTemp = null;

        // main viewstack selectedIndex is bound to this modelpkg locator value
        // so this now switches the view from the detail screen back to the employee list
        model.viewing = AppModelLocator.EMPLOYEE_LIST;
    }
}
}