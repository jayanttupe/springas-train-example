package command {
import control.DeleteEmployeeEvent;

import modelpkg.AppModelLocator;


[Command(eventType="deleteEmployee")]
	public class DeleteEmployeeCommand extends CommandBase {

		public function execute( cgEvent : DeleteEmployeeEvent ) : void {
			// loop thru the employee list in the modelpkg locator
			for ( var i : uint = 0; i < model.employeeListDP.length; i++ ) {
				// if the emp_id stored in the temp employee matches one of the emp_id's in the employee list
				if ( model.employeeTemp.emp_id == model.employeeListDP[i].emp_id ) {
					// remove that item from the ArrayCollection
					model.employeeListDP.removeItemAt( i );
					}
				}
				
			// clear out the data stored in the temp employee
			model.employeeTemp = null;
			
			// main viewstack selectedIndex is bound to this modelpkg locator value
			// so this now switches the view from the detail screen back to the employee list
			// the list should be one array item shorter
			model.viewing = AppModelLocator.EMPLOYEE_LIST;
			}
		}
	}