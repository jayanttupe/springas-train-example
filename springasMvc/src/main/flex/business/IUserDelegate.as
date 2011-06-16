package business {
import mx.rpc.IResponder;

public interface IUserDelegate {

    function setCommand(command:IResponder):void;

    function login(username:String , password:String):void;

    function getAll():void;

}
}
