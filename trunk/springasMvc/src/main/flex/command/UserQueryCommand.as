package command {
import business.IUserDelegate;
import business.UserDelegate;

import event.UserQueryEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.IResponder;

[Command(eventType="userQuery")]
public class UserQueryCommand extends CommandBase implements IResponder {

    public var userDelegate:IUserDelegate;

    public function execute(event:UserQueryEvent):void{
        userDelegate.setCommand(this);
        userDelegate.getAll();
    }

    public function result(data:Object):void {
        var array:ArrayCollection = data.result;
        appModel.userList = array;
    }

    public function fault(info:Object):void {
        Alert.show("operation failed!");
    }
}
}
