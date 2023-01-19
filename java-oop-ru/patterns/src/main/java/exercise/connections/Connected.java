package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    TcpConnection tcpcoonection;

    public Connected(TcpConnection tcpcoonection) {
        this.tcpcoonection = tcpcoonection;
        this.tcpcoonection.connectionState = "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error");
    }

    @Override
    public void disconnect() {
        this.tcpcoonection.state = new Disconnected(this.tcpcoonection);
    }

    @Override
    public void write(String data) {
        this.tcpcoonection.data += data;
    }
}
// END
