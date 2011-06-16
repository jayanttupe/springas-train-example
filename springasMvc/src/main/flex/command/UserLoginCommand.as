package command {
import business.IUserDelegate;
import business.UserDelegate;

import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;
import mx.rpc.IResponder;

[Command(eventType="userLogin")]
public class UserLoginCommand extends CommandBase implements IResponder {

    public var userDelegate:IUserDelegate;

    public function execute(e:UserLoginEvent):void {
        var username:String = e.username;
        var password:String = e.password
        userDelegate.setCommand(this);
        userDelegate.login(username,password);
    }

    public function result(data:Object):void {
        var result:Boolean = data.result;
        if (result) {
            appModel.viewing = AppModelLocator.USER_LIST;
        } else {
            Alert.show("invalid username and password!");
        }
    }

    public function fault(info:Object):void {
        Alert.show("operation failed!");
    }
}
}
