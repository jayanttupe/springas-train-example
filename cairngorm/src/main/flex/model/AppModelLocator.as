package model {
import com.adobe.cairngorm.model.ModelLocator;

import mx.collections.ArrayCollection;

[Bindable]
public class AppModelLocator implements ModelLocator {

    private static var appModel:AppModelLocator;

    public static const USER_LOGIN:Number = 0;
    public static const USER_LIST:Number = 1;
    public static const USER_DETAIL:Number = 2;
    public var viewing:Number = USER_LOGIN;

    public var userList:ArrayCollection = new ArrayCollection();

    public static function getInstance():AppModelLocator {
        if (appModel == null) {
            appModel = new AppModelLocator();
        }
        return appModel;
    }

    //Constructor should be private but current AS3.0 does not allow it.
    public function appModelLocator() {
        if (appModel != null) {
            throw new Error("Only one ShopModelLocator instance should be instantiated");
        }
    }

}
}
