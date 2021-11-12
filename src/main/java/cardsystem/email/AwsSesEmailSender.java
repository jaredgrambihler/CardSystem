package cardsystem.email;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

public class AwsSesEmailSender implements EmailSender {

    @Override
    public void send(Email email) {
        if (!email.userIsOptedIn()) {
            return;
        }
        try {
            getClient().sendEmail(createSendEmailRequest(email));
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }

    private AmazonSimpleEmailService getClient() {
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
    }

    private SendEmailRequest createSendEmailRequest(Email email) {
        String textContent = email.getHeader() + "\n" + email.getBody() + "\n" + email.getFooter();
        return new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(email.getToAddress())
                )
                .withMessage(new Message()
                        .withBody(new Body()
                                .withText(new Content()
                                        .withCharset("UTF-8")
                                        .withData(textContent)
                                )
                        )
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData(email.getSubject())
                        )
                )
                .withSource(email.getFromAddress());
    }
}
