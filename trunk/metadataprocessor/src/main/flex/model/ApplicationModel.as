package model {
import flash.events.EventDispatcher;
import mx.collections.ArrayCollection;

public class ApplicationModel extends EventDispatcher {

    [Bindable]
    public var classNames:ArrayCollection;

    public function ApplicationModel() {
        super();
        initModel();
    }

    protected function initModel():void {
        classNames = new ArrayCollection();
    }

}
}