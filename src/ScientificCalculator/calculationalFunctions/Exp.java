package ScientificCalculator.calculationalFunctions;

import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This class calculates the exponential function that is
 *
 * @author Ahmad
 * @version 1.0.0
 *          Created by ahmad on 3/26/16.
 */

public class Exp extends CalculationFunction {

	public Exp(ArrayList<String> input){
		super(input);
	}

	public Exp(List<String> input) {
		super(input);
	}

	public BigDecimal calValue() {
		BigDecimal result = new BigDecimal("0.0");
		BigDecimal term;

		for (int i = 0; i <= numOfTerms; i++) {
			term = parameter.pow(i);
			term = term.divide(fact(i), ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
			result = result.add(term);
		}

		return result;
	}

	protected void checkDomain() throws NotInDomainException {
		;
	}

	protected void standardizeParameter() {
		;
	}
}

