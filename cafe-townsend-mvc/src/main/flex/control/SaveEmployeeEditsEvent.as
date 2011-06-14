package control {
import flash.events.Event;

public class SaveEmployeeEditsEvent extends Event {

    public static const SAVE_EMPLOYEE_EDITS_EVENT:String = "saveEmployeeEdits";

    public var emp_id:Number;
    public var firstname:String;
    public var lastname:String;
    public var startdate:Date;
    public var email:String;

    public function SaveEmployeeEditsEvent(emp_id:Number, firstname:String, lastname:String, startdate:Date, email:String) {
        super(SAVE_EMPLOYEE_EDITS_EVENT);
        this.emp_id = emp_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.startdate = startdate;
        this.email = email;
    }
}
}