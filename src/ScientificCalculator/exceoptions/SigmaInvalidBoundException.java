package ScientificCalculator.exceoptions;

/**
 * Created by ahmad on 4/1/16.
 */
public class SigmaInvalidBoundException extends Exception {
	public SigmaInvalidBoundException(){
		super("Upper bound of a sigma should be greater than lower bound");
	}
}
