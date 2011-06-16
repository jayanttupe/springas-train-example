package business {
import mx.collections.ArrayCollection;
import mx.rpc.IResponder;
import mx.rpc.events.ResultEvent;

import vo.User;

public class UserDelegateMock implements IUserDelegate {

    public var command:IResponder;

    public function login(username:String, password:String):void {
        var result:Boolean = (username == "user" && password == "password");
        command.result(new ResultEvent("result", false, true, result));
    }

    public function getAll():void {
        var index:Number = 1;
        var userList:ArrayCollection = new ArrayCollection();
        userList.addItem(new User(index++, "Hu", "pass1", "Hhu@hotmail.com"));
        userList.addItem(new User(index++, "Zhang", "pass2", "Zhang@hotmail.com"));
        userList.addItem(new User(index++, "Lee", "pass3", "lee@hotmail.com"));
        userList.addItem(new User(index++, "Wang", "pass4", "wang@hotmail.com"));
        command.result(new ResultEvent("result", false, true, userList));
    }

    public function setCommand(command:IResponder):void {
        this.command = command;
    }
}
}
