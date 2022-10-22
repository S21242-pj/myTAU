package chainOfResponsibility.validators;

import chainOfResponsibility.Message;

public interface IMessageComponentValidator {

    void validate(Message message) throws MessageValidatorException;
}