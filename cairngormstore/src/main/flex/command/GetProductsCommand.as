package command {
import business.IProductDelegate;

import com.adobe.cairngorm.commands.ICommand;
import com.adobe.cairngorm.control.CairngormEvent;

import modelpkg.ShopModelLocator;

import mx.collections.ArrayCollection;
import mx.collections.ICollectionView;
import mx.collections.Sort;
import mx.collections.SortField;
import mx.controls.Alert;
import mx.rpc.IResponder;
import mx.rpc.events.FaultEvent;

import org.as3commons.lang.Assert;

/**
 * @version  $Revision: $
 */
public class GetProductsCommand implements ICommand, IResponder {

    private var _delegate:IProductDelegate;

    public function GetProductsCommand(delegate:IProductDelegate) {
        Assert.notNull(delegate, "The product delegate must not be null");
        _delegate = delegate;
    }

    public function execute(event:CairngormEvent):void {
        if (ShopModelLocator.getInstance().products == null) {
            _delegate.responder = this;
            _delegate.getProducts();
        } else {
            Alert.show("Products already retrieved!");
            return;
        }
    }

    public function result(event:Object):void {
        // since amfphp returns an array of productVo's we need to pass it
        // as the source of an arraycollection
        var products:ICollectionView = ((event.result is Array) ? new ArrayCollection(event.result) : ICollectionView(event.result));
        var model:ShopModelLocator = ShopModelLocator.getInstance();

        // sort the data
        var sort:Sort = new Sort();
        sort.fields = [new SortField("name", true)];
        products.sort = sort;
        products.refresh();

        // set the products on the modelpkg
        model.selectedItem = products[0];
        model.products = products;
        model.workflowState = ShopModelLocator.VIEWING_PRODUCTS_IN_THUMBNAILS;
    }

    public function fault(event:Object):void {
        var faultEvent:FaultEvent = FaultEvent(event);
        Alert.show("Products could not be retrieved!");
    }
}
}
