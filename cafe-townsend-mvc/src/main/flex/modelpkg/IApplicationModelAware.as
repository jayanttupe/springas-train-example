package modelpkg {

public interface IApplicationModelAware {
    function get model():AppModelLocator;

    function set model(value:AppModelLocator):void;
}
}