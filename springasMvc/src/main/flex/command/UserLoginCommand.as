package command {
import business.IUserDelegate;

import business.UserDelegateMock;

import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;

import mx.rpc.IResponder;

import org.as3commons.lang.Assert;

[Command(eventType="userLogin")]
public class UserLoginCommand extends CommandBase implements IResponder{

    public var userDelegate:IUserDelegate;

    public function execute(e:UserLoginEvent):void {
        var username:String = e.username;
        var password:String = e.password
        userDelegate.login(username, password);
//        if (userDelegate.login(username, password)) {
//            appModel.viewing = AppModelLocator.USER_LIST;
//        } else {
//            Alert.show("invalid username and password!");
//        }
    }

    public function result(data:Object):void {
        var result : Boolean = data.result;
        Alert.show("result:"+result.toString());
    }

    public function fault(info:Object):void {
        Alert.show("info:"+info.toString());
    }
}
}
