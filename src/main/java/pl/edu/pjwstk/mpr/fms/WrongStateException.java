package pl.edu.pjwstk.mpr.fms;

public class WrongStateException extends Exception {


    public WrongStateException(String errorMessage){
        super(errorMessage);
    }

}
