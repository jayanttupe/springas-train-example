package business {
import mx.rpc.IResponder;
import mx.rpc.events.ResultEvent;

public class UserDelegateMock implements IUserDelegate {

   private var command:IResponder;

    public function UserDelegateMock(command:IResponder) {
        this.command = command;
    }

    public function login(username:String, password:String):void {
        var result:Boolean = (username == "user" && password == "password");
        command.result(new ResultEvent("result", false, true, result));
    }

    public function getAll():void {

    }
}
}
