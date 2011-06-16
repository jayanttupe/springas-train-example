package business {
import mx.collections.ArrayCollection;
import mx.rpc.IResponder;
import mx.rpc.events.ResultEvent;

import vo.User;

[Component(id="userDelegate")]
public class UserDelegate implements IUserDelegate {

    private var command:IResponder;

    public function setCommand(command:IResponder):void {
        this.command = command;
    }

    public function login(username:String, password:String):void {
        var result:Boolean = (username == "user" && password == "password")
        command.result(new ResultEvent("result", false, true, result));
    }

    public function getAll():void {
        var index:Number = 1;
        var userList:ArrayCollection = new ArrayCollection();
        userList.addItem(new User(index++, "Hu", "pass5", "Hhu@hotmail.com"));
        userList.addItem(new User(index++, "Zhang", "pass6", "Zhang@hotmail.com"));
        userList.addItem(new User(index++, "Lee", "pass7", "lee@hotmail.com"));
        userList.addItem(new User(index++, "Wang", "pass8", "wang@hotmail.com"));
        command.result(new ResultEvent("result", false, true, userList));
    }

}
}
