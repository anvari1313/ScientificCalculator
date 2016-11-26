package ScientificCalculator.calculationalFunctions;

import ScientificCalculator.calculationalFunctions.trigonometric.Cos;
import ScientificCalculator.calculationalFunctions.trigonometric.Sin;
import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 3/26/16.
 */
public class Ln extends CalculationFunction {

	BigDecimal finalResult;

	public Ln(ArrayList<String> input) throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public Ln(List<String> input)throws NotInDomainException {
		super(input);
		checkDomain();
	}

	public BigDecimal calValue() {
		BigDecimal localResult = new BigDecimal("0.0");
		BigDecimal term;

		parameter = parameter.subtract(new BigDecimal(1));

		for (int i = 1; i <= numOfTerms; i++) {
			term = parameter.pow( i + 1);
			term = term.multiply((new BigDecimal(-1)).pow(i));
			term = term.divide(new BigDecimal(i + 1), ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
			localResult = localResult.add(term);
		}
		BigDecimal result = finalResult.add(localResult);
		return result;
	}

	protected void checkDomain() throws NotInDomainException{
		if (parameter.compareTo(new BigDecimal(0)) <= 0)
			throw new NotInDomainException("ln");

	}

	protected void standardizeParameter() {
		finalResult = new BigDecimal(0);

		BigDecimal upperBoundStandardParameter = new BigDecimal(2);

		while ((parameter.compareTo(upperBoundStandardParameter) >= 0)) {
			parameter = parameter.divide(ExpressionCalculator.eNumber, ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
			finalResult = finalResult.add(new BigDecimal(1));
		}

	}

}
