import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Slave implements Serializable {
    private Type type;
    private Socket socket;
    public Slave(Type type) {
        this.type = type;
        communicate();
    }
    public Slave() {
        this.type = Type.A;
    }

        private void communicate() {
        try (Socket socket = new Socket("127.0.0.1", 30121);
        ) {
            System.out.println("Slave " + type.name() + " is communicating");
            // Sends the slave object
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            Slave slave = new Slave();
            slave.setType(type);
            outputStream.writeObject(slave);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void setType(Type type) {
        this.type = type;
    }

    public static void main(String[] args) {
        Slave slave2 = new Slave(Type.B);
    }
}