package command {
import model.AppModelLocator;
import model.IApplicationModelAware;

public class CommandBase implements IApplicationModelAware {
		
		public function CommandBase() {
			super();
		}

		private var _model:AppModelLocator;

		public function get appModel():AppModelLocator {
			return _model;
		}
		public function set appModel(value:AppModelLocator):void {
			_model = value;
		}
	}
}