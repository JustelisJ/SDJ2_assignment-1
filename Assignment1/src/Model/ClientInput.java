package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.google.gson.Gson;

public class ClientInput implements Runnable{

    private BufferedReader in;
    private Socket socket;
    private ChatModel model;

    public ClientInput(Socket socket, ChatModel model) throws IOException {
        this.socket = socket;
        this.model = model;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {

        try {
            while(true)
            {
                String JsonText = in.readLine();
                Gson gson = new Gson();
                Text text = gson.fromJson(JsonText, Text.class);
                model.gotAText(text);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
