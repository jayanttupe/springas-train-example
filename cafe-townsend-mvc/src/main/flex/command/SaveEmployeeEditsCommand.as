package command {
import control.SaveEmployeeEditsEvent;

import model.AppModelLocator;


[Command(eventType="saveEmployeeEdits")]
	public class SaveEmployeeEditsCommand extends CommandBase {

		public function execute(cgEvent:SaveEmployeeEditsEvent):void {
			appModel.employeeTemp.emp_id = cgEvent.emp_id;
			appModel.employeeTemp.firstname = cgEvent.firstname;
			appModel.employeeTemp.lastname = cgEvent.lastname;
			appModel.employeeTemp.startdate = cgEvent.startdate;
			appModel.employeeTemp.email = cgEvent.email;

			// assume the edited fields are not an existing employee, but a new employee
			// and set the ArrayCollection index to -1, which means this employee is not in our existing
			// employee list anywhere
			var dpIndex:int = -1;

			// loop thru the employee list
			for (var i:uint = 0; i < appModel.employeeListDP.length; i++) {
				// if the emp_id of the incoming employee matches an employee already in the list
				if (appModel.employeeListDP[i].emp_id == appModel.employeeTemp.emp_id) {
					// set our ArrayCollection index to that employee position
					dpIndex = i;
				}
			}

			// if it was an existing employee already in the ArrayCollection
			if (dpIndex >= 0) {
				// update that employee's values
				appModel.employeeListDP.setItemAt(appModel.employeeTemp, dpIndex);
			}
			// otherwise, if it didn't match any existing employees
			else {
				// add the temp employee to the ArrayCollection
				appModel.employeeListDP.addItem(appModel.employeeTemp);
			}

			// now that we've trasferred the temp employee to the array we can clear out the temp employee
			appModel.employeeTemp = null;

			// main viewstack selectedIndex is bound to this modelpkg locator value
			// so this now switches the view from the detail screen back to the employee list
			// the employee list should now contain one more item
			appModel.viewing = AppModelLocator.EMPLOYEE_LIST;
		}
	}
}