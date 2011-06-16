package command {
import business.IUserDelegate;
import business.UserDelegate;

import event.UserLoginEvent;

import model.AppModelLocator;

import mx.controls.Alert;
import mx.rpc.IResponder;

[Component]
[Command(eventType="userLogin")]
public class UserLoginCommand implements IResponder {

    /*
    * the [Property] case, the property injection will be defined as a property on the object definition.
    * Therefore it is possible that this definition will be processed by other IObjectFactoryPostProcessor implementations (and thus the property might be processed too) later in the application context bootstrap.
    * the [Autowired] case, the injections will be resolved at runtime, at the moment that the context instantiates the object.
    * Both cases lead to the same result, but keep these small differences in mind when choosing which annotation to use.*/

 //    [Property(ref="userDelegate")]
    [Autowired(name="userDelegate")]
    public var userDelegate:IUserDelegate;

    public function execute(e:UserLoginEvent):void {
        var username:String = e.username;
        var password:String = e.password
        userDelegate.setCommand(this);
        userDelegate.login(username,password);
    }

    public function result(data:Object):void {
        var result:Boolean = data.result;
        if (result) {
            AppModelLocator.getInstance().viewing = AppModelLocator.USER_LIST;
        } else {
            Alert.show("invalid username and password!");
        }
    }

    public function fault(info:Object):void {
        Alert.show("operation failed!");
    }
}
}
