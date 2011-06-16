/**
 * Created by IntelliJ IDEA.
 * User: jingling
 * Date: 6/16/11
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
package business {
import mx.collections.ArrayCollection;

import vo.User;

public class UserDelegateMock extends BaseDelegateMock implements IUserDelegate {
    public function UserDelegateMock() {
        super();
        this.Latency = 1;
    }

    public function login(username:String, password:String):void {
        var result:Boolean = (username == "user" && password == "password");
        //call async responder
        setResults(result);
    }

    public function getAll():void {
        var index:Number = 1;
        var userList:ArrayCollection = new ArrayCollection();
        userList.addItem(new User(index++, "Hu", "pass1", "Hhu@hotmail.com"));
        userList.addItem(new User(index++, "Zhang", "pass2", "Zhang@hotmail.com"));
        userList.addItem(new User(index++, "Lee", "pass3", "lee@hotmail.com"));
        userList.addItem(new User(index++, "Wang", "pass4", "wang@hotmail.com"));
        setResults(userList);
    }
}
}
