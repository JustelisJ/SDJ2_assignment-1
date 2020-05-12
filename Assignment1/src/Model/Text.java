package Model;

public class Text {

    private String message;
    private String sender;
    private DateTime time;

    public Text(String message, String sender) {
        this.message = message;
        this.sender = sender;
        time = new DateTime();
    }

    public String getMessage()
    {
        return message;
    }

    public String toString()
    {
        return "\n" + time + ") " + sender + "> " + message;
    }
}
