package command
{
  import com.adobe.cairngorm.commands.ICommand;
  import com.adobe.cairngorm.control.CairngormEvent;

import event.FilterProductsEvent;

import model.ShopModelLocator;

/**
   * @version $Revision: $
   */
  public class FilterProductsCommand implements ICommand
  {
    public function FilterProductsCommand()
    {
    }

    public function execute( event : CairngormEvent ) : void
    {
      var model : ShopModelLocator = ShopModelLocator.getInstance();
      var filterEvent : FilterProductsEvent = FilterProductsEvent( event );

      var filterOn : String = filterEvent.filterOn;
      var min : Number = filterEvent.min;
      var max : Number = filterEvent.max;

      model.productComparator.addFilterRangeProperty( filterOn, min, max );
      model.productComparator.filter();
      model.productComparator.applyAlphaEffect();
    }
  }
}
