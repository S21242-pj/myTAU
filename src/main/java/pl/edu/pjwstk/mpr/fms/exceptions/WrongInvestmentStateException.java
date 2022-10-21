package pl.edu.pjwstk.mpr.fms.exceptions;

public class WrongInvestmentStateException extends RuntimeException {

    public WrongInvestmentStateException(String errorMessage) {
        super(errorMessage);
    }
}