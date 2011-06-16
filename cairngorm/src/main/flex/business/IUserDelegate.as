package business {
import org.springextensions.actionscript.cairngorm.IResponderAware;

public interface IUserDelegate extends IResponderAware{

    function login(username:String , password:String):void;

    function getAll():void;

}
}
