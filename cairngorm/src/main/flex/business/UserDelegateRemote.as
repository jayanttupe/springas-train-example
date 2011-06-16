package business {
import mx.rpc.remoting.RemoteObject;

import org.springextensions.actionscript.cairngorm.business.AbstractBusinessDelegate;

public class UserDelegateRemote extends AbstractBusinessDelegate implements IUserDelegate {

    public function UserDelegateRemote(service:RemoteObject) {
        super(service);
    }

    public function login(username:String, password:String):void {
    }

    public function getAll():void {
        var call:Object = service.getAll();
        call.addResponder(responder);
    }
}
}
