package chainOfResponsibility;

public class MailSendingException extends RuntimeException {

    MailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
