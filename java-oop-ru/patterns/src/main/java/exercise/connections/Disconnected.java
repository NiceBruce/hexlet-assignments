package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    TcpConnection tcpcoonection;

    public Disconnected(TcpConnection tcpcoonection) {
        this.tcpcoonection = tcpcoonection;
        this.tcpcoonection.connectionState = "disconnected";
    }

    @Override
    public void connect() {
        this.tcpcoonection.state = new Connected(this.tcpcoonection);
    }

    @Override
    public void disconnect() {
        System.out.println("Error");
    }

    @Override
    public void write(String data) {
        System.out.println("Error");
    }
}
// END
