package ScientificCalculator.calculationalFunctions.hyperbolic;

import ScientificCalculator.calculationalFunctions.CalculationFunction;
import ScientificCalculator.calculationalFunctions.ExpressionCalculator;
import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 3/26/16.
 */
public class Coth extends CalculationFunction {

	public Coth(ArrayList<String> input) throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public Coth(List<String> input) throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public BigDecimal calValue() {
		BigDecimal sinhValue = (new Sinh(parameter)).calValue();
		BigDecimal coshValue = (new Cosh(parameter)).calValue();
		return coshValue.divide(sinhValue, ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
	}

	protected void checkDomain() throws NotInDomainException {
		if (parameter.compareTo(new BigDecimal(0)) == 0)
			throw new NotInDomainException("coth");
	}

	protected void standardizeParameter() {
	}
}
