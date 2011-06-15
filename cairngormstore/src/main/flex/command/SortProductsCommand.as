package command
{
  import com.adobe.cairngorm.commands.ICommand;
  import com.adobe.cairngorm.control.CairngormEvent;

import event.SortProductsEvent;

import model.ShopModelLocator;

import mx.collections.Sort;
  import mx.collections.SortField;

  /**
   * @version $Revision: $
   */
  public class SortProductsCommand implements ICommand
  {
    public function SortProductsCommand()
    {
    }

    public function execute( event : CairngormEvent ) : void
    {
      var sortEvent : SortProductsEvent = SortProductsEvent( event );

      var sortBy : String = sortEvent.sortBy;
      var model : ShopModelLocator = ShopModelLocator.getInstance();

      var sort : Sort = new Sort();

      if ( sortBy == "price" )
      {
        sort.fields = [ new SortField( sortBy, false, false, true ) ];
      }
      else
      {
        sort.fields = [ new SortField( sortBy, true ) ];
      }

      model.products.sort = sort;
      model.products.refresh();

      model.selectedItem = model.products[ 0 ];
    }
  }
}
