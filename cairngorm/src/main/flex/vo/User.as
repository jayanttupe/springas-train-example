package vo {

[Bindable]
public class User {

    public var userId:Number;
    public var username:String;
    public var password:String;
    public var email:String;

    public function User(userId:Number, username:String, password:String, email:String) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
}