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
public class Tanh extends CalculationFunction {

	public Tanh(ArrayList<String> input) {
		super(input);
	}

	public Tanh(List<String> input){
		super(input);
	}

	public BigDecimal calValue() {
		BigDecimal sinhValue = (new Sinh(parameter)).calValue();
		BigDecimal coshValue = (new Cosh(parameter)).calValue();
		return sinhValue.divide(coshValue, ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
	}

	protected void checkDomain() throws NotInDomainException{
	}

	protected void standardizeParameter() {
	}
}
