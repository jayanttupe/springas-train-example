package command {
import business.IUserDelegate;

import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;

import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;
import mx.rpc.IResponder;

import org.as3commons.lang.Assert;

public class UserLoginCommand implements ICommand, IResponder {

    private var userDelegate:IUserDelegate;

    public function UserLoginCommand(userDelegate:IUserDelegate):void {
        Assert.notNull(userDelegate, "The userDelegate must not be null");
        this.userDelegate = userDelegate;
    }

    public function execute(event:CairngormEvent):void {
        var e:UserLoginEvent = UserLoginEvent(event);
        var username:String = e.username;
        var password:String = e.password;
        userDelegate.responder = this;
        userDelegate.login(username, password);
    }

    public function result(data:Object):void {
        var result:Boolean = data.result;
        if (result) {
            AppModelLocator.getInstance().viewing = AppModelLocator.USER_LIST;
        } else {
            Alert.show("invalid username and password!");
        }
    }

    public function fault(info:Object):void {
        Alert.show("operation failed!");
    }
}
}


