package command {
import business.IUserDelegate;

import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;

import model.AppModelLocator;

import mx.rpc.IResponder;

public class UserLogoutCommand implements ICommand,IResponder {

    private var userDelegate:IUserDelegate;

    public function UserLogoutCommand(userDelegate:IUserDelegate) {
        this.userDelegate = userDelegate;
    }

    public function execute(event:CairngormEvent):void {
        //...
        AppModelLocator.getInstance().viewing = AppModelLocator.USER_LOGIN;
    }

    public function result(data:Object):void {
        //...
    }

    public function fault(info:Object):void {
        //...
    }
}
}
