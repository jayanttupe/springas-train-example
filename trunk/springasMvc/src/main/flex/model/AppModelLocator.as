package model {
import mx.collections.ArrayCollection;

[Bindable]
public class AppModelLocator {

    private static var appModel:AppModelLocator;

    public static const USER_LOGIN:Number = 0;
    public static const USER_LIST:Number = 1;
    public static const USER_DETAIL:Number = 2;
    public var viewing:Number = USER_LIST;

    public var userList:ArrayCollection=new ArrayCollection();

    public function AppLocator():void {
        if (AppModelLocator.appModel != null)
            throw new Error("Only one ModelLocator instance should be instantiated");
    }

    public static function getInstance():AppModelLocator {
        if (appModel == null)
            appModel = new AppModelLocator();
        return appModel;
    }
}
}
