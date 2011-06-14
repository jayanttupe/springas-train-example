package model {

[Bindable]
public class AppModelLocator {

    private static var appModel:AppModelLocator;

    public static const USER_LOGIN:Number = 0;
    public static const USER_LIST:Number = 1;
    public static const USER_DETAIL:Number = 2;
    public var viewing:Number = USER_LOGIN;

    function AppModelLocator(){

    }

    public static function getInstance():AppModelLocator {
        if (appModel == null)
            appModel = new AppModelLocator();
        return appModel;
    }
}
}
