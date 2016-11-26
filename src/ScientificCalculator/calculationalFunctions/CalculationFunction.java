package ScientificCalculator.calculationalFunctions;

import ScientificCalculator.exceoptions.NotInDomainException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 3/26/16.
 */
public abstract class CalculationFunction {
    protected static final int numOfTerms = 40;
    protected BigDecimal parameter;

	/**
     * This constructor would get the parameter expression of function and calculate the expression with the ExpressionCalculator class and put it into the parameter field.
     * @param input is an ArrayList of tokens of the expression
     */
    public CalculationFunction(ArrayList<String> input){
        ExpressionCalculator expressionCalculator = new ExpressionCalculator(input);
        this.parameter = expressionCalculator.getResult();
	    this.standardizeParameter();
    }

	/**
	 * This constructor would get the parameter expression of function and calculate the expression with the ExpressionCalculator class and put it into the parameter field.
	 * @param input is an List of tokens of the expression
	 */
    public CalculationFunction(List<String> input){
	    this(new ArrayList<>(input));
    }

	/**
	 * This constructor gets a BigDecimal as the parameter of the function not an exression
	 * @param parameter the parameter for the function
	 */
	public CalculationFunction(BigDecimal parameter){
		this.parameter = parameter;
		this.standardizeParameter();
	}

	/**
	 * A factorial method implemented with BigDecimal
	 * @param i parameter of the factorial function
	 * @return i!
	 */
    public static BigDecimal fact(int i){
        BigDecimal res = new BigDecimal(1);
        for (int j = 1; j <= i; j++) {
            res = res.multiply(new BigDecimal(j));
        }

        return res;
    }

	/**
	 * This function calculates
	 * @param p
	 * @param i
	 * @return
	 */
	public static BigDecimal newFactoriel(BigDecimal p,int i){
		BigDecimal res = new BigDecimal(1);

		for (int j = 0; j < i; j++) {
			p = p.subtract(new BigDecimal(j));
			res = res.multiply(p);
		}

		return res;
	}

	/**
	 * To get a better result the input parameter of every function should be limited to specific intervals.
	 * For example we limit the input parameter of sin function to the [0,2pi) interval.
	 * Caution: This method is NOT a domain check.
	 */
	protected abstract void standardizeParameter();

	/**
	 * This method should be implemented with the specific procedure of calculating with the Maclaurin's series.
	 * @return the result of that function
	 */
    public abstract BigDecimal calValue();

	protected abstract void checkDomain() throws NotInDomainException;
}
