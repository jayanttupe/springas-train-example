package control {
import flash.events.Event;

public class AddNewEmployeeEvent extends Event {

    public static const ADD_NEW_EMPLOYEE_EVENT:String = "addNewEmployee";

    public function AddNewEmployeeEvent() {
        super(ADD_NEW_EMPLOYEE_EVENT);
    }
}
}