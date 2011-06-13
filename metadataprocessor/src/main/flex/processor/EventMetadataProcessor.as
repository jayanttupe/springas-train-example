package processor {
import model.ApplicationModel;
import org.as3commons.reflect.IMetaDataContainer;
import org.springextensions.actionscript.metadata.AbstractMetadataProcessor;

public class EventMetadataProcessor extends AbstractMetadataProcessor {

    private static const EVENT_METADATA_NAME:String = "Event";

    public var applicationModel:ApplicationModel;

    public function EventMetadataProcessor() {
        super(true, [EVENT_METADATA_NAME]);
    }

    override public function process(instance:Object, container:IMetaDataContainer, name:String, objectName:String):void {
        applicationModel.classNames.addItem(Object(instance).constructor);
    }

}
}