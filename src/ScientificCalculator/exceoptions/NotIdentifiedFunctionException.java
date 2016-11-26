package ScientificCalculator.exceoptions;

/**
 * Created by ahmad on 3/31/16.
 */
public class NotIdentifiedFunctionException extends Exception {
	public NotIdentifiedFunctionException(String functionName) {
		super("The function " + functionName + " is not identifiable.");
	}
}
