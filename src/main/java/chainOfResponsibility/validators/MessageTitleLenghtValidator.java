package chainOfResponsibility.validators;

import chainOfResponsibility.Message;

public class MessageTitleLenghtValidator extends BaseMessageValidatorChainComponent {

    @Override
    protected MessageValidatorException getComponentException() {
        return new MessageValidatorException("Message title is to long");
    }

    @Override
    protected boolean isInnerValidationCorrect(Message message) {
        return message.getTitle().length() < 50;
    }
}