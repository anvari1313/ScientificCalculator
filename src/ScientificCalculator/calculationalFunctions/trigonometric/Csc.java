package ScientificCalculator.calculationalFunctions.trigonometric;

import ScientificCalculator.calculationalFunctions.CalculationFunction;
import ScientificCalculator.calculationalFunctions.ExpressionCalculator;
import ScientificCalculator.calculationalFunctions.Sigma;
import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static ScientificCalculator.calculationalFunctions.ExpressionCalculator.*;

/**
 * Created by ahmad on 4/1/16.
 */
public class Csc extends CalculationFunction {

	public Csc(ArrayList<String> input) throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public Csc(List<String> input)throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public BigDecimal calValue() {
		Sin sin = new Sin(parameter);
		return (new BigDecimal(1)).divide(sin.calValue(), scale, RoundingMode.HALF_EVEN);
	}

	protected void checkDomain() throws NotInDomainException{
		if ((parameter.compareTo(new BigDecimal(0)) == 0) ||
				(parameter.compareTo(piNumber) == 0) ||
				(parameter.compareTo(piNumber.multiply(new BigDecimal(2))) == 0)) {
			throw new NotInDomainException("csc");
		}

	}

	protected void standardizeParameter() {
		BigDecimal step;
		if ((new BigDecimal(0)).compareTo(parameter) > 0) {   // The parameter is less than zero : alpha < 0
			step = piNumber.multiply(new BigDecimal(2));
		}else if ((new BigDecimal(0)).compareTo(parameter) < 0) {   // The parameter is more than zero : alpha > 0
			step = piNumber.multiply(new BigDecimal(-2));
		}else{
			step = new BigDecimal(0);
		}

		BigDecimal uppperBoundStandardParameter = piNumber.multiply(new BigDecimal(2));
		BigDecimal lowerBoundStandardParameter = new BigDecimal(0);

		while ((parameter.compareTo(uppperBoundStandardParameter) >= 0) ||
				(parameter.compareTo(lowerBoundStandardParameter) < 0)) {
			parameter = parameter.add(step);
		}
	}
}
