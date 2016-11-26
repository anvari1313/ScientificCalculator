package ScientificCalculator.exceoptions;

/**
 * Created by ahmad on 3/26/16.
 */
public class NotInDomainException extends Exception {
    public NotInDomainException(){}

    public NotInDomainException(String functionName){
        super(functionName);
    }
}
