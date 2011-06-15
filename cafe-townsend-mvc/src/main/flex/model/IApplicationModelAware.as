package model {

public interface IApplicationModelAware {
    function get appModel():AppModelLocator;

    function set appModel(value:AppModelLocator):void;
}
}