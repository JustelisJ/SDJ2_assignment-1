package Model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private String timeStamp;

    public DateTime()
    {
        timeStamp = currentTime();
    }

    public String currentTime()
    {
        timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
        return timeStamp;
    }

    public String toString()
    {
        return timeStamp;
    }
}
