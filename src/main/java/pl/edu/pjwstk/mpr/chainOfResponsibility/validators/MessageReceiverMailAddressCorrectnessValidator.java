package pl.edu.pjwstk.mpr.chainOfResponsibility.validators;

import pl.edu.pjwstk.mpr.chainOfResponsibility.Message;

public class MessageReceiverMailAddressCorrectnessValidator extends BaseMessageValidatorChainComponent {

    @Override
    protected MessageValidatorException getComponentException() {
        return new MessageValidatorException("Wrong receiver mail address pattern");
    }

    @Override
    protected boolean isInnerValidationCorrect(Message message) {
        return message.getReceiverAddress().matches("\\S+@\\S+\\.\\S{2,3}");
    }
}