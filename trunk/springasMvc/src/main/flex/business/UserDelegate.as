package business {
import mx.collections.ArrayCollection;
import mx.rpc.IResponder;

import mx.rpc.events.ResultEvent;

import org.as3commons.bytecode.abc.Integer;

import vo.User;

public class UserDelegate {

    private var command:IResponder;

    public function UserDelegate(command:IResponder) {
        this.command = command;
    }

    public function getUserList():void {
        // call the service
        var index:Number = 1;
        var userList:ArrayCollection = new ArrayCollection();
        userList.addItem(new User(index++, "Hu", "pass1", "Hhu@hotmail.com"));
        userList.addItem(new User(index++, "Zhang", "pass2", "Zhang@hotmail.com"));
        userList.addItem(new User(index++, "Lee", "pass3", "lee@hotmail.com"));
        userList.addItem(new User(index++, "Wang", "pass4", "wang@hotmail.com"));
        command.result(new ResultEvent("result", false, true, userList));
    }

}
}
