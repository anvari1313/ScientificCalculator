package ScientificCalculator.calculationalFunctions;

import ScientificCalculator.exceoptions.SigmaInvalidBoundException;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Created by ahmad on 3/26/16.
 */
public class Sigma {
	private BigDecimal lowerBoundValue;
	private BigDecimal upperBoundValue;
	private ArrayList <String>expression;
	private String variableName;

	public Sigma(List<String> expression,List<String> lBound, List<String> uBound, String variableName) throws SigmaInvalidBoundException {
		this.expression = new ArrayList<String>(expression);
		lowerBoundValue = (new ExpressionCalculator(new ArrayList<>(lBound))).getResult();
		upperBoundValue = (new ExpressionCalculator(new ArrayList<>(uBound))).getResult();
		this.variableName = variableName;
		errorChecking();
	}

	private void errorChecking() throws SigmaInvalidBoundException {
		if (upperBoundValue.compareTo(lowerBoundValue) < 0)
			throw new SigmaInvalidBoundException();
	}

	/**
	 * This method calculates the expression of the sigma
	 * @return the result of the sigma
	 */
	public BigDecimal calSigma(){
		BigDecimal sum = new BigDecimal(0);

		for (BigDecimal iterator = lowerBoundValue;
		     iterator.compareTo(upperBoundValue) <= 0;
		     iterator = iterator.add(new BigDecimal(1))){
			ArrayList expTemp = (ArrayList) this.expression.clone();
			Collections.replaceAll(expTemp,variableName,iterator);
			ExpressionCalculator expressionCalculator = new ExpressionCalculator(expTemp);
			sum = sum.add(expressionCalculator.getResult());
		}

		return sum;
	}
}
