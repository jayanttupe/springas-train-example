package command {
import business.IUserDelegate;
import business.UserDelegate;

import event.UserQueryEvent;

import model.AppModelLocator;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.IResponder;

[Component]
[Command(eventType="userQuery")]
public class UserQueryCommand implements IResponder {

    [Autowired(name="userDelegate")]
    public var userDelegate:IUserDelegate;

    public function execute(event:UserQueryEvent):void{
        userDelegate.setCommand(this);
        userDelegate.getAll();
    }

    public function result(data:Object):void {
        var array:ArrayCollection = data.result;
        AppModelLocator.getInstance().userList = array;
    }

    public function fault(info:Object):void {
        Alert.show("operation failed!");
    }
}
}
