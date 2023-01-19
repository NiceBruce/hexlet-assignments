package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    Connected connected;
    Disconnected disconnected;

    public Connection state;
    public String connectionState;
    public String data;
    private String ip;
    private int port;


    TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return connectionState;
    }

    public void connect() {
        this.state.connect();
    }

    public void disconnect() {
        this.state.disconnect();
    }

    public void write(String str) {
        this.state.write(str);
    }
}
// END
