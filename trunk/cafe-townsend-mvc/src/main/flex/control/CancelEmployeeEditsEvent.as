package control {
import flash.events.Event;

public class CancelEmployeeEditsEvent extends Event {

    public static const CANCEL_EMPLOYEE_EDITS_EVENT:String = "cancelEmployeeEdits";

    public function CancelEmployeeEditsEvent() {
        super(CANCEL_EMPLOYEE_EDITS_EVENT);
    }
}
}