package control {
import flash.events.Event;

public class LoadEmployeesEvent extends Event {

    public static const LOAD_EMPLOYEES_EVENT:String = "loadEmployees";

    public function LoadEmployeesEvent() {
        super(LOAD_EMPLOYEES_EVENT);
    }
}
}