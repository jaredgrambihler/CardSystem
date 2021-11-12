package cardsystem.email;

public class AwsEmailSenderFactory implements EmailSenderFactory {

    public EmailSender getEmailSender() {
        return new AwsSesEmailSender();
    }
}
