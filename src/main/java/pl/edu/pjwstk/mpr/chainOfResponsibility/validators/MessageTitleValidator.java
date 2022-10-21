package pl.edu.pjwstk.mpr.chainOfResponsibility.validators;

import pl.edu.pjwstk.mpr.chainOfResponsibility.Message;

public class MessageTitleValidator extends StringEmptinessValidator{

    @Override
    protected MessageValidatorException getComponentException() {
        return new MessageValidatorException("No message title provided");
    }

    @Override
    protected boolean isInnerValidationCorrect(Message message) {
        return isStringPopulated(message.getTitle());
    }
}