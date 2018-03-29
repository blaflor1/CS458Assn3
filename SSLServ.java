import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

public class SSLServ {

	//initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
 
    // constructor with port
    public SSLServ(int port) {
        // starts server and waits for a connection
        try {
            // Create a SSL Server Socket Factory
            SSLServerSocketFactory sslServerSocketFactory = 
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

            // Create a SSL Server Socket with SSL Socket Factory 
            server = sslServerSocketFactory.createServerSocket(port);
            System.out.println("SSL Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try { 
                BufferedReader br = 
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                    out.println(line);
                }
            } catch (IOException e) {

            }

            System.out.println("Closing connection");
 
            // close connection
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
 
    public static void main(String[] args) {

    	if (args.length != 1 ) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 1 argumnet.");
			System.exit(0);
		}

		int portNum = Integer.parseInt(args[0]);

        SSLServ server = new SSLServ(portNum);
    }
}