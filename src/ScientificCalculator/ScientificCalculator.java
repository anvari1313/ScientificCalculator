package ScientificCalculator;

import ScientificCalculator.calculationalFunctions.ExpressionCalculator;
import ScientificCalculator.exceoptions.NotInDomainException;
import ScientificCalculator.number.Number;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by ahmad on 3/23/16.
 */
public class ScientificCalculator {

    public static void main(String[] args) {
	    Tokenizer tokenizer = new Tokenizer((new Scanner(System.in)).nextLine());
        ArrayList tokes = tokenizer.toArrayList();

	    ExpressionCalculator cal = new ExpressionCalculator(tokes);

	    System.out.println(cal.getResult());



    }
}
