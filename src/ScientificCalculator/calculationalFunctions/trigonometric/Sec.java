package ScientificCalculator.calculationalFunctions.trigonometric;

import ScientificCalculator.calculationalFunctions.CalculationFunction;
import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static ScientificCalculator.calculationalFunctions.ExpressionCalculator.piNumber;
import static ScientificCalculator.calculationalFunctions.ExpressionCalculator.scale;

/**
 * Created by ahmad on 3/25/16.
 */
public class Sec extends CalculationFunction {

	public Sec(ArrayList<String> input) throws NotInDomainException {
		super(input);
	}

	public Sec(List<String> input)throws NotInDomainException {
		super(input);
	}

	public BigDecimal calValue() {
		Cos sin = new Cos(parameter);
		return (new BigDecimal(1)).divide(sin.calValue(), scale, RoundingMode.HALF_EVEN);
	}

	protected void checkDomain() throws NotInDomainException{
		if ((parameter.compareTo(piNumber.divide(new BigDecimal(2), scale,RoundingMode.HALF_EVEN)) == 0) ||
				(parameter.compareTo(piNumber.multiply(new BigDecimal(3/2))) == 0)) {
			throw new NotInDomainException("sec");
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
