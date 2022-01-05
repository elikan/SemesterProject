import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
    private int portNumber;
    private ArrayList<Object> clients = new ArrayList<Object>();
    private ArrayList<Job> jobs = new ArrayList<Job>();
    private Socket socket;
    ObjectInputStream objectInputStream;

    public Master(int portNumber) {
        this.portNumber = portNumber;
        startprogram();
    }



    public void startprogram() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            // As long as have room for clients. We continue to accept.
            while (clients.size() < 4) {
                socket = serverSocket.accept();
                System.out.println("Connecting");
                InputStream requestReader = socket.getInputStream();
                objectInputStream = new ObjectInputStream(requestReader);
                Object O = objectInputStream.readObject();
                if (O.getClass()== Slave.class) {
                    System.out.println("Slave Connected");
                } else {
                    System.out.println("Client Connected");
                }
                clients.add(O);
                getJob();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    public void getJob() throws IOException, ClassNotFoundException {
        jobs.add((Job)objectInputStream.readObject());
    }
    public static void main(String[] args) {
        Master master = new Master(30121);
    }
}