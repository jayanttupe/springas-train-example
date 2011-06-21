package business {

	import mx.rpc.remoting.RemoteObject;

	import org.springextensions.actionscript.cairngorm.business.AbstractBusinessDelegate;

	/**
	 * @version  $Revision: $
	 */
	public class ProductDelegate extends AbstractBusinessDelegate implements IProductDelegate {

		public function ProductDelegate(service:RemoteObject) {
			super(service);
		}

		public function getProducts():void {
			var call:Object = service.getProducts();
			call.addResponder(responder);
		}
	}
}
