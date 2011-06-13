package control {

	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.FrontController;

import command.AddProductToShoppingCartCommand;
import command.CompletePurchaseCommand;
import command.DeleteProductFromShoppingCartCommand;
import command.FilterProductsCommand;
import command.GetProductsCommand;

import command.SortProductsCommand;
import command.ValidateCreditCardCommand;

import command.ValidateOrderCommand;

import event.FilterProductsEvent;
import event.GetProductsEvent;

import event.PurchaseCompleteEvent;
import event.SortProductsEvent;
import event.UpdateShoppingCartEvent;

import event.ValidateCreditCardEvent;
import event.ValidateOrderEvent;

import flash.utils.Dictionary;

	import org.springextensions.actionscript.context.IApplicationContext;
	import org.springextensions.actionscript.context.IApplicationContextAware;

	/**
	 * The main controller.
	 *
	 * <p>This controller is and application context aware Cairngorm front controller
	 * that will inject business delegates into the commands that it creates, specifically
	 * made for this example project.
	 *
	 * <p>Note: since this class application context aware, it must be managed by the Spring container.
	 *
	 * @version  $Revision: $
	 */
	public class ShopController extends FrontController implements IApplicationContextAware {

		private var _appContext:IApplicationContext;

		private var _commandArguments:Dictionary = new Dictionary();

		/**
		 * Creates a new ShopController
		 */
		public function ShopController() {
			_commandArguments[GetProductsCommand] = "productDelegate";
			_commandArguments[ValidateCreditCardCommand] = "creditCardDelegate";

			initialiseCommands();
		}

		public function set applicationContext(value:IApplicationContext):void {
			_appContext = value;
		}

        public function get applicationContext():IApplicationContext {
            return _appContext;
        }

		public function initialiseCommands():void {
			addCommand(GetProductsEvent.EVENT_GET_PRODUCTS, GetProductsCommand);
			addCommand(UpdateShoppingCartEvent.EVENT_ADD_PRODUCT_TO_SHOPPING_CART, AddProductToShoppingCartCommand);
			addCommand(UpdateShoppingCartEvent.EVENT_DELETE_PRODUCT_FROM_SHOPPING_CART, DeleteProductFromShoppingCartCommand);
			addCommand(FilterProductsEvent.EVENT_FILTER_PRODUCTS, FilterProductsCommand);
			addCommand(SortProductsEvent.EVENT_SORT_PRODUCTS, SortProductsCommand);
			addCommand(ValidateOrderEvent.EVENT_VALIDATE_ORDER, ValidateOrderCommand);
			addCommand(ValidateCreditCardEvent.EVENT_VALIDATE_CREDIT_CARD, ValidateCreditCardCommand);
			addCommand(PurchaseCompleteEvent.EVENT_COMPLETE_PURCHASE, CompletePurchaseCommand);
		}

		/**
		 * Custom implementation of executeCommand that will inject the constructor arguments into the command objects
		 * by looking them up in the application context.
		 */
		override protected function executeCommand(event:CairngormEvent):void {
			var cmdClass:Class = getCommand(event.type);
			var cmd:ICommand;

			// do we need to inject a constructor argument?
			var ctorArg:String = _commandArguments[cmdClass];

			if (ctorArg) {
				var arg:* = _appContext.getObject(ctorArg);
				cmd = new cmdClass(arg);
			} else {
				cmd = new cmdClass();
			}

			cmd.execute(event);
		}
	}
}
