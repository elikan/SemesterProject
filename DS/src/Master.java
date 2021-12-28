import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
    private int portNumber;
    private ArrayList<Object> clients = new ArrayList<Object>();
    private Socket socket;

    public Master(int portNumber) {
        this.portNumber = portNumber;
        startprogram();
    }



    public void startprogram() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            while (clients.size() < 4) {
                socket = serverSocket.accept();
                System.out.println("Connecting");
                InputStream requestReader = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(requestReader);
                Object O = objectInputStream.readObject();
                if (O instanceof Slave) {
                    Slave slave = (Slave) O;
                    System.out.println("Slave Connected");
                } else {
                    Client client = (Client) O;
                    System.out.println("Client Connected");

                }
                clients.add(O);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Master master = new Master(30121);
    }
}