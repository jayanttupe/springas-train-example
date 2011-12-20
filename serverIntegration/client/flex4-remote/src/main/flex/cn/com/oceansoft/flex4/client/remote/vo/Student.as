/**
 * Created by IntelliJ IDEA.
 * User: hujl
 * Date: 11-12-20
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
package cn.com.oceansoft.flex4.client.remote.vo {

[Bindable]
[RemoteClass(alias="cn.com.oceansoft.flex4.server.remote.StudentDto")]

public class Student {

    public var id:String;

    public var name:String;

    public var studentNumber:String;

    public var gender:Number;

}
}
