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
public class Cos extends CalculationFunction {
	public Cos(ArrayList<String> input){
		super(input);
	}
	public Cos(List<String> input){
		super(input);
	}
	public Cos(BigDecimal parameter){
		super(parameter);
	}
	protected void checkDomain() throws NotInDomainException {

	}
	public BigDecimal calValue(){
        BigDecimal resultCos = new BigDecimal("0.0");
        BigDecimal cosTerm;


		for (int i = 0; i < numOfTerms; i++) {
			cosTerm = parameter.pow(2 * i);
            cosTerm = cosTerm.multiply((new BigDecimal(-1)).pow(i));
            cosTerm = cosTerm.divide(fact(2 * i) , ExpressionCalculator.scale, RoundingMode.HALF_EVEN);
            resultCos = resultCos.add(cosTerm);
		}
		return resultCos;
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
