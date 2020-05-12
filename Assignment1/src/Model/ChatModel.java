package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class ChatModel implements Model {

    private String name;
    private final int port = 6789;
    private PropertyChangeSupport property;

    public ChatModel() {
        name = "";
        property = new PropertyChangeSupport(this);
    }

    private void connectToServer()
    {
        try {
            Socket socket = new Socket("localhost", port);
            Thread clientInput = new Thread(new ClientInput(socket, this));
            Thread clientOutput = new Thread(new ClientOutput(socket, this));
            clientInput.start();
            clientOutput.start();
            Text text = new Text("<Connected>", name);
            property.firePropertyChange("Send", "", text);
        } catch (IOException e) {

        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
        connectToServer();
    }

    @Override
    public void sendText(String message) {
        Text text = new Text(message, name);
        property.firePropertyChange("Send", "", text);
    }

    @Override
    public void gotAText(Text text) {
        property.firePropertyChange("Receive", "", text);
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
