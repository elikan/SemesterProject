import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

enum slaveType {A, B}

public class Slave implements Serializable {
    private final slaveType type;
    private Socket socket;

    public Slave(slaveType type) {
        this.type = type;
        communicate();
    }

    private void communicate() {
        try (
                Socket socket = new Socket("127.0.0.1", 30121);
        ) {
            System.out.println("Slave " + type.name() + " is communicating");
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            Slave slave = this;
            outputStream.writeObject(slave);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Slave slave1 = new Slave(slaveType.A);
        Slave slave2 = new Slave(slaveType.B);
    }
}