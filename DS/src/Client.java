import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Serializable {
    private String hostName;
    private int portNumber;
    private Socket clientSocket;
    int ID;

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
            //flush is to clear the output stream
            outputStream.flush();
            createjob();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createjob() throws IOException {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("What type do you want A or B; ");
        userInput = stdIn.readLine();
        String jtype=userInput.toString();
        Type T = null;
        if(jtype=="A"){
            T=Type.A;
        }
        else{
            T=Type.B;
        }
        Job job = new Job(T,ID);
        ID++;
    }

    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 30121);
    }
}