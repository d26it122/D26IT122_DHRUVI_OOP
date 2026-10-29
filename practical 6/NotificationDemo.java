@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class EmailNotifier implements Notifier, Urgent {
    private final Notifier sender;

    EmailNotifier(Notifier sender) {
        this.sender = sender;
    }

    @Override
    public void send(String message) {
        sender.send(message);
    }
}

class SMSNotifier implements Notifier {
    private final Notifier sender;

    SMSNotifier(Notifier sender) {
        this.sender = sender;
    }

    @Override
    public void send(String message) {
        sender.send(message);
    }
}

public class NotificationDemo {

    public static void main(String[] args) {

        Notifier emailLambda =
                message -> System.out.println("Email: " + message);

        Notifier smsLambda =
                message -> System.out.println("SMS: " + message);

        Notifier email = new EmailNotifier(emailLambda);
        Notifier sms = new SMSNotifier(smsLambda);

        Notifier[] notifiers = {
            email,
            sms
        };

        String message = "Exam starts at 10 AM.";

        for (Notifier notifier : notifiers) {

            notifier.send(message);

            if (notifier instanceof Urgent) {
                notifier.send(message);
            }
        }
    }
}