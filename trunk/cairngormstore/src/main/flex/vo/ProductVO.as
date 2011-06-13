package vo {
import com.adobe.cairngorm.vo.IValueObject;

import util.Comparable;

[RemoteClass(alias="com.adobe.cairngorm.samples.store.vo.ProductVO")]
public class ProductVO implements IValueObject, Comparable {
    public function get identifier():String {
        return String(id);
    }

    public function toString():String {
        var s:String = "ProductVO[id=";
        s += id;
        s += ", name=";
        s += name;
        s += ", description=";
        s += description;
        s += ", price=";
        s += price;
        s += ", image=";
        s += image;
        s += ",thumbnail=";
        s += thumbnail;
        s += " ]";
        return s;
    }

    [Bindable]
    public var id:Number;

    [Bindable]
    public var name:String;

    [Bindable]
    public var description:String;

    [Bindable]
    public var price:Number;

    [Bindable]
    public var image:String;

    [Bindable]
    public var thumbnail:String;
}
}
