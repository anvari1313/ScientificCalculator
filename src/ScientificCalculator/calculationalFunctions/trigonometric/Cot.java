package ScientificCalculator.calculationalFunctions.trigonometric;

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
public class Cot extends CalculationFunction {
	public Cot(ArrayList<String> input) throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public Cot(List<String> input)throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public BigDecimal calValue() {
		BigDecimal sinValue = (new Sin(parameter)).calValue();
		BigDecimal cosValue = (new Cos(parameter)).calValue();
		return cosValue.divide(sinValue, ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
	}

	protected void checkDomain() throws NotInDomainException{
		if ((parameter.compareTo(ExpressionCalculator.piNumber) == 0) ||
				(parameter.compareTo(new BigDecimal(0)) == 0))
			throw new NotInDomainException("cot");
	}

	protected void standardizeParameter() {

		BigDecimal step;
		if ((new BigDecimal(0)).compareTo(parameter) > 0) {   // The parameter is less than zero : alpha < 0
			step = ExpressionCalculator.piNumber.multiply(new BigDecimal(1));
		} else if ((new BigDecimal(0)).compareTo(parameter) < 0) {   // The parameter is more than zero : alpha > 0
			step = ExpressionCalculator.piNumber.multiply(new BigDecimal(-1));
		} else {
			step = new BigDecimal(0);
		}

		BigDecimal upperBoundStandardParameter = ExpressionCalculator.piNumber.multiply(new BigDecimal(1));
		BigDecimal lowerBoundStandardParameter = ExpressionCalculator.piNumber.multiply(new BigDecimal(0));

		while ((parameter.compareTo(upperBoundStandardParameter) >= 0) ||
				(parameter.compareTo(lowerBoundStandardParameter) < 0)) {

			parameter = parameter.add(step);
		}
	}
}
