package pl.edu.pjwstk.mpr.chainOfResponsibility;

import pl.edu.pjwstk.mpr.chainOfResponsibility.validators.*;

public class MailSender {

    private BaseMessageValidatorChainComponent chainOfValidators;

    public MailSender() {
        createChainOfValidators();
    }

    private void createChainOfValidators() {
        var step1 = new MessageReceiverMailAddressValidator();
        var step2 = new MessageReceiverMailAddressCorrectnessValidator();
        var step3 = new MessageTitleValidator();
        var step4 = new MessageTitleLenghtValidator();
        var step5 = new MessageTextValidator();

        step1.setNextChainNode(step2);
        step2.setNextChainNode(step3);
        step3.setNextChainNode(step4);
        step4.setNextChainNode(step5);
        this.chainOfValidators = step1;
    }

    public void sendMail(String receiverAddress, String title, String message)  {
        try {
            var mail = new Message(title, message, receiverAddress);
            chainOfValidators.validate(mail);
            System.out.println("Mail sent correctly!");
        } catch (MessageValidatorException exception) {
            throw new MailSendingException("Couldn't send the mail because validation error occurred: ", exception);
        }
    }
}