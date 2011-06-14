package control {
import flash.events.Event;

public class UpdateEmployeeEvent extends Event {

    public static const UPDATE_EMPLOYEE_EVENT:String = "updateEmployee";

    public var selectedItem:Object;

    public function UpdateEmployeeEvent(selectedItem:Object) {
        super(UPDATE_EMPLOYEE_EVENT);
        this.selectedItem = selectedItem;
    }
}
}