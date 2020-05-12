package Model;

import com.google.gson.Gson;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutput implements Runnable, PropertyChangeListener {

    private UnnamedPropertyChangeSubject subject;
    private PrintWriter out;
    private Socket socket;

    public ClientOutput(Socket socket, UnnamedPropertyChangeSubject subject) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        this.subject = subject;
        subject.addListener(this);
    }

    @Override
    public void run() {
        while(true)
        {

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Send"))
        {
            Text output = (Text) evt.getNewValue();

            Gson gson = new Gson();
            String outputJson = gson.toJson(output);

            out.println(outputJson);
        }
    }
}
