package command {
import control.UpdateEmployeeEvent;

import model.AppModelLocator;

import vo.Employee;

[Command(eventType="updateEmployee")]
public class UpdateEmployeeCommand extends CommandBase {

    public function execute(cgEvent:UpdateEmployeeEvent):void {
        // cast the caringorm event so we can get at the selectedItem values sent from the mx:List
        var selectedItem:Object = cgEvent.selectedItem;

        // populate a temp employee in the modelpkg locator with the details from the selectedItem
        appModel.employeeTemp = new Employee(selectedItem.emp_id,
                selectedItem.firstname,
                selectedItem.lastname,
                selectedItem.email,
                new Date(Date.parse(selectedItem.startdate)));

        // main viewstack selectedIndex is bound to this modelpkg locator value
        // so this now switches the view from the employee list to the detail screen
        appModel.viewing = AppModelLocator.EMPLOYEE_DETAIL;
    }
}
}