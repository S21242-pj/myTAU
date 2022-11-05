package chainOfResponsibility;

public class DemoCoR {
    public static void main(String[] args) {
        var mailSender = new MailSender();
        mailSender.sendMail("s21242@pjwstk.edu.pl", "mail title", "mail message text");
        System.out.print("mail sender is: " + mailSender);
    }
}
