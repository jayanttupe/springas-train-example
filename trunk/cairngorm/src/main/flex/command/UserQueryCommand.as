package command {
import business.IUserDelegate;

import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;

import model.AppModelLocator;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.IResponder;

import org.as3commons.lang.Assert;

public class UserQueryCommand implements ICommand,IResponder {

    private var userDelegate:IUserDelegate;

    public function UserQueryCommand(userDelegate:IUserDelegate) {
        Assert.notNull(userDelegate, "The userDelegate must not be null");
        this.userDelegate = userDelegate;
    }

    public function execute(event:CairngormEvent):void {
        userDelegate.responder = this;
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
