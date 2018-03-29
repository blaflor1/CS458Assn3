import java.net.*;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
 
public class SslCli {
    // initialize socket and input output streams
    private SSLSocket        socket  = null;
    private BufferedReader   input   = null;
    private DataOutputStream out     = null;
 
    // Constructor to put ip address and port
    public SslCli(String address, int port) {
        // establish a connection
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = (SSLSocket) sslsocketfactory.createSocket(address, port);
            System.out.println("Connected");
 
            // takes input from terminal
            InputStreamReader isr = new InputStreamReader(System.in);
            input = new BufferedReader(isr);
 
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException u) {
            u.printStackTrace();
			System.err.println("Error connecting to server.");
        } catch(IOException i) {
            i.printStackTrace();
			System.err.println("Error connecting to server.");
        }
 
        // string to read message from input
        String line = "";
 
        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch(IOException i) {
                i.printStackTrace();
				System.err.println("Error while reading input.");
            }
        }
 
        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
 
    public static void main(String[] args) {

        if (args.length != 2 ) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
            System.exit(0);
        }

        String address = args[0];
        int portNum = Integer.parseInt(args[1]);
        SslCli client = new SslCli(address, portNum);
    }
}