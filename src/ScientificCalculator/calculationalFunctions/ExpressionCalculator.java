package ScientificCalculator.calculationalFunctions;


import ScientificCalculator.Tokenizer;
import ScientificCalculator.calculationalFunctions.hyperbolic.Cosh;
import ScientificCalculator.calculationalFunctions.hyperbolic.Coth;
import ScientificCalculator.calculationalFunctions.hyperbolic.Sinh;
import ScientificCalculator.calculationalFunctions.hyperbolic.Tanh;
import ScientificCalculator.calculationalFunctions.trigonometric.*;
import ScientificCalculator.exceoptions.NotIdentifiedFunctionException;
import ScientificCalculator.exceoptions.NotInDomainException;
import ScientificCalculator.exceoptions.SigmaInvalidBoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by ahmad on 3/26/16.
 */
public class ExpressionCalculator {
	private Stack<BigDecimal> operands;
	private Stack<String> operators;

	public static int scale = 50;
	public static BigDecimal piNumber;
	public static BigDecimal eNumber;

	public ExpressionCalculator(ArrayList<String> tokens) {
		this.operands = new Stack<>();
		this.operators = new Stack<>();
		this.piNumber = new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481");
		this.eNumber = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956");
		this.piNumber = this.piNumber.setScale(scale,RoundingMode.HALF_EVEN);
		this.eNumber = this.eNumber.setScale(scale,RoundingMode.HALF_EVEN);
		try {
			pushToStacks(tokens);
		} catch (NotIdentifiedFunctionException e) {
			e.printStackTrace();
		}
	}


	private int getPriority(String operator) {
		switch (operator) {
			case ")":
				return -900;
			case "(":
				return 900;
			case "^":
				return 40;
			case "*":
				return 30;
			case "/":
				return 30;
			case "+":
				return 20;
			case "-":
				return 20;
			case "=":
				return 10;
			default:
				System.out.println(operator);
				System.err.println("Unknown operator");
				return -1;
		}
	}

	private void add() {
		BigDecimal operand1 = this.operands.pop();
		BigDecimal operand2 = this.operands.pop();

		this.operands.push(operand1.add(operand2));
	}

	private void sub() {
		BigDecimal operand1 = this.operands.pop();
		BigDecimal operand2 = this.operands.pop();

		this.operands.push(operand2.subtract(operand1));
	}

	private void multiply() {
		BigDecimal operand1 = this.operands.pop();
		BigDecimal operand2 = this.operands.pop();

		this.operands.push(operand1.multiply(operand2));
	}

	private void div() throws ArithmeticException {
		BigDecimal operand1 = this.operands.pop();
		BigDecimal operand2 = this.operands.pop();

		this.operands.push(operand2.divide(operand1, scale, RoundingMode.HALF_EVEN));
	}

	private void pow() {
		BigDecimal power = this.operands.pop();
		BigDecimal base = this.operands.pop();

		base = base.subtract(new BigDecimal(1));

		BigDecimal res = new BigDecimal(0);
		BigDecimal term;
		for (int i = 0; i < CalculationFunction.numOfTerms; i++) {
			term = CalculationFunction.newFactoriel(power,i);
			term = term.divide(CalculationFunction.fact(i),scale,RoundingMode.HALF_EVEN);
			term = term.multiply(base.pow(i));
			res = res.add(term);
		}

		this.operands.push(res);
	}

	private void equal() {
		BigDecimal operand1 = this.operands.pop();
		BigDecimal operand2 = this.operands.pop();

		if (operand1.compareTo(operand2) == 0)
			this.operands.push(new BigDecimal(1));
		else
			this.operands.push(new BigDecimal(0));
	}

	private void calOperator(String operator) {
		switch (operator) {
			case "^":
				pow();
				break;
			case "*":
				multiply();
				break;
			case "/":
				try {
					div();
				}catch (ArithmeticException e){
					System.err.println("Division By zero has happend!");
					System.exit(0);
					return;
				}
				break;
			case "+":
				add();
				break;
			case "-":
				sub();
				break;
			case "=":
				equal();
				break;
			case ")":
				while (operator.compareTo("(") != 0) {
					operator = this.operators.pop();
					calOperator(operator);
				}
				break;
			case "(":
				return;     //  Do nothing for the (
			default:
				System.out.println(operator);
				System.err.println("Unknown operator");
		}
	}

	public BigDecimal getResult() {
		BigDecimal res;
		while (!this.operators.isEmpty()) {
			calOperator(this.operators.pop());
		}

		res = this.operands.pop();
		this.operands.push(res);     // To always have the result in the stack
		return res;
	}

	private void pushToStacks(ArrayList<String> tokens) throws NotIdentifiedFunctionException {
		boolean minus = false;

		// To check the expression if the first token is minus
		Iterator firstToke = tokens.iterator();
		if (firstToke.hasNext())
			if (firstToke.next().toString().compareTo("-") == 0) {
				minus = true;
				firstToke.remove();
			}

		Object[] tokearr = tokens.toArray();

		for (int i = 0; i < tokearr.length; i++) {

			switch (Tokenizer.getTokenType(tokearr[i].toString())) {

				case Tokenizer.FUNCTION_TOKEN:


					String functionName =tokearr[i].toString();
					CalculationFunction function;

					int parenthesis = 0;
					int startExpIndex = i;

					do {
						if ("(".compareTo(tokearr[i + 1].toString()) == 0)
							parenthesis ++;
						else if (")".compareTo(tokearr[i + 1].toString()) == 0)
							parenthesis --;
						else
							;
						i ++;
					}while (parenthesis != 0);

					if ("sigma".compareTo(functionName) == 0){
						int endExpIndex = i + 1;
						i++;    //  i points to the 'for' token
						i++;    //  i points to the variable token
						String variable = tokearr[i].toString();
						i++;    //  i points to the 'from' token
						i++;    //  i points to the first token of the lowerBound of sigma
						int startLBIndex = i;
						i--;
						do {
							if ("from".compareTo(tokearr[i].toString()) == 0)
								parenthesis ++;
							else if ("to".compareTo(tokearr[i].toString()) == 0)
								parenthesis --;
							else
								;
							i ++;
						}while (parenthesis != 0);
						int endLBIndex = i-1;
						//i++;
						int startUBIndex = i;
						do {
							if ("(".compareTo(tokearr[i].toString()) == 0)
								parenthesis ++;
							else if (")".compareTo(tokearr[i].toString()) == 0)
								parenthesis --;
							else
								;
							i ++;
						}while (parenthesis != 0);
						int endUBIndex = i;
						Sigma sigma = null;
						try {
							sigma = new Sigma(tokens.subList(startExpIndex + 1,endExpIndex)
									,tokens.subList(startLBIndex,endLBIndex),tokens.subList(startUBIndex,endUBIndex),variable);
						} catch (SigmaInvalidBoundException e) {
							System.err.println(e.getMessage());
							System.exit(0);
							return;
						}
						this.operands.push(sigma.calSigma());
						break;
					}

					switch (functionName){
						case "sin":
							function = new Sin(tokens.subList(startExpIndex + 1,i + 1));
							break;
						case "cos":
							function = new Cos(tokens.subList(startExpIndex + 1,i + 1));
							break;
						case "tan":
							try {
								function = new Tan(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;
						case "cot":
							try {
								function = new Cot(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;
						case "sec":
							try {
								function = new Sec(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;
						case "csc":
							try {
								function = new Csc(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;
						case "sinh":
							function = new Sinh(tokens.subList(startExpIndex + 1,i + 1));
							break;
						case "cosh":
							function = new Cosh(tokens.subList(startExpIndex + 1,i + 1));
							break;
						case "tanh":
							function = new Tanh(tokens.subList(startExpIndex + 1,i + 1));
							break;
						case "coth":
							try {
								function = new Coth(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;
						case "ln":
							try {
								function = new Ln(tokens.subList(startExpIndex + 1 , i + 1));
							} catch (NotInDomainException e) {
								System.err.println(e.getMessage());
								System.exit(0);
								return;
							}
							break;

						case "exp":
							function = new Exp(tokens.subList(startExpIndex + 1,i + 1));
							break;
						default:
							throw new NotIdentifiedFunctionException(tokearr[i].toString());
					}
					operands.push(function.calValue());
					break;

				case Tokenizer.IDENTIFIER_TOKEN:

					break;
				case Tokenizer.NUMBER_TOKEN:
					if (minus == true) {
						operands.add((new BigDecimal(tokearr[i].toString())).multiply(new BigDecimal(-1)));
						minus = false;
					} else
						operands.add(new BigDecimal(tokearr[i].toString()));
					break;
				case Tokenizer.OPERATOR_TOKEN:

					if (!this.operators.isEmpty()) {
						String lastOperator = this.operators.pop();

						if ((lastOperator.compareTo("(") != 0) && (getPriority(lastOperator) > getPriority(tokearr[i].toString()))) {
							calOperator(lastOperator);
						} else
							this.operators.push(lastOperator);

					}
					this.operators.push(tokearr[i].toString());
					if (i != tokearr.length - 1) {
						if (tokearr[i + 1].toString().compareTo("-") == 0) {
							minus = true;
							i++;
						}
					}
					break;
				case Tokenizer.PI_NUMBER:
					if (minus == true) {
						operands.add(piNumber.multiply(new BigDecimal(-1)));
						minus = false;
					} else
						operands.add(piNumber);
					break;
				case Tokenizer.EXP_NUMBER:
					if (minus == true) {
						operands.add(eNumber.multiply(new BigDecimal(-1)));
						minus = false;
					} else
						operands.add(eNumber);
					break;
			}
		}
	}
}
