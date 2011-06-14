package control {
import flash.events.Event;

public class DeleteEmployeeEvent extends Event {

    public static const DELETE_EMPLOYEE_EVENT:String = "deleteEmployee";

    public function DeleteEmployeeEvent() {
        super(DELETE_EMPLOYEE_EVENT);
    }
}
}