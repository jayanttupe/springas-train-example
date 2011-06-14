package command {
import modelpkg.AppModelLocator;
import modelpkg.IApplicationModelAware;

public class CommandBase implements IApplicationModelAware {
		
		public function CommandBase() {
			super();
		}

		private var _model:AppModelLocator;
		public function get model():AppModelLocator {
			return _model;
		}
		public function set model(value:AppModelLocator):void {
			_model = value;
		}
	}
}