package modelpkg {

	import com.adobe.cairngorm.model.ModelLocator;
	import mx.collections.ArrayCollection;
	import mx.collections.ICollectionView;
	import mx.formatters.CurrencyFormatter;

import util.Comparator;

import view.assets.CairngormStoreAssets;

import view.checkout.GeneralInformationModel;

import view.checkout.PaymentInformationModel;

import vo.ProductVO;

[Bindable]
	public class ShopModelLocator implements ModelLocator {

		private static var modelLocator:ShopModelLocator;

		public static function getInstance():ShopModelLocator {
			if (modelLocator == null) {
				modelLocator = new ShopModelLocator();
			}

			return modelLocator;
		}

		//Constructor should be private but current AS3.0 does not allow it yet (?)...
		public function ShopModelLocator() {
			if (modelLocator != null) {
				throw new Error("Only one ShopModelLocator instance should be instantiated");
			}

			shoppingCart = new ShoppingCart();
			productComparator = new Comparator();
			currencyFormatter = getInitialisedFormatter();
			assets = new CairngormStoreAssets();
		}

		private function getInitialisedFormatter():CurrencyFormatter {
			var formatter:CurrencyFormatter = new CurrencyFormatter();
			formatter.currencySymbol = "$";
			formatter.precision = 2;

			return formatter;
		}

		public var products:ICollectionView;

		public var selectedItem:ProductVO;

		public var shoppingCart:ShoppingCart;

		public var productComparator:Comparator;

		public var currencyFormatter:CurrencyFormatter;

		public var assets:CairngormStoreAssets;

		public var orderConfirmed:Boolean;

		public var creditCardInvalid:Boolean;

		public var cartEmpty:Boolean;

		public var formIncomplete:Boolean;

		public var generalInfo:GeneralInformationModel = new GeneralInformationModel();

		public var paymentInfo:PaymentInformationModel = new PaymentInformationModel();

		public var paymentValidators:ArrayCollection = new ArrayCollection();

		public var generalInfoValidators:ArrayCollection = new ArrayCollection();

		public var workflowState:Number = VIEWING_PRODUCTS_IN_THUMBNAILS;

		public static var VIEWING_PRODUCTS_IN_THUMBNAILS:Number = 0;

		public static var VIEWING_PRODUCTS_IN_GRID:Number = 1;

		public static var VIEWING_CHECKOUT:Number = 2;
	}
}
