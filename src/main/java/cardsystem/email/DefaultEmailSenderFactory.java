package cardsystem.email;

public class DefaultEmailSenderFactory implements EmailSenderFactory {

    public EmailSender getEmailSender() {
        return new AwsSesEmailSender();
    }
}
