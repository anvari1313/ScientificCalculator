package ScientificCalculator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ahmad on 3/23/16.
 */
public class Tokenizer {
    private String input;
    private ArrayList<String> tokens;
    private Iterator<String> iterator;

    private final int OPERATOR_CHARACTER = 120;
    private final int NUMBER_CHARACTER = 145;
    private final int ALPHABET_CHARACTER = 190;
    private final int SPACE_CHARACTER = 195;


    public static final int OPERATOR_TOKEN = 900;
    public static final int NUMBER_TOKEN = 910;
    public static final int IDENTIFIER_TOKEN = 920;
    public static final int FUNCTION_TOKEN = 930;
    public static final int EXP_NUMBER = 940;
    public static final int PI_NUMBER = 950;

    /**
     * Get the input expression to be tokenize
     * @param lineInput The input expression to be tokenize
     */
    public Tokenizer(String lineInput){
        this.input = lineInput.toLowerCase();   //  Lowercase the input to omit the case sensivity from the input
        this.tokens = new ArrayList<>();
        startToken();
        iterator = tokens.iterator();
    }

    private int getCharType(char chr){
        switch (chr) {
            case '(':
                return OPERATOR_CHARACTER;
            case ')':
                return OPERATOR_CHARACTER;
            case '+':
                return OPERATOR_CHARACTER;
            case '-':
                return OPERATOR_CHARACTER;
            case '*':
                return OPERATOR_CHARACTER;
            case '/':
                return OPERATOR_CHARACTER;
            case '^':
                return OPERATOR_CHARACTER;
	        case '=':
	            return OPERATOR_CHARACTER;

            case '0':
                return NUMBER_CHARACTER;
            case '1':
                return NUMBER_CHARACTER;
            case '2':
                return NUMBER_CHARACTER;
            case '3':
                return NUMBER_CHARACTER;
            case '4':
                return NUMBER_CHARACTER;
            case '5':
                return NUMBER_CHARACTER;
            case '6':
                return NUMBER_CHARACTER;
            case '7':
                return NUMBER_CHARACTER;
            case '8':
                return NUMBER_CHARACTER;
            case '9':
                return NUMBER_CHARACTER;
            case '.':
                return NUMBER_CHARACTER;

            case ' ':
                return SPACE_CHARACTER;

            default:
                return ALPHABET_CHARACTER;
        }

    }

    private void startToken(){
        String temp = "";
        int lastType = SPACE_CHARACTER;

        for (int i = 0; i < input.length(); i++) {
            char chr = input.charAt(i);
            switch (getCharType(chr)){
                case OPERATOR_CHARACTER:
                    if (!temp.isEmpty()){
                        tokens.add(temp);
                        temp = "";
                    }
                    tokens.add(new String("" + chr));
                    lastType = OPERATOR_CHARACTER;
                    break;
                case NUMBER_CHARACTER:
                    if ((lastType != NUMBER_CHARACTER) && (!temp.isEmpty())){
                        tokens.add(temp);
                        temp = "";
                    }
                    temp += chr;
                    lastType = NUMBER_CHARACTER;
                    break;
                case ALPHABET_CHARACTER:
                    if ((lastType != ALPHABET_CHARACTER) && (!temp.isEmpty())){
                        tokens.add(temp);
                        temp = "";
                    }
                    temp += chr;
                    lastType = ALPHABET_CHARACTER;
                    break;
                case SPACE_CHARACTER:
                    if (!temp.isEmpty()){
                        tokens.add(temp);
                        temp = "";
                    }
                    lastType = SPACE_CHARACTER;
                    break;
            }
        }

        if (!temp.isEmpty()){
            tokens.add(temp);
            temp = "";
        }

    }


    /**
     * Returns the type of given token. Types can be:
     *  -   OPERATOR_TOKEN: Operators are : * - + / ^ ( )
     *  -   NUMBER_TOKEN: Numbers are numbers :D
     *  -   IDENTIFIER_TOKEN: Identifiers are variables
     *  -   FUNCTION_TOKEN: The name of the identified functions
     *  -   EXP_NUMBER: This is the famous e number
     * @param token is the token that should be
     * @return Return the type of the given token
     */
    public static int getTokenType(String token){

        if (  (((token.charAt(0) - '0') <= 9) && ((token.charAt(0) - '0') >= 0)) || (token.charAt(0) == '.') )
            return NUMBER_TOKEN;

	    if (token.compareTo("+") == 0)
		    return OPERATOR_TOKEN;
	    if (token.compareTo("-") == 0)
		    return OPERATOR_TOKEN;
	    if (token.compareTo("*") == 0)
		    return OPERATOR_TOKEN;
	    if (token.compareTo("/") == 0)
		    return OPERATOR_TOKEN;
	    if (token.compareTo("=") == 0)
		    return OPERATOR_TOKEN;
	    if (token.compareTo("^") == 0)
		    return OPERATOR_TOKEN;
        if (token.compareTo("(") == 0)
            return OPERATOR_TOKEN;
        if (token.compareTo(")") == 0)
            return OPERATOR_TOKEN;

        if (token.compareTo("e") == 0)
            return EXP_NUMBER;

        if (token.compareTo("pi") == 0)
            return PI_NUMBER;

        switch (token){
            case "sin":
                return FUNCTION_TOKEN;
            case "cos":
                return FUNCTION_TOKEN;
            case "tan":
                return FUNCTION_TOKEN;
            case "cot":
                return FUNCTION_TOKEN;
	        case "sec":
		        return FUNCTION_TOKEN;
	        case "csc":
		        return FUNCTION_TOKEN;
	        case "sinh":
		        return FUNCTION_TOKEN;
	        case "cosh":
		        return FUNCTION_TOKEN;
	        case "tanh":
		        return FUNCTION_TOKEN;
	        case "coth":
		        return FUNCTION_TOKEN;
            case "exp":
                return FUNCTION_TOKEN;
            case "ln":
                return FUNCTION_TOKEN;
            case "sigma":
                return FUNCTION_TOKEN;
            default:
                return IDENTIFIER_TOKEN;
        }
    }

    /**
     * Returns the next tokens that can be returned
     * @return
     */
    public String nextToken(){
        return iterator.next();
    }

    /**
     * This method checks whether any token is remained to return or not
     * @return true if more token is existed
     */
    public boolean hasNextToken(){
        return iterator.hasNext();
    }

    /**
     * This method converts the toekens to the array of Strings
     * @return Arrays of tokens that is array of strings
     */
    public ArrayList<String> toArrayList(){
        return tokens;
    }
}
