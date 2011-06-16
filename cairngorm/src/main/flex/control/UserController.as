package control {
import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;
import com.adobe.cairngorm.control.FrontController;

import command.UserLoginCommand;

import command.UserLogoutCommand;
import command.UserQueryCommand;

import event.UserLoginEvent;

import event.UserLogoutEvent;
import event.UserQueryEvent;

import flash.utils.Dictionary;

import org.springextensions.actionscript.context.IApplicationContext;
import org.springextensions.actionscript.context.IApplicationContextAware;

public class UserController extends FrontController implements IApplicationContextAware {

    private var appContext:IApplicationContext;

    private var commandArguments:Dictionary = new Dictionary();

    public function UserController() {
        commandArguments[UserLoginCommand] = "userDelegate";
        commandArguments[UserQueryCommand] = "userDelegate";
        commandArguments[UserLogoutCommand] = "userDelegate";
        initialiseCommands();
    }

    public function initialiseCommands():void {
        addCommand(UserLoginEvent.USER_LOGIN_EVENT, UserLoginCommand);
        addCommand(UserQueryEvent.USER_QUERY_EVENT, UserQueryCommand);
        addCommand(UserLogoutEvent.USER_LOGOUT_EVENT, UserLogoutCommand);
    }

    public function set applicationContext(value:IApplicationContext):void {
        this.appContext = value
    }

    public function get applicationContext():IApplicationContext {
        return this.appContext;
    }

    /**
		 * Custom implementation of executeCommand that will inject the constructor arguments into the command objects
		 * by looking them up in the application context.
		 */
		override protected function executeCommand(cgEvent:CairngormEvent):void {
			var cmdClass:Class = getCommand(cgEvent.type);
			var cmd:ICommand;

			// do we need to inject a constructor argument?
			var ctorArg:String = commandArguments[cmdClass];

			if (ctorArg) {
				var arg:* = appContext.getObject(ctorArg);
				cmd = new cmdClass(arg);
			} else {
				cmd = new cmdClass();
			}

			cmd.execute(cgEvent);
		}
}
}
