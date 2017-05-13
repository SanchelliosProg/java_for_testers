package mantis.model;

/**
 * Created by Александр on 13.05.2017.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
