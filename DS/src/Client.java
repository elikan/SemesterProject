import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Serializable {
    private String hostName;
    private int portNumber;
    private Socket clientSocket;

    Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        connect();
    }
    
    private void connect() {
        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                ) {
            System.out.println("Client is connected");
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Client client = this;
            outputStream.writeObject(client);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 30121);
    }
}