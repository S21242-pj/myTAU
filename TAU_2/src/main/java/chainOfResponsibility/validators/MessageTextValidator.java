package chainOfResponsibility.validators;

import chainOfResponsibility.Message;

public class MessageTextValidator extends StringEmptinessValidator {

    @Override
    protected MessageValidatorException getComponentException() {
        return new MessageValidatorException("No text content provided");
    }

    @Override
    protected boolean isInnerValidationCorrect(Message message) {
        return isStringPopulated(message.getMessage());
    }
}