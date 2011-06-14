package business {
import mx.collections.ArrayCollection;
import mx.rpc.IResponder;
import mx.rpc.events.ResultEvent;

import vo.Employee;

public class LoadEmployeesDelegate {

    private var command:IResponder;

    public function LoadEmployeesDelegate(command:IResponder) {
        // constructor will store a reference to the service we're going to call
        // and store a reference to the command that created this delegate
        this.command = command;
    }

    public function loadEmployeesService():void {
        // call the service
        var employeeIndex:int = 1;
        var employees:ArrayCollection = new ArrayCollection();
        employees.addItem(new Employee(employeeIndex++, "Sue", "Hove", "shove@cafetownsend.com", new Date(2006, 7, 1)));
        employees.addItem(new Employee(employeeIndex++, "Matt", "Boles", "mboles@cafetownsend.com", new Date(2007, 3, 13)));
        employees.addItem(new Employee(employeeIndex++, "Mike", "Kollen", "mkollen@cafetownsend.com", new Date(2006, 11, 19)));
        employees.addItem(new Employee(employeeIndex++, "Jennifer", "Jaegel", "jjaegel@cafetownsend.com", new Date(2003, 1, 27)));
        command.result(new ResultEvent("result", false, true, employees));
    }
}
}