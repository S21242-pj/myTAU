package pl.edu.pjwstk.mpr.chainOfResponsibility.validators;

import pl.edu.pjwstk.mpr.chainOfResponsibility.Message;

public interface IMessageComponentValidator {

    void validate(Message message) throws MessageValidatorException;
}