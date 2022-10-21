package pl.edu.pjwstk.mpr.chainOfResponsibility;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjwstk.mpr.chainOfResponsibility.validators.MessageValidatorException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MailSenderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void can_create_mail_sender() {
        var sender = new MailSender();

        assertThat(sender).isNotNull();
    }

    @Test
    public void can_send_correctly_filled_mail() {
        var sender = new MailSender();

        sender.sendMail("s21242@pjwstk.edu.pl", "mail title", "mail message text");

        assertThat(outContent.toString()).isEqualTo("Mail sent correctly!");
    }

    @Test
    public void can_not_send_mail_with_no_receiver_address_case_1() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail(null, "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No receiver mail address provided");
    }

    @Test
    public void can_not_send_mail_with_no_receiver_address_case_2() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No receiver mail address provided");
    }

    @Test
    public void can_not_send_mail_with_no_receiver_address_case_3() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("            ", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No receiver mail address provided");
    }

    @Test
    public void can_not_send_mail_with_wrong_receiver_address_case_1() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("szymon", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("Wrong receiver mail address pattern");
    }

    @Test
    public void can_not_send_mail_with_wrong_receiver_address_case_2() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("szymon.com", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("Wrong receiver mail address pattern");
    }

    @Test
    public void can_not_send_mail_with_wrong_receiver_address_case_3() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("szymon.mail", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("Wrong receiver mail address pattern");
    }

    @Test
    public void can_not_send_mail_with_wrong_receiver_address_case_4() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk", "mail title", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("Wrong receiver mail address pattern");
    }

    @Test
    public void can_not_send_mail_with_no_title_case_1() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No message title provided");
    }

    @Test
    public void can_not_send_mail_with_no_title_case_2() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", null, "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No message title provided");
    }

    @Test
    public void can_not_send_mail_with_no_title_case_3() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "            ", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No message title provided");
    }

    @Test
    public void can_not_send_mail_with_too_long_title() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "abcdefghijklmnopqrstuwvxyzabcdefghijklmnopqrstuwvxyz", "mail text"))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("Message title is to long");
    }

    @Test
    public void can_not_send_mail_with_no_message_text_case_1() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "message title", ""))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No text content provided");
    }

    @Test
    public void can_not_send_mail_with_no_message_text_case_2() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "message title", null))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No text content provided");
    }

    @Test
    public void can_not_send_mail_with_no_message_text_case_3() {
        var sender = new MailSender();

        assertThatThrownBy(() -> sender.sendMail("s21242@pjwstk.edu.pl", "message title", "           "))
                .isInstanceOf(MailSendingException.class)
                .hasCauseInstanceOf(MessageValidatorException.class)
                .hasStackTraceContaining("No text content provided");
    }
}