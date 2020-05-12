package Server;

import Model.Text;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import com.google.gson.Gson;
import utility.observer.subject.UnnamedPropertyChangeSubject;

public class ServerInput implements Runnable, UnnamedPropertyChangeSubject {

    private Socket socket;
    private BufferedReader in;
    private PropertyChangeSupport property;
    private ServerModel model;

    public ServerInput(Socket socket, ServerModel model) throws IOException {
        this.socket = socket;
        this.model = model;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        try {
            while(true)
            {
                String JsonText = in.readLine();

                Gson gson = new Gson();
                Text text = gson.fromJson(JsonText, Text.class);
                if(text.getMessage() != null && !text.getMessage().isBlank()) {
                    property.firePropertyChange("TEXT", null, text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
