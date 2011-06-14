package command {
import business.LoadEmployeesDelegate;

import control.LoadEmployeesEvent;

import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	

	[Command(eventType="loadEmployees")]
	public class LoadEmployeesCommand extends CommandBase implements IResponder {

		public function execute(cgEvent:LoadEmployeesEvent):void {
			// create a worker who will go get some data
			// pass it a reference to this command so the delegate knows where to return the data
			var delegate:LoadEmployeesDelegate = new LoadEmployeesDelegate(this);
			// make the delegate do some work
			delegate.loadEmployeesService();
		}

		// this is called when the delegate receives a result from the service
		public function result(rpcEvent:Object):void {
			// populate the employee list in the modelpkg locator with the results from the service call
			model.employeeListDP = ArrayCollection(rpcEvent.result);
		}

		// this is called when the delegate receives a fault from the service
		public function fault(rpcEvent:Object):void {
			// store an error message in the modelpkg locator
			// labels, alerts, etc can bind to this to notify the user of errors
			model.errorStatus = "Fault occured in LoadEmployeesCommand.";
		}
	}
}