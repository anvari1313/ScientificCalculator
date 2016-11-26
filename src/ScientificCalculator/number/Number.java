package ScientificCalculator.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * This is a bigDecimal that has special
 * Created by ahmad on 3/26/16.
 */
public class Number extends BigDecimal {
	public Number(char[] chars, int i, int i1) {
		super(chars, i, i1);
	}

	public Number(char[] chars, int i, int i1, MathContext mathContext) {
		super(chars, i, i1, mathContext);
	}

	public Number(char[] chars) {
		super(chars);
	}

	public Number(char[] chars, MathContext mathContext) {
		super(chars, mathContext);
	}

	public Number(String s) {
		super(s);
	}

	public Number(String s, MathContext mathContext) {
		super(s, mathContext);
	}

	public Number(double v) {
		super(v);
	}

	public Number(double v, MathContext mathContext) {
		super(v, mathContext);
	}

	public Number(BigInteger bigInteger) {
		super(bigInteger);
	}

	public Number(BigInteger bigInteger, MathContext mathContext) {
		super(bigInteger, mathContext);
	}

	public Number(BigInteger bigInteger, int i) {
		super(bigInteger, i);
	}

	public Number(BigInteger bigInteger, int i, MathContext mathContext) {
		super(bigInteger, i, mathContext);
	}

	public Number(int i) {
		super(i);
	}

	public Number(int i, MathContext mathContext) {
		super(i, mathContext);
	}

	public Number(long l) {
		super(l);
	}

	public Number(long l, MathContext mathContext) {
		super(l, mathContext);
	}

	public Number add(Number n){
		return (Number) super.add(n);
	}
}
