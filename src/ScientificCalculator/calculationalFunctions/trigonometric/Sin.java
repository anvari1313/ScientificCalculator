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
public class Sin extends CalculationFunction {
    public Sin(ArrayList<String> input){
        super(input);
    }
	public Sin(List<String> input){
		super(input);
	}
	public Sin(BigDecimal parameter){
		super(parameter);
	}
	protected void checkDomain() throws NotInDomainException {
		;   // There is nothing to check in this function
	}
    public BigDecimal calValue(){
	    BigDecimal resultSin = new BigDecimal("0.0");
        BigDecimal sinTerm;

	    for (int i = 0; i < numOfTerms; i++) {
		    sinTerm = parameter.pow(2 * i + 1);
		    sinTerm = sinTerm.multiply((new BigDecimal(-1)).pow(i));
		    sinTerm = sinTerm.divide(fact(2 * i + 1), ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
		    resultSin = resultSin.add(sinTerm);
	    }
        return resultSin;
    }
	protected void standardizeParameter(){

		BigDecimal step;
		if ((new BigDecimal(0)).compareTo(parameter) > 0) {   // The parameter is less than zero : alpha < 0
			step = ExpressionCalculator.piNumber.multiply(new BigDecimal(2));
		}else if ((new BigDecimal(0)).compareTo(parameter) < 0) {   // The parameter is more than zero : alpha > 0
			step = ExpressionCalculator.piNumber.multiply(new BigDecimal(-2));
		}else{
			step = new BigDecimal(0);
		}

		BigDecimal uppperBoundStandardParameter = ExpressionCalculator.piNumber.multiply(new BigDecimal(2));
		BigDecimal lowerBoundStandardParameter = new BigDecimal(0);

		while ((parameter.compareTo(uppperBoundStandardParameter) >= 0) ||
				(parameter.compareTo(lowerBoundStandardParameter) < 0)) {
			parameter = parameter.add(step);
		}
	}
}
