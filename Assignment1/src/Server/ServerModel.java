package Server;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerModel implements UnnamedPropertyChangeSubject {

    private ServerSocket welcomeSocket;
    private ArrayList<Socket> sockets;
    private PropertyChangeSupport property;

    public ServerModel(int port) throws IOException {
        welcomeSocket = new ServerSocket(port);
        sockets = new ArrayList<Socket>();
        this.property = new PropertyChangeSupport(this);
        System.out.println("Starting server... (Tip - dont forget to change the IP in the ClientModel :) )");
    }

    public void execute() throws IOException {
        while(true)
        {
            Socket socket = welcomeSocket.accept();
            System.out.println(socket.getInetAddress().getHostAddress() + " connected");
            sockets.add(socket);

            ServerInput input = new ServerInput(socket, this);
            ServerOutput output = new ServerOutput(socket, sockets, input, this);

            Thread clientInput = new Thread(input);
            Thread clientOutput = new Thread(output);
            clientInput.start();
            clientOutput.start();
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
