package chainOfResponsibility.validators;

import chainOfResponsibility.Message;

public class MessageReceiverMailAddressValidator extends StringEmptinessValidator {

    @Override
    protected MessageValidatorException getComponentException() {
        return new MessageValidatorException("No receiver mail address provided");
    }

    @Override
    protected boolean isInnerValidationCorrect(Message message) {
        return isStringPopulated(message.getReceiverAddress());
    }
}