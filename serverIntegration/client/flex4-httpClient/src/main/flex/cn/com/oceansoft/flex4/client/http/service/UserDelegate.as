/**
 * Created by IntelliJ IDEA.
 * User: hujl
 * Date: 11-12-9
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
package cn.com.oceansoft.flex4.client.http.service {
import mx.rpc.AsyncToken;
import mx.rpc.http.mxml.HTTPService;

//import mx.rpc.http.HTTPService;

import org.swizframework.utils.services.ServiceHelper;

public class UserDelegate implements IUserDelegate {


    [Inject( "getUserListSrv" )]
    public var ro:HTTPService;

    [Inject]
    protected var util:ServiceHelper;

    public function UserDelegate() {

    }

    public function getUserList():AsyncToken {
        util.executeServiceCall(ro.send(),resultHandler , faultHandler)
        return null;
    }

    private function faultHandler(info:Object):void {
        //...
    }

    private function resultHandler(data:Object):void {
    }
}
}
