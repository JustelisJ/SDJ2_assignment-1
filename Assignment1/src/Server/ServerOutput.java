package Server;

import Model.Text;
import com.google.gson.Gson;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerOutput implements Runnable, PropertyChangeListener {

    private Socket socket;
    private PrintWriter out;
    private UnnamedPropertyChangeSubject subjectInput;
    private UnnamedPropertyChangeSubject subjectModel;
    private ArrayList<Socket> sockets;

    public ServerOutput(Socket socket, ArrayList<Socket> sockets, UnnamedPropertyChangeSubject subjectInput,
                        UnnamedPropertyChangeSubject subjectModel) throws IOException {
        this.socket = socket;
        this.subjectInput = subjectInput;
        subjectInput.addListener(this);
        this.subjectModel = subjectModel;
        subjectModel.addListener(this);
        out = new PrintWriter(socket.getOutputStream(), true);
        this.sockets = sockets;
    }

    @Override
    public void run()
    {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("TEXT"))
        {
            Text output = (Text) evt.getNewValue();
            System.out.println(output);

            Gson gson = new Gson();
            String outputJson = gson.toJson(output);

            for(int i = 0; i < sockets.size(); i++) {
                try {
                    out = new PrintWriter(sockets.get(i).getOutputStream(), true);
                    out.println(outputJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
