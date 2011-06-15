package command {
import business.UserDelegate;

import event.UserQueryEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.IResponder;

[Command(eventType="userQuery")]
public class UserQueryCommand extends CommandBase implements IResponder {

    public function UserQueryCommand() {
    }

    public function execute(event:UserQueryEvent):void{
        var delegate:UserDelegate = new UserDelegate(this);
        delegate.getUserList();
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
