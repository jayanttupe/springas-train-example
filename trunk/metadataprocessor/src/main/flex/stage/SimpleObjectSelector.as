package stage {
import mx.core.UIComponent;
import org.springextensions.actionscript.stage.IObjectSelector;

public class SimpleObjectSelector implements IObjectSelector {

    public function SimpleObjectSelector() {
        super();
    }

    public function approve(object:Object):Boolean {
        return (object is UIComponent);
    }
}
}