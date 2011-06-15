/*
* Copyright 2007-2010 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package command {
import control.AddNewEmployeeEvent;

import model.AppModelLocator;

import vo.Employee;

[Command(eventType="addNewEmployee")]
	public class AddNewEmployeeCommand extends CommandBase {

		public function execute( cgEvent : AddNewEmployeeEvent ) : void {
			// add new employee instantiates a new employee object, which has default blank values in the constructor
			appModel.employeeTemp = new Employee();
			
			// main viewstack selectedIndex is bound to this modelpkg locator value
			// so this now switches the view from the employee list to the detail screen
			// so we can populate the new blank employee values
			appModel.viewing = AppModelLocator.EMPLOYEE_DETAIL;
			}
		}
	}