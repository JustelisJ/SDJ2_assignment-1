package Server;

import java.io.IOException;

public class Server
{

    public static void main(String[] args) throws IOException {
        final int port = 6789;

        ServerModel server = new ServerModel(port);
        server.execute();
    }

}