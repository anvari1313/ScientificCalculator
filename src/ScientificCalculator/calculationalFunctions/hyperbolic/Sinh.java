package ScientificCalculator.calculationalFunctions.hyperbolic;

import ScientificCalculator.calculationalFunctions.CalculationFunction;
import ScientificCalculator.calculationalFunctions.ExpressionCalculator;
import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 3/25/16.
 */
public class Sinh extends CalculationFunction {
	public Sinh(ArrayList<String> input){
		super(input);
	}
	public Sinh(List<String> input){
		super(input);
	}
	public Sinh(BigDecimal parameter){
		super(parameter);
	}
	protected void checkDomain() throws NotInDomainException {
		;   // There is nothing to check in this function
	}
	public BigDecimal calValue(){
		BigDecimal result = new BigDecimal("0.0");
		BigDecimal term;

		for (int i = 0; i < numOfTerms; i++) {
			term = parameter.pow(2 * i + 1);
			term = term.divide(fact(2 * i + 1), ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
			result = result.add(term);
		}
		return result;
	}
	protected void standardizeParameter(){

	}
}
