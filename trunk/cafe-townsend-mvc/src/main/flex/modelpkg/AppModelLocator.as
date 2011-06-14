package modelpkg {
import mx.collections.ArrayCollection;

import vo.Employee;
import vo.User;

[Bindable]
public class AppModelLocator {

    // this instance stores a static reference to itself
    private static var model:AppModelLocator;

    // available values for the main viewstack
    // defined as contants to help uncover errors at compile time instead of run time
    public static const EMPLOYEE_LOGIN:Number = 0;
    public static const EMPLOYEE_LIST:Number = 1;
    public static const EMPLOYEE_DETAIL:Number = 2;
    // viewstack starts out on the login screen
    public var viewing:Number = EMPLOYEE_LOGIN;

    // user object contains uid/passwd
    // its value gets set at login and cleared at logout but nothing binds to it or uses it
    // retained since it was used in the original Adobe CafeTownsend example app
    public var user:User;

    // variable to store error messages from the httpservice
    // nothinng currently binds to it, but an Alert or the login box could to show startup errors
    public var errorStatus:String;

    // contains the main employee list which is populated on startup
    // mx:application's creationComplete event is mutated into a cairngorm event
    // that calls the httpservice for the data
    public var employeeListDP:ArrayCollection;

    // temp holding space for employees we're creating or editing
    // this gets copied into or added onto the main employee list
    public var employeeTemp:Employee = new Employee(0, "", "", "", null);

    // singleton: constructor only allows one modelpkg locator
    public function AppLocator():void {
        if (AppModelLocator.model != null)
            throw new Error("Only one ModelLocator instance should be instantiated");
    }


    function AppModelLocator() {
    }

// singleton: always returns the one existing static instance to itself
    public static function getInstance():AppModelLocator {
        if (model == null)
            model = new AppModelLocator();
        return model;
    }
}
}

